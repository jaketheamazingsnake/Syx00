package sprites;

import util.ARECTANGLE;
import engine.Renderer;

class SpriteV extends Base implements SPRITEVI{
	
	protected final float[] x;
	protected final float y;
	
	SpriteV(float pxW, float pxH, float[] coo, int variations){
		super(pxW, pxH, coo);
		x = new float [variations];
		y = coo[2]*pxH;
		
		for (int i = 0; i < variations; i++){
			x[i] = (coo[0] + coo[1]*i)*pxW; 
		}
	}
	
	@Override
	public boolean render(ARECTANGLE r, float[] color, float dms){
		Renderer.texture(r.getX1(), r.getX2(), r.getY1(), r.getY2(), x[0], x[0]+width, y, y+height, color);
		return false;
	}
	
	@Override
	public boolean render(int Nr, ARECTANGLE r, float[] color, float dms){
		Renderer.texture(r.getX1(), r.getX2(), r.getY1(), r.getY2(),  x[Nr], x[Nr]+width, y, y+height, color);
		return false;
	}
	
	@Override
	public int getVariations() {
		return x.length-1;
	}
	
}
