package menu;



import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import sprites.Gui;
import sprites.RenSpriteV;
import util.Vector;
import engine.GlMaster;

public class MenuCore{

	static final int MAIN = 0;
	static final int SETTINGS = 1;
	static final int SANDBOX = 2;
	static final int EXIT = 3;
	private int currentS = MAIN;
	Resources res = new Resources();
	private SCREEN[] screens = new SCREEN[4];
	private GameSpark result;
	protected boolean running = true;
	
	private RenSpriteV mouse;
	
	GlMaster glManager;
	
	public MenuCore(GlMaster glManager){
		this.glManager = glManager;
		res.cleanup();
		res = new Resources();
		SCREEN.centerBounds(GlMaster.getWidth(), GlMaster.getHeight());
		screens[0] = new ScreenMain(res);
		screens[2] = new ScreenSandbox(res);
		mouse = new RenSpriteV(Gui.sprites.mouse_cruiser(), 0, 0);
		Mouse.setGrabbed(true);
		
	}
	
	public GameSpark start(){
		loop();
		return result;
	}
	
	private void loop(){

		float updateT = System.nanoTime();
		UpdateSheet s = new UpdateSheet();
		res.menuTheme.play();
		poll(s);
		while (running){
			if (System.nanoTime() - updateT >= 25000000){
				updateT = (System.nanoTime() - updateT)/1000000000;
				s.update(updateT);
				update(s);
				render(updateT);
				poll(s);
				updateT = System.nanoTime();
			}else{
				try {
					Thread.sleep((long)(25000000 - System.nanoTime() + updateT)/1000000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		res.cleanup();
	}
	
	
	private void update(UpdateSheet s){
		if (screens[currentS].update(s))
			mouse.setVariation(1);
		
	}
	
	private void render(float dms){
		GlMaster.clearScreen();
		screens[currentS].render(dms);
		mouse.render(dms);
		Gui.flush();
		
		if (GlMaster.updateDisplayAndSound()){
			running = false; 
		}
	}

	private void poll(UpdateSheet s){
		
		if (Mouse.isButtonDown(0))
			mouse.setVariation(2);
		else
			mouse.setVariation(0);
	
		s.setMouseCoo(Mouse.getX(), GlMaster.getHeight() - Mouse.getY() - 1);
		mouse.moveX1(Mouse.getX());
		mouse.moveY1(GlMaster.getHeight() - Mouse.getY() - 1);
		
		
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
			running = false;
		}
		
		while(Mouse.next()) {
			if (Mouse.getEventButton() == 0) {
				if (Mouse.getEventButtonState()) {
					currentS = screens[currentS].click(s);
				}
			}
		}
		
	}
	
	class UpdateSheet {

		//private Random rnd;
		
		private double startTime;
		private double timeSinceStartD = 0;
		private int timeSinceStartI = 0;
		private float deltaT;
		
		
		private Vector mouseCoo;
		
		private UpdateSheet(){
			//rnd = new Random();
			startTime = System.currentTimeMillis();
			mouseCoo = new Vector(0,0);
		}
		
		private void update(float dms){
			timeSinceStartD = (System.currentTimeMillis()-startTime)/1000;
			timeSinceStartI = (int)timeSinceStartD;
		
			this.deltaT = dms;
		}
		
		private void setMouseCoo(int x, int y){
			mouseCoo.setX(x);
			mouseCoo.setY(y);
		}
		
		/**
		 *
		 * @return Time since game started in second as a double
		 */
		double timeSinceStartD(){return timeSinceStartD;}
		/**
		 * 
		 * @return Time since game started in second as an int
		 */
		int timeSinceStartI(){return timeSinceStartI;}
		/**
		 * 
		 * @return the coordinate of the mouse
		 */
		Vector mouseCoo(){return mouseCoo;}
		/**
		 * 
		 * @return time since last update in seconds
		 */
		float ds(){return deltaT;}
		
		void start(GameSpark gs){
			result = gs;
			running = false;
		}
		
	}
	
}
