package menu;

import sprites.BigImage;
import sprites.Gui;
import sprites.Misk;
import data.Paths;
import engine.Music;
import engine.Sound;

class Resources {

	final Music menuTheme;
	
	final Gui gui;
	final Misk misk;
	final BigImage wall;
	
	final Sound click1;
	final Sound click2;
	final Sound select1;
	final Sound select2;
	
	Resources(){
		
		Paths.Sounds sounds = Paths.Sounds();
		Paths.Music music = Paths.Music();
		Paths.BigImages images = Paths.Images();
		
		
		menuTheme = new Music(music.MainMenu_08_50);
		gui = new Gui();
		wall = new BigImage(images.WallPainting_2048x128);
		misk = new Misk();
		
		click1 = new Sound(sounds.MharshSwish_00_01);
		click2 = new Sound(sounds.MbugSquish_00_01);
		select1 = new Sound(sounds.MshoeTap_00_01);
		select2 = new Sound(sounds.MbroomSwoop_00_01);
	}
	
	void cleanup(){
		menuTheme.stop();
		wall.release();
		misk.release();
	}
	
}
