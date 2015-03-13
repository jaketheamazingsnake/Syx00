package gui;

import sprites.RENDERABLE;
import util.Vector;

public abstract interface CLICKABLE extends RENDERABLE{

	/**
	 * 
	 * @param mouseCoo
	 * @return -1 if not hovered, 0 if hovered, 1 if just resently been hovered
	 */
	public int hover(Vector mouseCoo);
	
	/**
	 * 
	 * @return the code of the object if hovered, else -1
	 */
	public boolean click();
	
	public int getCode();
	
	
}
