package game;

import org.lwjgl.input.Mouse;

import util.Vector;

public class GameInfo {

	//private Random rnd;
	
	private double startTime;
	private double timeSinceStartD = 0;
	private int timeSinceStartI = 0;
	float deltaT;
	
	private final float screenWidth;
	private final float screenHeight;
	
	private final Vector mouseCoo;
	
	public final String worldName = "Hanarion";
	public final int year = 1523;
	
	GameInfo(float screenWidth, float screenHeight){
		//rnd = new Random();
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		startTime = System.currentTimeMillis();
		mouseCoo = new Vector(0,0);
	}
	
	void update(float dms){
		
		timeSinceStartD = (System.currentTimeMillis()-startTime)/1000;
		timeSinceStartI = (int)timeSinceStartD;

		this.deltaT = dms;
	}
	
	boolean setMouse(float mouseX, float mouseY){
		float newmouseX = mouseX;
		float newmouseY = screenHeight - Mouse.getY() - 1;
		
		if (newmouseX != mouseCoo.getX() || newmouseY != mouseCoo.getY()){
			mouseCoo.setX(newmouseX);
			mouseCoo.setY(newmouseY);
			return true;
		}
		
		return false;
	}
	
	/**
	 *
	 * @return Time since game started in second as a double
	 */
	public double timeSinceStartD(){return timeSinceStartD;}
	/**
	 * 
	 * @return Time since game started in second as an int
	 */
	public int timeSinceStartI(){return timeSinceStartI;}
	/**
	 * 
	 * @return time since last update in seconds
	 */
	public float ds(){return deltaT;}
	public float SWidth(){return screenWidth;}
	public float SHeight(){return screenHeight;}
	/**
	 * 
	 * @return the coordinate of the mouse
	 */
	public Vector mouseCoo(){return mouseCoo;}

	public void centerMouse(){
		GameCore.setMouse((int) screenWidth/2, (int) screenHeight/2); 
	}
	
}
