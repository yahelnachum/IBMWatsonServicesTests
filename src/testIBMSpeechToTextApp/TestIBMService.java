package testIBMSpeechToTextApp;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechResults;

public class TestIBMService {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello World");
		
		String username = "86fd34e9-23e7-492e-93bc-ea0218eb963c";
		String password = "RkKUVPzpsIvU";
		SpeechToText service = new SpeechToText();
		service.setUsernameAndPassword(username, password);

		String audioFile1 = "./audioFiles/Homer_Simpson_-_Help_me_Lisa_I_have_serious_Mental.flac";
		String audioFile2 = "./audioFiles/short_audio.flac";
		File audio = new File(audioFile1);
		System.out.println("File exists: " + audio.exists());
		Map params = new HashMap();
		params.put("audio", audio);
		params.put("content_type", "audio/flac; rate=44100");
		params.put("word_confidence", false);
		params.put("continuous", false);
		params.put("timestamps", false);
		params.put("inactivity_timeout", 30);
		params.put("max_alternatives", 1);

		SpeechResults transcript = service.recognize(params);

		System.out.println(transcript);
	}

}
