package sprites;

import util.ARECTANGLE;
import engine.QuadColor;

public class RenSpriteV extends ARECTANGLE implements RENDERABLE{

	private SPRITEVI sprite;
	protected int variation = 0;
	private final int max;
	
	private float[] currentColor = QuadColor.fullWhite;
	
	public RenSpriteV(SPRITEVI s, float x1, float y1) {
		super(x1, x1 + s.getWidth(), y1, y1 + s.getHeight());
		this.sprite = s;
		max = sprite.getVariations();
	}
	
	public void render(float dms){
		sprite.render(variation, this, currentColor, dms);
	}

	public void setColor(float[] c){
		currentColor = c;
	}
	
	public void incrementVariation(int amount){
		variation+= amount;
		if (variation > max)
			variation = 0;
		else if (variation < 0)
			variation = max;
	}
	
	public void setVariation(int v){
		variation = v;
		if (variation > max)
			variation = 0;
		else if (variation < 0)
			variation = max;
	}
	
}
