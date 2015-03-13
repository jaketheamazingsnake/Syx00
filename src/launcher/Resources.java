package launcher;


import sprites.BigImage;
import sprites.Misk;
import data.Paths;
import engine.Music;
import engine.Sound;

public class Resources {

	final BigImage map;
	final Misk misk;

	final Sound click1;
	final Sound click2;
	final Sound select1;
	final Sound select2;
	final Sound wind;
	final Music intro;

	
	Resources (){

		Paths.Sounds sounds = Paths.Sounds();
		Paths.Music music = Paths.Music();
		Paths.BigImages images = Paths.Images();
		
		map = new BigImage(images.Map1_1024x1024);
		
		misk = new Misk();
		
		click1 = new Sound(sounds.MharshSwish_00_01);
		click2 = new Sound(sounds.MbugSquish_00_01);
		select1 = new Sound(sounds.MshoeTap_00_01);
		select2 = new Sound(sounds.MbroomSwoop_00_01);
		wind = new Sound(sounds.Wind);
		intro = new Music(music.Launcher_01_15);
		
	}

	public void release() {

		click1.stop();
		click2.stop();
		select1.stop();
		select2.stop();
		intro.stop();
		
		map.release();
		misk.release();
		
	}
	
}
