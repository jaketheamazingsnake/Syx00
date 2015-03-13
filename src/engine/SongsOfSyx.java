package engine;

import game.GameCore;

import org.lwjgl.opengl.DisplayMode;

import launcher.Launcher;
import menu.GameSpark;
import menu.MenuCore;

public class SongsOfSyx {

	private static GlMaster glManager;
	
	public static void main(String[] args) {

		glManager = new GlMaster();
		PSettings settings = new PSettings();
		try{
			if (true){ //args.length == 1 
				glManager.createDisplay("Launcher", new DisplayMode(Launcher.LAUNCHER_WIDTH,Launcher.LAUNCHER_HEIGHT), false);
				if (new Launcher(settings).start()){
					glManager.setSettings(settings);
					loop();
	
				}
				glManager.cleanup();
			}
		}catch (Exception e){
			e.printStackTrace();
			glManager.cleanup();
		}
	}

	private static void loop(){
		
		while(true){
			PSettings settings = new PSettings();
			GameSpark gs = new MenuCore(glManager).start();
			if (gs == null)
				break;
			if (!new GameCore(gs, settings).start())
				break;
		}
	}
	
}
