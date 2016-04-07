package testIBMSpeechToTextApp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechResults;

public class TestMicrophoneToIBMServiceStreaming {

   private static CountDownLatch lock = new CountDownLatch(1);
 
   public static void main(String[] args) throws FileNotFoundException, InterruptedException {
     SpeechToText service = new SpeechToText();
     service.setUsernameAndPassword("<username>", "<password>");
 
     FileInputStream audio = new FileInputStream("src/test/resources/speech_to_text/sample1.wav");
 
     RecognizeOptions options = new RecognizeOptions();
     options.continuous(true).interimResults(true).contentType(HttpMediaType.AUDIO_WAV);
 
     service.recognizeUsingWebSockets(audio, options, new BaseRecognizeDelegate() {
       @Override
       public void onMessage(SpeechResults speechResults) {
         System.out.println(speechResults);
         if (speechResults.isFinal())
           lock.countDown();
       }
     });
 
     lock.await(20000, TimeUnit.MILLISECONDS);
   }
 }