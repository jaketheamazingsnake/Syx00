package gui;

import engine.QuadColor;
import sprites.SPRITEVI;

/**
 * A selectable button
 * @author mail__000
 *
 */
public class SelButt extends MultiValueA{

	protected SPRITEVI sprite;
	
	/**
	 * 
	 * @param code
	 * @param sprite
	 * @param x1
	 * @param y1
	 */
	public SelButt(int code, SPRITEVI sprite, float x1, float y1) {
		super(x1, x1+sprite.getWidth(), y1, y1+sprite.getHeight(), code, 0, 1);
		this.sprite = sprite;
	}

	@Override
	public void render(float dms) {
		if (currentValue == 1){
			sprite.render(2, this, QuadColor.fullWhite, dms);
		}
		else if (hovered){
			sprite.render(1, this, QuadColor.fullWhite, dms);
		}
		else{
			sprite.render(0, this, QuadColor.fullWhite, dms);
		}
	}
	
	public void deSelect(){
		currentValue = 0;
	}

}
