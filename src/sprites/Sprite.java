package sprites;

import engine.Renderer;
import util.ARECTANGLE;

class Sprite extends Base implements SPRITEI{

	protected final float x;
	protected final float y;
	
	public Sprite(float pxW, float pxH, float[] coo){
		super(pxW, pxH, coo);
		x = coo[0]*pxW;
		y = coo[2]*pxH;
	}
	
	@Override
	public boolean render(ARECTANGLE r, float[] color, float dms){
		Renderer.texture(r.getX1(), r.getX2(), r.getY1(), r.getY2(), x, x+width, y, y+height, color);
		return false;
	}
	
}
