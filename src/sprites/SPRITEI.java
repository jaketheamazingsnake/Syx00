package sprites;

import util.ARECTANGLE;


public interface SPRITEI {

	/**
	 * Render this sprite within quad with color color
	 * @param quad
	 * @param color
	 * @param dms used by animations to determine which frame to render
	 */
	
	/**
	 * 
	 * @param quad
	 * @param color
	 * @param dms
	 * @return true if the animation has reached its end frame. Else false
	 */
	public boolean render(ARECTANGLE r, float[] color, float dms);
	
	/**
	 * 
	 * @return game width of sprite
	 */
	public float getWidth();
	/**
	 * 
	 * @return game height of sprite
	 */
	public float getHeight();
	
}
