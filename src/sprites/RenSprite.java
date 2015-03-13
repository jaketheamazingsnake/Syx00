package sprites;

import engine.QuadColor;
import util.ARECTANGLE;


public class RenSprite extends ARECTANGLE implements RENDERABLE{

	private SPRITEI sprite;
	
	protected float[] currentColor = QuadColor.fullWhite;
	
	public RenSprite(SPRITEI s, float x1, float y1) {
		super(x1, x1 + s.getWidth(), y1, y1 + s.getHeight());
		this.sprite = s;
	}
	
	public void render(float dms){
		sprite.render(this, currentColor, dms);
	}

	public void setColor(float[] c){
		currentColor = c;
	}

}
