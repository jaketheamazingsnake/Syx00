package sprites;

import data.CONST;

class Base {

	protected final float gameWidth;
	protected final float gameHeight;
	protected final float width;
	protected final float height;
	
	Base(float pxW, float pxH, float[] coo){
		gameWidth = coo[1]*CONST.SCALE;
		gameHeight = coo[3]*CONST.SCALE;
		width = coo[1]*pxW;
		height = coo[3]*pxW;
	}
	
	public float getWidth(){
		return gameWidth;
	}
	
	public float getHeight(){
		return gameHeight;
	}
	
}
