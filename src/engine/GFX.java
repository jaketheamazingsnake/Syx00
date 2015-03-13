package engine;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import sprites.Gui;
import util.ARECTANGLE;

/**
 * subject for deletion
 * @author mail__000
 *
 */
public class GFX {
	
	//public final FontHandler Text = new FontHandler();
	
	private GFX () {
	}
	
	public static void ColorFilter(boolean red, boolean blue, boolean green, boolean alpha){
		glColorMask(red, blue, green, alpha);
	}
	
	public static void ColorFilter(){
		glColorMask(true, true, true, true);
	}
	
	public static void drawLoadingText(String text){
		GlMaster.clearScreen();
		Gui.bind();
		Gui.fonts.BellS.render(text, 20, 20);
		Gui.flush();
		GlMaster.updateDisplayAndSound();
	}
	
	/**
	 * renders a filled rectangle.
	 * @param rec the bounds
	 * @param color The Color 
	 */
	public static void drawRectangle(ARECTANGLE rec, float[] color){
		
		glDisable(GL11.GL_TEXTURE_2D);
			
		GL11.glBegin(GL11.GL_QUADS);
			glColor4f(color[0], color[1], color[2], color[3]);
			GL11.glVertex2f(rec.getX1(),rec.getY1());
			glColor4f(color[4], color[5], color[6], color[7]);
			GL11.glVertex2f(rec.getX2(),rec.getY1());
			glColor4f(color[8], color[9], color[10], color[11]);
			GL11.glVertex2f(rec.getX2(),rec.getY2());
			glColor4f(color[12], color[13], color[14], color[15]);
			GL11.glVertex2f(rec.getX1(),rec.getY2());
		GL11.glEnd();
		
		glEnable(GL11.GL_TEXTURE_2D);
 
		glColor4f(1f,1f,1f,1f);
	}
	
	/**
	 * 
	 * @param amount 0-1 (1 = pitch black)
	 */
	public static void darkenScreen(float amount){
		
		glDisable(GL11.GL_TEXTURE_2D);
		glColor4f(0f,0f,0f,amount);
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2f(0,0);
			GL11.glVertex2f(Display.getWidth(),0);
			GL11.glVertex2f(Display.getWidth(),Display.getHeight());
			GL11.glVertex2f(0,Display.getHeight());
		GL11.glEnd();
		
		glEnable(GL11.GL_TEXTURE_2D);
 
		glColor4f(1f,1f,1f,1f);
	}
	
	/**
	 * draws a vignette along the upper and lower bounds
	 * @param rec
	 * @param height the width of the vignette
	 */
	public static void drawVignette(ARECTANGLE rec, int height){
		
		glDisable(GL11.GL_TEXTURE_2D);
		
		//upper part1
		GL11.glBegin(GL11.GL_QUADS);
			glColor4f(0f,0f,0f,1f);
			GL11.glVertex2f(rec.getX1(),rec.getY1());
			GL11.glVertex2f(rec.getX2(),rec.getY1());
			glColor4f(0f,0f,0f,0f);
			GL11.glVertex2f(rec.getX2(),rec.getY1()+height);
			GL11.glVertex2f(rec.getX1(),rec.getY1()+height);
		
		//lover  part1

			glColor4f(0f,0f,0f,0f);
			GL11.glVertex2f(rec.getX1(),rec.getY2()-height);
			GL11.glVertex2f(rec.getX2(),rec.getY2()-height);
			glColor4f(0f,0f,0f,1f);
			GL11.glVertex2f(rec.getX2(),rec.getY2());
			GL11.glVertex2f(rec.getX1(),rec.getY2());
		
		//upper part2
			glColor4f(0f,0f,0f,1f);
			GL11.glVertex2f(rec.getX1(),rec.getY1());
			GL11.glVertex2f(rec.getX2(),rec.getY1());
			glColor4f(0f,0f,0f,0f);
			GL11.glVertex2f(rec.getX2(),rec.getY1()+height*2);
			GL11.glVertex2f(rec.getX1(),rec.getY1()+height*2);
		
		//lover  part2
			glColor4f(0f,0f,0f,0f);
			GL11.glVertex2f(rec.getX1(),rec.getY2()-height*2);
			GL11.glVertex2f(rec.getX2(),rec.getY2()-height*2);
			glColor4f(0f,0f,0f,1f);
			GL11.glVertex2f(rec.getX2(),rec.getY2());
			GL11.glVertex2f(rec.getX1(),rec.getY2());
		GL11.glEnd();
		
		glEnable(GL11.GL_TEXTURE_2D);
	}
	
}