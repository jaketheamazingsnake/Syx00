package data;

/**
 * A collection of all the paths in the game
 * @author mail__000
 *
 */
public class Paths {

	/**
	 * 
	 * @return all engine related
	 */
	public static Engine Engine(){
		return new Engine();
	}
	
	/**
	 * 
	 * @return all big images
	 */
	public static BigImages Images(){
		return new BigImages();
	}
	
	/**
	 * 
	 * @return all spritesheets
	 */
	public static SpriteSheets SpriteSheets(){
		return new SpriteSheets();
	}
	
	/**
	 * 
	 * @return all soundeffects
	 */
	public static Sounds Sounds(){
		return new Sounds();
	}
	
	/**
	 * 
	 * @return all music
	 */
	public static Music Music(){
		return new Music();
	}
	
	/**
	 * 
	 * @return all data
	 */
	public static Data Data(){
		return new Data();
	}
	
	public static class Engine{
		
		public final ImData systemIcon16x16;
		public final ImData systemIcon32x32;
		
		private Engine(){
			
			String root 				="res/engine/";
			
			systemIcon16x16				=new ImData 		(root+"icon16x16.png",				16,		16);
			systemIcon32x32				=new ImData 		(root+"icon32x32.png",				32,		32);					
		}
	}
	
	public static class BigImages{
		
		public final String Map1_1024x1024;
		public final String WallPainting_2048x128;
		public final String WallPainting_2_2048x128;
		public final String Scene1_1_128x64;
		
		private BigImages(){
			
			String root					="res/images/";
			
			Map1_1024x1024				= root+"Map1_1024x1024.png";
			WallPainting_2048x128		= root+"WallPainting_2048x128.png";
			WallPainting_2_2048x128		= root+"WallPainting_2_2048x128.png";
			Scene1_1_128x64				= root+"Scene1_1_128x64.png";
		}
	}
	
	public static class SpriteSheets{
		
		public final String misc;
		public final String Gui;
		public final String worldMap;
		
		private SpriteSheets(){
			
			String root					="res/spriteSheets/";
			
			misc						=root+"MiskSheet.png";
			Gui							=root+"Gui.png";
			worldMap					=root+"WorldMap.png";
		}
		
	}
	
	public static class Sounds{
		
		public final String MbroomSwoop_00_01;
		public final String MbugSquish_00_01;
		public final String MharshSwish_00_01;
		public final String MshoeTap_00_01;
		public final String Wind;
		
		private Sounds(){
			
			String root 				="res/sound/";
			
			MbroomSwoop_00_01 			=root+"MbroomSwoop.wav";
			MbugSquish_00_01			=root+"MbugSquish.wav";
			MharshSwish_00_01			=root+"MharshSwish.wav";
			MshoeTap_00_01				=root+"MshoeTap.wav";
			Wind						=root+"Wind3.wav";
		}
	}
	
	public static class Music{
		
		public final String Launcher_01_15;
		public final String MainMenu_08_50;
		
		private Music(){
			String root 				="res/music/";
			
			Launcher_01_15 				=root+"Launcher.ogg";
			MainMenu_08_50				=root+"MainMenu.ogg";
		}
		
	}
	
	public static class Data{
		
		public final String PlayerSettings;
		
		private Data(){
			
			String root					="res/data/";
			
			PlayerSettings				=root+"PlayerSettings.prop";	
		}
		
	}
	
	public static class ImData{
		
		private String path;
		private int width;
		private int height;
		
		private ImData(String p, int imageWidth, int imageHeight){
			path = p;
			width = imageWidth;
			height = imageHeight;
		}
		
		public String getPath(){
			return path;
		}
		
		public int getWidth(){
			return width;
		}
		
		public int getHeight(){
			return height;
		}

	}
	

	

	
}
