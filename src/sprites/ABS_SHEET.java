package sprites;

import engine.Tex;

/**
 * A spritesheet
 * @author mail__000
 *
 */
abstract class ABS_SHEET {

	protected final Tex texture;
	protected final float pxH;
	protected final float pxW;
	/**
	 * renders all the stored vertices of this texture
	 * @param near
	 */
	public void flush(){
		texture.flush();
	}
	
	/**
	 * binds the texture
	 */
	public void bind(){
		texture.bind();
	}
	
	public void release(){
		texture.release();
	}
	
	/**
	 * 
	 * @param path the path to the png image
	 */
	ABS_SHEET(String path){
		texture = new Tex(path);
		pxH = 1/texture.getHeight();
		pxW = 1/texture.getWidth();
	}
	
}
