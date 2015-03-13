package engine;

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

public class Tex {
	
	private Texture texture;
	/**
	 * renders all the stored vertices of this texture
	 * @param near
	 */
	public void flush(){
		glEnd();
	}
	
	public float getWidth(){
		return texture.getTextureWidth();
	}
	
	public float getHeight(){
		return texture.getTextureHeight();
	}
	
	/**
	 * binds the texture
	 */
	public void bind(){
		texture.bind();
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glBegin(GL_TRIANGLES);
	}
	
	public void release(){
		texture.release();
	}
	
	/**
	 * 
	 * @param path the path to the png image
	 */
	public Tex(String path){
		try {
			File f = new File(path);
			if(!f.exists() || f.isDirectory())
				throw new IOException("file doesn't exist: " + path);
			texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("failed to load texture" + path);
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
