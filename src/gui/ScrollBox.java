package gui;

import sprites.GuiSprites;
import sprites.SPRITEI;
import util.Rectangle;
import util.Vector;
import data.CONST;
import engine.QuadColor;

/**
 * A fixed object
 * @author mail__000
 *
 */
public class ScrollBox extends MultiValueA{

	private final SPRITEI sprite;
	private final ClButt rightArrow;
	private final ClButt leftArrow;
	private final Rectangle labelBox;
	private final String label;
	private final String[] values;
	private final Rectangle valuesBox;
	private final TextType type = TextType.Launcher;

	/**
	 * 
	 * @param code
	 * @param x1
	 * @param y1
	 * @param values
	 * @param label
	 * @param gui
	 */
	public ScrollBox(int code, float x1, float y1, String[] values, String label, GuiSprites gui) {
		
		super(x1, x1+gui.scrollable_box.getWidth(), y1, y1+gui.scrollable_box.getHeight(), code, 0, values.length-1);
		this.label = label;
		
		labelBox = new Rectangle(getX1(), getX2(),
				getY1(), getY1()+gui.button_medium.getHeight());
		
		this.values = values;
		valuesBox = new Rectangle(getX1(), getX2(), 
				getY1()+(gui.button_medium.getHeight())*2, getY2());
		
		sprite = gui.scrollable_box;
		leftArrow = new ClButt(1, gui.arrow_relief_left, getX1()+8*CONST.SCALE, getY1()+(gui.button_medium.getHeight()-CONST.SCALE)+CONST.SCALE*2);
		rightArrow = new ClButt(1, gui.arrow_relief_right, 
				leftArrow.getX2(), leftArrow.getY1());
	}

	@Override
	public int hover(Vector mouseCoo){
		int a = leftArrow.hover(mouseCoo);
		int b = rightArrow.hover(mouseCoo);
		if (a == 1 || b == 1)
			return 1;
		if (a == 0 || b == 0)
			return 0;
		return -1;
		
	}
	
	@Override
	public boolean click() {
		if (leftArrow.click()){
			incrementValue(-1);
		}
		else if (rightArrow.click()){
			incrementValue(1);
		}
		else
			return false;
		return true;
	}

	@Override
	public void render(float dms) {
		sprite.render(this, QuadColor.fullWhite, dms);
		leftArrow.render(dms);
		rightArrow.render(dms);
		type.getFont().renderCentered(label, labelBox, labelBox.getHeight()/2, type.getNColor());
		type.getFont().renderCentered(values[currentValue], valuesBox, valuesBox.getHeight()/2, type.getNColor());
	}
	
}
