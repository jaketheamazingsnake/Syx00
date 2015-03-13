package sprites;

import util.ARECTANGLE;

public interface SPRITEVI extends SPRITEI{

	/**
	 * REnder a specific variation of this sprite
	 * @param Nr the variation (starting at zero)
	 * @param quad within
	 * @param color the color
	 * @param dms used by animations
	 */
	
	/**
	 * 
	 * @param Nr
	 * @param r
	 * @param color
	 * @param dms
	 * @return true if the animation has reached end frame.
	 */
	public boolean render(int Nr, ARECTANGLE r, float[] color, float dms);
	
	/**
	 * 
	 * @return the nuber of variations this sprite has
	 */
	public int getVariations();
	
}
