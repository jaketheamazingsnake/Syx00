package menu;

import menu.MenuCore.UpdateSheet;
import util.Rectangle;
import data.CONST;
import gui.GuiSection;

public abstract class SCREEN {
	
	protected final GuiSection[] sections;
	protected int sectionNr = 0;
	protected int id;
	protected static Rectangle bounds = new Rectangle(0, CONST.MIN_WIDTH, 0, CONST.MIN_HEIGHT);
	
	/**
	 * 
	 * @param id Core.something
	 * @param sectionsNr How many guisections
	 */
	SCREEN(int id, int sectionsNr){
		this.id = id;
		sections = new GuiSection[sectionsNr];
	}
	
	/**
	 * 
	 * @param s
	 * @return return true if something is hovered
	 */
	abstract boolean update(UpdateSheet s);
	
	/**
	 * 
	 * @param s
	 * @return own id or id of other if there's a switch.
	 */
	abstract int click(UpdateSheet s);
	
	abstract void render(float dms); 
	
	/**
	 * adapts to the new screen resolution
	 */
	abstract void reinit(Resources res);
	
	/**
	 * only called by Core
	 * @param x2
	 * @param y2
	 */
	static void centerBounds(float x2, float y2){
		bounds.centerIn(0, x2, 0, y2);
	}
	
	
}
