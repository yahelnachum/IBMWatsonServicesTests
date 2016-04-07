package testIBMSpeechToTextApp;


public class TestMicrophoneStreaming {

    /**
     * Entry to run the program
     */
    public static void main(String[] args) {
        final JavaSoundRecorder recorder = new JavaSoundRecorder();
 
        // creates a new thread that waits for a specified
        // of time before stopping
        Thread stopper = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                recorder.finish();
            }
        });
 
        stopper.start();
 
        // start recording
        recorder.start();
		
		
		/*// Obtain and open the line.
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
		}*/
	}

}
