package gui;

import engine.QuadColor;
import sprites.SPRITEVI;

/**
 * A selectable button with text
 * @author mail__000
 *
 */
public class SelTextButt extends MultiValueA{

	protected String text;
	protected SPRITEVI sprite;
	protected TextType type;
	
	/**
	 * 
	 * @param code
	 * @param sprite
	 * @param text
	 * @param x1
	 * @param y1
	 * @param type
	 */
	public SelTextButt(int code, SPRITEVI sprite, String text, float x1, float y1, TextType type) {
		super(x1, x1+sprite.getWidth(), y1, y1+sprite.getHeight(), code, 0, 1);
		this.sprite = sprite;
		this.text = text;
		this.type = type;
	}

	@Override
	public void render(float dms) {
		if (currentValue == 1){
			sprite.render(2, this, QuadColor.fullWhite, dms);
			type.getFont().renderCentered(text, this, (getHeight()/2)*0.9f, type.getSColor());
		}
		else if (hovered){
			sprite.render(1, this, QuadColor.fullWhite, dms);
			type.getFont().renderCentered(text, this, (getHeight()/2)*0.95f, type.getHColor());
		}
		else{
			sprite.render(0, this, QuadColor.fullWhite, dms);
			type.getFont().renderCentered(text, this, (getHeight()/2), type.getNColor());
		}
	}

}
