package engine;

import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2d;
import static org.lwjgl.opengl.GL11.glVertex2f;

public class Renderer {

	public static void texture(float qx1, float qx2, float qy1, float qy2, 
			float tx1, float tx2, float ty1, float ty2, float[] color){
		glColor4f(color[0],color[1],color[2],color[3]);	
		glTexCoord2f(tx1, ty1);	
		glVertex2f(qx1,qy1);
		
		glColor4f(color[4],color[5],color[6],color[7]);	
		glTexCoord2f(tx2, ty1);	
		glVertex2f(qx2, qy1);
		
		glColor4f(color[8],color[9],color[10],color[11]);		
		glTexCoord2f(tx2, ty2);
		glVertex2d(qx2, qy2);
		
		glColor4f(color[8],color[9],color[10],color[11]);		
		glTexCoord2f(tx2, ty2);
		glVertex2d(qx2, qy2);
		
		glColor4f(color[12],color[13],color[14],color[15]);	
		glTexCoord2f(tx1, ty2);
		glVertex2f(qx1, qy2);
		
		glColor4f(color[0],color[1],color[2],color[3]);	
		glTexCoord2f(tx1, ty1);	
		glVertex2f(qx1,qy1);
		
	}
	
}
