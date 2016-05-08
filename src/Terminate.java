import java.io.*;


public class Terminate implements Runnable{
	
        //Stops the audio file after 6.5 seconds
	public void run()
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			Thread.sleep(6500);
		} catch (Exception e) {}

	}
}
