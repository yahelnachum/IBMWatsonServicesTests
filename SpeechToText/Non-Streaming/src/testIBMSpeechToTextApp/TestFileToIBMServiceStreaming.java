package testIBMSpeechToTextApp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.CountDownLatch;

import com.ibm.watson.developer_cloud.speech_to_text.v1.RecognizeOptions;
import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechResults;
import com.ibm.watson.developer_cloud.speech_to_text.v1.websocket.BaseRecognizeDelegate;

public class TestFileToIBMServiceStreaming {

   private static CountDownLatch lock = new CountDownLatch(1);
 
   public static void main(String[] args) throws FileNotFoundException, InterruptedException {
	   SpeechToText service = new SpeechToText();
	   service.setUsernameAndPassword("1e3eacc7-03a5-4448-9053-9092e38f5d90", "uD45nLN3Jtvl");
	   service.setEndPoint("https://stream.watsonplatform.net/speech-to-text/api");

	   RecognizeOptions options = new RecognizeOptions().contentType("audio/flac")
	     .continuous(true).interimResults(true);

	   service.recognizeUsingWebSockets(new FileInputStream("./audioFiles/short_audio.flac"),
	     options, new BaseRecognizeDelegate()
	     {
	       @Override
	       public void onMessage(SpeechResults speechResults) {
	         System.out.println(speechResults);
	       }
	     }
	   );
   }
 }