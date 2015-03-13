package gui;

import engine.QuadColor;
import sprites.SPRITEVI;

/**
 * A clickable button with text
 * @author mail__000
 *
 */
public class ClTextButton extends ClickableA{

	protected String text;
	protected SPRITEVI sprite;
	protected TextType type;
	
	public ClTextButton(int code, SPRITEVI sprite, String text, float x1, float y1, TextType type){
		super(x1, x1+sprite.getWidth(), y1, y1+sprite.getHeight(), code);
		this.text = text;
		this.type = type;
		this.sprite = sprite;
	}
	
	@Override
	public void render(float dms) {
		if (hovered){
			sprite.render(1, this, QuadColor.fullWhite, dms);
			type.getFont().renderCentered(text, this, (getHeight()/2)*0.95f, type.getHColor());
		}
		else{
			sprite.render(0, this, QuadColor.fullWhite, dms);
			type.getFont().renderCentered(text, this, (getHeight()/2), type.getNColor());
		}
	}
	
}
