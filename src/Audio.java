//package Audio;
import javazoom.jl.player.*;

import java.io.*;

public class Audio implements Runnable{

	public static Player playplayer;
	public static FileInputStream fis;
	public static BufferedInputStream bis;
	public static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	//boolean for checking if you should still play
	static boolean running = true;

public void Terminate()//method to kill playback
	{
		running = false;
		playplayer.close();
		
	}
	
        //runs the audio file, the terminate action is in found in the terminate.java
	public void run()
	{
		BufferedReader fr = null;
		String choose = null;
                
		{

		try {
			String file = "Alert1.mp3";
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			playplayer = new Player(bis);

			playplayer.play();

		} catch (Exception e) {
			System.out.print("ERROR " + e);
		}
	}
	}
	// if (audioplay != null) {
	// Terminate();
	// playplayer.close();
	// try {
	// audioplay.join();
	// } catch (InterruptedException ex) {
	// //catch the exception
	// }
	// System.out.print("The thread has stopped");
	// }

}
