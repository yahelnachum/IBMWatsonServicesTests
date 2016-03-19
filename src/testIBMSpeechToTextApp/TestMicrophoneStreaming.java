package testIBMSpeechToTextApp;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

public class TestMicrophoneStreaming {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TargetDataLine line;
		
		float sampleRate = 8000;
		int sampleSizeInBits = 8;
		int channels = 1;
		boolean signed = true;
		boolean bigEndian = true;
		AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
		DataLine.Info info = new DataLine.Info(TargetDataLine.class, 
		    format); // format is an AudioFormat object
		if (!AudioSystem.isLineSupported(info)) {
		    // Handle the error ... 

		}
		// Obtain and open the line.
		try {
		    line = (TargetDataLine) AudioSystem.getLine(info);
		    line.open(format);
		    System.out.println("Line Open Succeded!");
		    
			 // Assume that the TargetDataLine, line, has already
			 // been obtained and opened.
			 ByteArrayOutputStream out  = new ByteArrayOutputStream();
			 int numBytesRead;
			 byte[] data = new byte[line.getBufferSize() / 5];
	
			 // Begin audio capture.
			 line.start();
			 
			 // Here, stopped is a global boolean set by another thread.
			 int counter = 0;
			 while (counter < 10) {
			    // Read the next chunk of data from the TargetDataLine.
			    numBytesRead =  line.read(data, 0, data.length);
			    // Save this chunk of data.
			    out.write(data, 0, numBytesRead);
			    counter++;
			    System.out.println("BytesRead: " + numBytesRead);
			 }
			 System.out.println("Done Streaming");
			 
			try {
				System.out.println("Writing to file");
				OutputStream outputStream = new FileOutputStream("./audioFiles/newTestAudioFile.wav");
				out.writeTo(outputStream);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (LineUnavailableException ex) {
		    // Handle the error ... 
		}
	}

}
