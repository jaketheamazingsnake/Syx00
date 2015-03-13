package sprites;

import data.Paths;
import engine.Tex;

public class Gui {

	private static final Tex texture = new Tex(Paths.SpriteSheets().Gui);
	private static final float pxH = 1/texture.getHeight();
	private static final float pxW = 1/texture.getWidth();
	
	public final static GuiSprites sprites = new GuiSprites(pxW, pxH);
	public final static Fonts fonts = new Fonts(pxW, pxH);
	
	/**
	 * renders all the stored vertices of this texture
	 * @param near
	 */
	public static void flush(){
		texture.flush();
	}
	
	/**
	 * binds the texture
	 */
	public static void bind(){
		texture.bind();
	}
	
	public static void release(){
		texture.release();
	}	
		
}
