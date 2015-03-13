package engine;

import java.io.IOException;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Sound {
	
	private Audio audio;
	
	public Sound (String path){
		try {
			audio = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream(path));

		} catch (IOException e) {
			System.out.println("cannot find soundEffect: " + path);
			e.printStackTrace();
		}	
	}
	
	public void play(){
		audio.playAsSoundEffect(1.0f, 0.5f, false);
	}
	
	public void stop(){
		audio.stop();
	}
	
}