package launcher;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.DisplayMode;

import sprites.Gui;
import sprites.GuiSprites;
import sprites.RenSprite;
import sprites.RenText;
import util.Vector;
import engine.GlMaster;
import engine.GFX;
import engine.PSettings;
import gui.ClButt;
import gui.ClText;
import gui.ClTextButton;
import gui.GuiSection;
import gui.ScrollBox;
import gui.SelTextButt;
import gui.TextType;


public class Launcher{
	
	public static final int LAUNCHER_WIDTH = 960;
	public static final int LAUNCHER_HEIGHT = 480;
	
	//engine related
	private boolean playGame = false;
	private final PSettings settings;
	private final Resources res;
	private Vector mouseCoo;
	protected boolean running = true;

	//layout
	private final FloatingBackgroundMap background;
	
	//button related
	private GuiSection[] pages;
	private int pageNr = 0;
	private final int PLAY = 100;
	private final int SETTINGS = 101;
	private final int INFO = 102;
	private final int EXIT = 103;
	private final int SAVE = 104;
	private final int RESET = 105;
	private final int MAIN = 106;
	private final int SETTINGS2 = 107;
	private final int MESSAGE = 108;

	
	private boolean uSure = false;
	private GuiSection message;
	
	public Launcher(PSettings settings) {
		
		mouseCoo = new Vector(Mouse.getX(), LAUNCHER_HEIGHT - Mouse.getY() - 1);
		res = new Resources();
		this.settings = settings;

		background = new FloatingBackgroundMap(res);
		
		pages = new GuiSection[]{new GuiSection(res.select1, res.click1), 
				new GuiSection(res.select1, res.click1)};
	
		float x1 = 70;
		float y1 = 70;
		
		GuiSprites g = Gui.sprites;
	
		pages[0].add(new RenSprite(g.logo_launcher, x1, y1));
		x1 = GlMaster.getWidth()- x1 - g.button_large.getWidth();
		y1 = (GlMaster.getHeight() - g.button_large.getHeight()*5)/2;
		pages[0].add(new RenSprite(g.button_large_top, x1, y1));
		pages[0].add(new ClTextButton(PLAY, g.button_large, "Play", x1, pages[0].getY2(), TextType.Launcher));
		pages[0].add(new ClTextButton(SETTINGS, g.button_large, "Settings", x1, pages[0].getY2(), TextType.Launcher));
		pages[0].add(new ClTextButton(INFO, g.button_large, "Info", x1, pages[0].getY2(), TextType.Launcher));
		pages[0].add(new ClTextButton(EXIT, g.button_large, "Exit", x1, pages[0].getY2(), TextType.Launcher));
		pages[0].add(new RenSprite(g.button_large_bottom, x1, pages[0].getY2()));
		
		pages[0].init();
		
		x1 = 40;
		RenText temp = new RenText("Settings", 0, 0, TextType.Launcher);
		temp.centerIn(x1, x1 + 3*g.button_medium.getWidth(), 0, 50);
		
		pages[1].add(temp);

		DisplayMode[] dms = GlMaster.getDisplayModes();
		String[] resoOptions = new String[dms.length];
		for(int i = 0; i < dms.length; i++){
			resoOptions[i] = Integer.toString(dms[i].getWidth())+"x"+Integer.toString(dms[i].getHeight());
		}

		ScrollBox scroll = new ScrollBox(PSettings.SETT.DISPLAYMODE.getCode(), x1, 100, resoOptions, PSettings.SETT.DISPLAYMODE.getLabel(), g);
		settings.addObserver(scroll.getObserver());
		pages[1].add(scroll);
		SelTextButt sel = new SelTextButt(PSettings.SETT.FULLSCREEN.getCode(), g.button_medium, PSettings.SETT.FULLSCREEN.getLabel(), x1, scroll.getY2(), TextType.Launcher);
		settings.addObserver(sel.getObserver());
		pages[1].add(sel);
		sel = new SelTextButt(PSettings.SETT.VSYNCH.getCode(), g.button_medium, PSettings.SETT.VSYNCH.getLabel(), x1, pages[1].getY2(), TextType.Launcher);
		settings.addObserver(sel.getObserver());
		pages[1].add(sel);
		
		x1 = pages[1].getX2();
		pages[1].add(new SelTextButt(1000, g.button_medium, "Option", x1, 100, TextType.Launcher));	
		pages[1].add(new SelTextButt(1000, g.button_medium, "Option", x1, pages[1].getY2(), TextType.Launcher));
		pages[1].add(new SelTextButt(1000, g.button_medium, "Option", x1, pages[1].getY2(), TextType.Launcher));	
		pages[1].add(new SelTextButt(1000, g.button_medium, "Option", x1, pages[1].getY2(), TextType.Launcher));	
		pages[1].add(new SelTextButt(1000, g.button_medium, "Option", x1, pages[1].getY2(), TextType.Launcher));
		x1 = pages[1].getX2();
		pages[1].add(new SelTextButt(1000, g.button_medium, "Option", x1, 100, TextType.Launcher));	
		pages[1].add(new SelTextButt(1000, g.button_medium, "Option", x1, pages[1].getY2(), TextType.Launcher));
		pages[1].add(new SelTextButt(1000, g.button_medium, "Option", x1, pages[1].getY2(), TextType.Launcher));	
		pages[1].add(new SelTextButt(1000, g.button_medium, "Option", x1, pages[1].getY2(), TextType.Launcher));	
		pages[1].add(new SelTextButt(1000, g.button_medium, "Option", x1, pages[1].getY2(), TextType.Launcher));
		
		x1 = GlMaster.getWidth() - (GlMaster.getWidth()-pages[1].getX2())/2 - g.checkBox_Large.getWidth();
		y1 = GlMaster.getHeight()/2-g.checkBox_Large.getHeight()/2;
		
		pages[1].add(new ClTextButton(MESSAGE, g.checkBox_Large, "Ok", x1, y1, TextType.Launcher));
		pages[1].add(new ClTextButton(MAIN, g.checkBox_Large, "X", pages[1].getX2(), y1, TextType.Launcher));
		pages[1].add(new ClButt(SETTINGS2, g.arrow_right, x1+5, pages[1].getY2()+25));
		pages[1].init();
		
		message = new GuiSection(res.select1, res.click1);
		
		RenText sta = new RenText("Are you committed to these changes?", 0, 0, TextType.Launcher);
		sta.centerIn(0, GlMaster.getWidth(), 0, GlMaster.getHeight());
		message.add(sta);
		ClText ctext = new ClText(SAVE, "Yes", 0, message.getY2()+50, TextType.Launcher);
		ctext.centerX(0, GlMaster.getWidth());
		ctext.incrementX(-100);
		message.add(ctext);
		message.add(new ClText(RESET, "No", message.getX2()+20, message.getY1(), TextType.Launcher));
		
		message.init();

	}
	
	public boolean start(){
		res.intro.play();
		resetSetts();
		loop();
		res.release();
		return playGame;
		
	}
	
	protected void loop(){

		double tempTime = System.nanoTime();
		float updateT = System.nanoTime();
		int instructionsLeft = 0;
		int timeSinceStart = 0;
		int timeSinceStart2 = 0;
		
		while (running){
			
			if (System.nanoTime() - tempTime >= 1000000000){
				System.out.println(instructionsLeft);
				tempTime = System.nanoTime();
				instructionsLeft = 0;
				timeSinceStart++;
				timeSinceStart2 = timeSinceStart;
			}
			if (System.nanoTime() - updateT >= 25000000){
				updateT = (System.nanoTime() - updateT)/1000000000;
				poll();
				update(updateT, timeSinceStart2);
				render(updateT);
				timeSinceStart2 = -1;
				updateT = System.nanoTime();
			}
			instructionsLeft++;
		}	
	}
	
	protected void update(float dms, int s){

		if(!uSure){
		pages[pageNr].update(mouseCoo);
		}else{
			message.update(mouseCoo);
		}
	
		background.update(dms, s);
	}
	
	private void click(int code){

		
		switch(code){
		case -1:
			break;
		case PLAY: exit(true);
			break;
		case SETTINGS: resetSetts(); pageNr = 1;
			break;
		case INFO:
			break;
		case EXIT: exit(false);
			break;
		case SAVE: uSure = false; saveSetts(); pageNr = 0;
			break;
		case RESET: uSure = false; resetSetts();
			break;
		case MAIN: pageNr = 0; resetSetts();
			break;
		case SETTINGS2:
			break;
		case MESSAGE: uSure = true;
		}
		
	}

	protected void poll() {
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
			exit(false);
		}
		while(Mouse.next()) {
			if (Mouse.getEventButton() == 0) {
				if (Mouse.getEventButtonState()) {
					
					
					if (uSure)
						click(message.click());
					else
					click(pages[pageNr].click());
				}
			}
		}

		mouseCoo.setX(Mouse.getX());
		mouseCoo.setY(LAUNCHER_HEIGHT - (Mouse.getY() - 1));
		
	}

	protected void render(float dms) {
		background.render(dms);
		Gui.bind();
		pages[pageNr].render(dms);
		Gui.flush();
		background.renderClouds();
		
		if(uSure){
			GFX.darkenScreen(0.85f);
			Gui.bind();
			message.render(dms);
			Gui.flush();
		}
		
		if (GlMaster.updateDisplayAndSound()){
			running = false;
		}
	}
	
	private void resetSetts() {
		settings.restoreObservers();
	}

	private void saveSetts() {
		uSure = false;
		settings.save();
	}

	private void exit(boolean playgame){
		this.playGame = playgame;
		running = false;	
	}
}