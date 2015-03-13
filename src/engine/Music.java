package engine;

import java.io.IOException;



import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Music {
	
	private Audio audio;
	
	public Music (String path){
		try {
			audio = AudioLoader.getStreamingAudio("OGG", ResourceLoader.getResource(path));
		} catch (IOException e) {
			System.out.println("cannot find sound: " + path);
			e.printStackTrace();
		}
	}
	
	public void play(){
		audio.playAsMusic(1.0f, 1.0f, true);
	}
	
	public void stop(){
		audio.stop();
	}
	
}