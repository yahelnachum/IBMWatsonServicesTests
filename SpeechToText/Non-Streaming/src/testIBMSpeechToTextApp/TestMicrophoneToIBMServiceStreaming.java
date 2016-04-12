package testIBMSpeechToTextApp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

import com.ibm.watson.developer_cloud.speech_to_text.v1.RecognizeOptions;
import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechResults;
import com.ibm.watson.developer_cloud.speech_to_text.v1.websocket.BaseRecognizeDelegate;

public class TestMicrophoneToIBMServiceStreaming {
	static File wavFile = new File("./audioFiles/RecordAudio.wav");
	
	static AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
	 
    // the line from which audio data is captured
    static TargetDataLine line;
 
    /**
     * Defines an audio format
     */
    static AudioFormat getAudioFormat() {
        float sampleRate = 16000;
        int sampleSizeInBits = 8;
        int channels = 2;
        boolean signed = true;
        boolean bigEndian = true;
        AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits,
                                             channels, signed, bigEndian);
        return format;
    }
    
	 public static void main(String[] args){
		 try {

				   
	            AudioFormat format = getAudioFormat();
	            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
	 
	            // checks if system supports the data line
	            if (!AudioSystem.isLineSupported(info)) {
	                System.out.println("Line not supported");
	                System.exit(0);
	            }
	            line = (TargetDataLine) AudioSystem.getLine(info);
	            line.open(format);
	            line.start();   // start capturing
	 
	            System.out.println("Start capturing...");
	 
	            AudioInputStream ais = new AudioInputStream(line);
	 
	            System.out.println("Start recording...");
	 
	            // start recording
				 SpeechToText service = new SpeechToText();
				   service.setUsernameAndPassword("1e3eacc7-03a5-4448-9053-9092e38f5d90", "uD45nLN3Jtvl");
				   service.setEndPoint("https://stream.watsonplatform.net/speech-to-text/api");
	
				   RecognizeOptions options = new RecognizeOptions().contentType("audio/wav")
				     .continuous(true).interimResults(true);
				   System.out.println("Done12"); 
				   service.recognizeUsingWebSockets(ais,
				     options, new BaseRecognizeDelegate()
				     {
				       @Override
				       public void onMessage(SpeechResults speechResults) {
				         System.out.println(speechResults);
				       }
				     }
				   );
				   System.out.println("Done1");  
	            AudioSystem.write(ais, fileType, wavFile);
	            System.out.println("Done");
	 
	        } catch (LineUnavailableException ex) {
	            ex.printStackTrace();
	        } catch (IOException ioe) {
	            ioe.printStackTrace();
	        }
	 }
}
