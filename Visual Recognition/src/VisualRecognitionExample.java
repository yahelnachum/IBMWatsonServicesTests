import java.io.File;

import com.ibm.watson.developer_cloud.visual_recognition.v2.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v2.model.VisualClassification;
import com.ibm.watson.developer_cloud.visual_recognition.v2.model.VisualClassifier;


public class VisualRecognitionExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		VisualRecognition service = new VisualRecognition(VisualRecognition.VERSION_DATE_2015_12_02);
	    service.setUsernameAndPassword("139fd356-ca14-4ace-a008-b7c2f852efab", "8T75cnk4jWbp");

	    File image = new File("./Images/Testing/Positive/pos_image_test (2).jpg");

	    System.out.println("Classify using all the classifiers");
	    VisualClassification result = service.classify(image);
	    System.out.println(result);

	  
/*
	    System.out.println("Create a classifier with positive and negative images");

	    File positiveImages = new File("./Images/Training/pos_image_training_set.zip");
	    File negativeImages = new File("./Images/Training/neg_image_training_set.zip");

	    VisualClassifier water_bottle = service.createClassifier("water_bottle", positiveImages, negativeImages);
	    System.out.println(water_bottle);
	    
	    result = service.classify(image);
	    System.out.println(result);*/
	}

}
