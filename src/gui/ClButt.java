package gui;

import engine.QuadColor;
import sprites.SPRITEVI;

/**
 * A clickable button
 * @author mail__000
 *
 */
public class ClButt extends ClickableA{

	protected SPRITEVI sprite;
	
	public ClButt(int code, SPRITEVI sprite, float x1, float y1){
		super(x1, x1+sprite.getWidth(), y1, y1+sprite.getHeight(), code);
			this.sprite = sprite;
	}

	@Override
	public void render(float dms) {
		if (hovered)
			sprite.render(1, this, QuadColor.fullWhite, dms);
		else
			sprite.render(0, this, QuadColor.fullWhite, dms);
	}

}
