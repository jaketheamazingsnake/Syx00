package sprites;

import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexParameteri;

import java.io.File;
import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import util.Rectangle;
import util.ARECTANGLE;
import util.Vector;
import data.CONST;
import engine.QuadColor;
import engine.Renderer;

/**
 * A grafic component that lets you create a sprite, which you specify the possition on the texture (in game pixels)
 * and the possition of the quad.
 * @author jake
 *
 */
public class BigImage {

	private  Texture texture;
	private  float pxH;
	private  float pxW;
	private  float width = 5;
	private  float height;
	private final float[] white = QuadColor.fullWhite;
	
	/**
	 * 
	 * @param path the path to the png texture
	 */
	public BigImage(String path){
		try {
			File f = new File(path);
			if(!f.exists() || f.isDirectory())
				throw new IOException("file doesn't exist: " + path);
			texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(path));
			width = texture.getTextureWidth()*CONST.SCALE;
			height = texture.getTextureHeight()*CONST.SCALE;
			pxH = 1f/height;
			pxW = 1f/width;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("failed to load texture" + path);
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * get a slice of the texture and render it to your heart's desire
	 * @param imbounds
	 * @param quadBounds
	 * @return
	 */
	public Slice getSlice(Rectangle imbounds){
		return new Slice(imbounds.getX1(), imbounds.getWidth(), imbounds.getY1(), imbounds.getWidth()); 
	}
	
	public Slice getSlice(float x1, float width, float y1, float height){
		return new Slice(x1, width, y1, height); 
	}
	
	/**
	 * 
	 * @return the in-game width of the texture
	 */
	public float getWidth(){
		return width;
	}
	
	/**
	 * 
	 * @return the in-game height of the texture
	 */
	public float getHeight(){
		return height;
	}
	
	public void bind(){
		texture.bind();
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glBegin(GL_TRIANGLES);
	}
	
	/**
	 * flush all that has been rendered.
	 */
	public void flush(){
		glEnd();
	}
	
	public void release(){
		texture.release();
	}
	
	/**
	 * a piece of the bigImage.
	 * @author jake
	 *
	 */
	public class Slice extends ARECTANGLE{
		
		private float x;
		private float y;
		private final float width;
		private final float height;
		
		private Slice(float x1, float width, float y1, float height){
			super(x1, x1 + width, y1, y1+height);
			x = x1*pxW;
			this.width = (x1+width)*pxW;
			y = y1*pxH;
			this.height = (y1+height)*pxH;
		}
		
		/**
		 * move the texture in in-game pixels
		 * @param v
		 * @return true if the texture is out of bounds
		 */
		public boolean texMove(Vector v){
			return texMove(v.getX(), v.getY());
		}
		
		/**
		 * move the texture in in-game pixels
		 * @param x
		 * @param y
		 * @return true if the texture is out of bounds
		 */
		public boolean texMove(float x, float y){
			this.x += x*pxW;
			this.y += y*pxH;
			
			if (x <= 0 || x+width >= 1f || y <= 0 || y+height >= 1)
				return true;
			return false;
			
		}
		
		public float texGetX1(){
			return x/pxW;
		}
		
		public float texGetX2(){
			return (x+width)/pxW;
		}
		
		public float texGetY1(){
			return y/pxH;
		}
		
		public float texGetY2(){
			return (y+height)/pxH;
		}
		
		public void render(){
			Renderer.texture(getX1(), getX2(), getY1(), getY2(), x, x+width, y, y+height, white);
		}
		
		
	}
	
}
