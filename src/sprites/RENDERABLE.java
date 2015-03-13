package sprites;

import util.RECTANGLEBLE;

public interface RENDERABLE extends RECTANGLEBLE{

	/**
	 * 
	 * @param dms
	 * @return false if the object is outside of the screen
	 */
	public void render(float dms);
	
}


