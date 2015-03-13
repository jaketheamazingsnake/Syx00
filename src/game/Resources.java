package game;

import sprites.TileSheet;
import sprites.BigImage;
import sprites.Gui;
import sprites.Misk;
import data.CONST;
import data.Paths;
import engine.GlMaster;
import engine.Music;
import engine.Sound;

public class Resources {

	//Resources
	public final float screenWidth;
	public final float screenHeight;
	
	//textures
	public final Misk misk;
	public final BigImage wall;
	public final TileSheet worldMap;
	
	//music
	public final Music menuTheme;
	//soundFx
	public final Sound click1;
	public final Sound click2;
	public final Sound select1;
	public final Sound select2;
	
	Resources(){
		
		screenWidth = GlMaster.getWidth();
		screenHeight = GlMaster.getHeight();
		
		Paths.Sounds sounds = Paths.Sounds();
		Paths.Music music = Paths.Music();
		Paths.BigImages images = Paths.Images();
		Paths.SpriteSheets sheets = Paths.SpriteSheets();
		
		GlMaster.clearScreen();
		Gui.bind();
		Gui.fonts.BellS.render("loading textures", 20, 20);
		Gui.flush();
		GlMaster.updateDisplayAndSound();
		
		wall = new BigImage(images.WallPainting_2048x128);
		misk = new Misk();
		worldMap = new TileSheet(sheets.worldMap, CONST.TILE_SIZE_WORLDMAP, CONST.TILE_SIZE_WORLDMAP);
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		GlMaster.clearScreen();
		Gui.bind();
		Gui.fonts.BellS.render("loading sounds", 20, 20);
		Gui.flush();
		GlMaster.updateDisplayAndSound();
		
		menuTheme = new Music(music.MainMenu_08_50);

		click1 = new Sound(sounds.MharshSwish_00_01);
		click2 = new Sound(sounds.MbugSquish_00_01);
		select1 = new Sound(sounds.MshoeTap_00_01);
		select2 = new Sound(sounds.MbroomSwoop_00_01);
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void cleanup(){
		menuTheme.stop();
		wall.release();
		misk.release();
		worldMap.release();
	}
	
}
