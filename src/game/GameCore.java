package game;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import menu.GameSpark;
import engine.GlMaster;
import engine.PSettings;
import game.world.WorldState;

public class GameCore {

	private final Resources res;
	private final PSettings setts;
	
	private final long FPS;
	private boolean running = true;
	
	private final STATE[] states;
	private int state = 0;
	private int previousState = -1;
	
	public final static int WORLD_MAP = 0;
	public final static int SETTLEMENT = 1;
	public final static int BACK = -1;
	
	public GameCore(GameSpark sp, PSettings settings) {
		res = new Resources();
		setts = settings;
		double temp = 1f/settings.getValue(PSettings.SETT.FPS);
		temp *= 1000000000;
		FPS = (long) temp;
		
		states = new STATE[1];
		states[0] = new WorldState(res, sp);
	}
	
	public boolean start(){
		loop();
		return true;
	}
	
	private void loop(){
		
		final GameInfo nfo = new GameInfo(GlMaster.getWidth(),GlMaster.getHeight());
		
		long timeElapsed;
		long last = System.nanoTime();
		float dms = 0;
		
		while (running){
			
			timeElapsed = System.nanoTime() - last;
			
			if (timeElapsed >= FPS*2){
				System.out.println("LAGG!!!");
				last = System.nanoTime();
				dms = timeElapsed/1000000000f;
				poll(nfo);
				update(dms, nfo);
			}else if (timeElapsed >= FPS){
				last = System.nanoTime();
				dms = timeElapsed/1000000000f;
				hover(nfo);
				poll(nfo);
				update(dms, nfo);
				render(dms, nfo);
			}else{
				try {
					Thread.sleep((FPS - timeElapsed)/1000000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		res.cleanup();
	}
	
	private void changeState(int newState){
		if (newState == BACK && previousState == -1){
			running = false;
		}else if (newState != state){
			previousState = state;
			state = newState;
		}
	}
	
	private void hover(GameInfo nfo){
		if (nfo.setMouse(Mouse.getX(), Mouse.getY()));
			states[state].hover(nfo);
	}
	
	private void update(float dms, GameInfo nfo){
		nfo.update(dms);
		states[state].update(dms, nfo);
	}
	
	private void render(float dms, GameInfo nfo){
		GlMaster.clearScreen();

		states[state].render(dms, nfo);
		
		if (GlMaster.updateDisplayAndSound()){
			running = false;
		}
	}

	private void poll(GameInfo nfo){
		
		if (Keyboard.isKeyDown(PSettings.KEY.DOWN.getKeyCode())){
			states[state].input(PSettings.KEY.DOWN);
		}
		if (Keyboard.isKeyDown(PSettings.KEY.UP.getKeyCode())){
			states[state].input(PSettings.KEY.UP);
		}
		if (Keyboard.isKeyDown(PSettings.KEY.LEFT.getKeyCode())){
			states[state].input(PSettings.KEY.LEFT);
		}
		if (Keyboard.isKeyDown(PSettings.KEY.RIGHT.getKeyCode())){
			states[state].input(PSettings.KEY.RIGHT);
		}
		
		while(Mouse.next()) {
			changeState(states[state].click(Mouse.getEventButton(), Mouse.getEventButtonState(), nfo));
		}
		
		PSettings.KEY k;
		
		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
		        k = setts.getKey(Keyboard.getEventKey());
				if (k == PSettings.KEY.ESCAPE)
					running = false;
				else if (k == PSettings.KEY.NONE);
				else
					changeState(states[0].input(k));
			}
		    else {
		    	//System.out.println(setts.getKey(Keyboard.getEventKey()) + " released");
		    }
		}
		
	}
	
	public static void setMouse(int x, int y){
		Mouse.setCursorPosition(x, y);
	}

}
