package gui;

import sprites.Font;
import sprites.Gui;
import engine.QuadColor;

/**
 * Gui text types
 * @author mail__000
 *
 */
public enum TextType {

	Launcher(0, 0, 0),	
	Menu(1, 1, 1),
	Game(1,1,1);
	
	private int height;
	private int font;
	private int baseColor;
	
	
	private final static float[] tHeights = new float[]{50,50};
	private final static Font[] fonts = new Font[]{Gui.fonts.BellS, Gui.fonts.BellS};
	private final static float[][] tColors = new float[][]{
		QuadColor.LauncherTextN, QuadColor.LauncherTextH, QuadColor.LauncherTextS,
		QuadColor.LauncherTextN, QuadColor.LauncherTextH, QuadColor.LauncherTextS};
	
	private TextType(int height, int font, int baseColor){
		this.height = height;
		this.font = font;
		this.baseColor = baseColor;
	}
	
	public float getHeight(){
		return tHeights[height];
	}
	
	public Font getFont(){
		return fonts[font];
	}
	
	public float[] getNColor(){
		return tColors[baseColor];
	}
	
	public float[] getHColor(){
		return tColors[baseColor+1];
	}
	
	public float[] getSColor(){
		return tColors[baseColor+2];
	}
	
}
