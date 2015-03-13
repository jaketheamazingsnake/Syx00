package sprites;

public interface ANIMATIONI extends SPRITEI{

	/**
	 * 
	 * @param FPS
	 */
	public void setSpeed(float FPS);
	/**
	 * 
	 */
	public void stop();
	/**
	 * 
	 */
	public void start();
	/**
	 * 
	 * @return
	 */
	public int getCurrentFrame();
	/**
	 * 
	 * @return
	 */
	public int getFrames();
	
}
