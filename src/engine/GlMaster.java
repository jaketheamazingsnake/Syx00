package engine;

import static org.lwjgl.opengl.GL11.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.openal.AL;
import org.newdawn.slick.openal.SoundStore;

import data.CONST;
import data.Paths;
import data.Paths.ImData;

/**
 * Handles all that has to do with the display and setup of openGL
 * @author mail__000
 *
 */
public class GlMaster {
	
	/**
	 * minimum width of the screen
	 */
	public static final int MIN_WIDTH = 160*8;
	/**
	 * minimum height of the game
	 */
	public static final int MIN_HEIGHT = 100*8;
	
	GlMaster(){
		
	}
	
	void createDisplay(String title, DisplayMode dm, boolean fullscreen){
		try {
			Display.setTitle(title);
			Display.setDisplayMode(dm);
			Display.setFullscreen(fullscreen);
			if (!Display.isCreated()){
				Display.create();
				ByteBuffer[] icons = new ByteBuffer[2];
			    icons[0] = loadIcon(Paths.Engine().systemIcon16x16);
			    icons[1] = loadIcon(Paths.Engine().systemIcon32x32);
			    Display.setIcon(icons);
			}
		} catch (LWJGLException e) {
			System.out.println("error when setting dm in glManager");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("error when setting dm in glManager");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		initGl(dm.getWidth(),dm.getHeight());
		
		//set Icon
		try {
		    ByteBuffer[] icons = new ByteBuffer[2];
		    icons[0] = loadIcon(Paths.Engine().systemIcon16x16);
		    icons[1] = loadIcon(Paths.Engine().systemIcon32x32);
		    Display.setIcon(icons);
		} catch (IOException ex) {
		    ex.printStackTrace();
		}
		
		//to system tray (useless, but might give clues
//		try {
//			SystemTray.getSystemTray().add(new TrayIcon(Toolkit.getDefaultToolkit().createImage("res/engine/icon16x16.png")));
//		} catch (AWTException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
	
	
	
	private void initGl (int width, int height) {
		
		glClearDepth(1);
		
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		glViewport(0,0,width,height);
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, width, height, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glClearColor(0, 0, 0, 0);
			
	}	
	
	private ByteBuffer loadIcon(ImData id) throws IOException {
	    BufferedImage image = ImageIO.read(new File(id.getPath())); // load image

	    // convert image to byte array
	    byte[] imageBytes = new byte[id.getWidth() * id.getHeight() * 4];
	    for (int i = 0; i < id.getHeight(); i++) {
	        for (int j = 0; j < id.getWidth(); j++) {
	            int pixel = image.getRGB(j, i);
	            for (int k = 0; k < 3; k++) // red, green, blue
	                imageBytes[(i*16+j)*4 + k] = (byte)(((pixel>>(2-k)*8))&255);
	            imageBytes[(i*16+j)*4 + 3] = (byte)(((pixel>>(3)*8))&255); // alpha
	        }
	    }
	    return ByteBuffer.wrap(imageBytes);
	}
	
	public void cleanup(){
		AL.destroy();
		try {
			Display.releaseContext();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Display.destroy();
	}
	
	public static DisplayMode[] getDisplayModes(){
		try {
			int count = 0;
			for (DisplayMode dm : Display.getAvailableDisplayModes())
				if (dm.getWidth() >= CONST.MIN_WIDTH && dm.getHeight() >= CONST.MIN_HEIGHT)
					count++;
			
			final DisplayMode[] result = new DisplayMode[count];
			
			count = 0;
			
			for (DisplayMode dm : Display.getAvailableDisplayModes()){
				if (dm.getWidth() >= CONST.MIN_WIDTH && dm.getHeight() >= CONST.MIN_HEIGHT){
					result[count] = dm;
					count++;
				}
			}
					
			return result;
			
		} catch (LWJGLException e) {
			System.out.println("couldn't get displaymodes from display in GLManager");
			e.printStackTrace();
		}
		return null;
	}
	
	public static void clearScreen(){
		glClear(GL_COLOR_BUFFER_BIT);
	}
	
	public static boolean updateDisplayAndSound(){
		Display.update();
		SoundStore.get().poll(0);
		return Display.isCloseRequested();
	}
	
	public void setSettings(PSettings sett){
		createDisplay("Game", getDisplayModes()[sett.getValue(PSettings.SETT.DISPLAYMODE)], sett.getBooValue(PSettings.SETT.FULLSCREEN));
		Display.setVSyncEnabled(sett.getBooValue(PSettings.SETT.VSYNCH));
	}
	
	public static int getWidth(){
		return Display.getWidth();
	}
	
	public static int getHeight(){
		return Display.getHeight();
	}
	
}
