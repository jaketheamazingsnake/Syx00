package sprites;

import util.ARECTANGLE;
import util.Rectangle;
import data.CONST;
import engine.QuadColor;
import engine.Renderer;

/**
 * I will change here... I will make it so that I only add width. Yes, you heard me! Glyphs will be packed edge to
 * edge on the sp. some glyphs will have offsets in the other array.
 * the final array will be calculated in the constructor. 
 * @author mail__000
 *
 */
public class Font {
	
	private char ascii;
	private float scale;
	private int renderedI;
	private int tempCount;
	private final float[] texX1;
	private final float[] texX2;
	private final float texY1;
	private final float texY2;
	private final float height;
	private final byte[] quadWidths;
	private final byte[] quadXoffsets;
	private final int Yoffset;
	
	/**
	 * 
	 * @param pxW
	 * @param pxH
	 * @param startX
	 * @param startY
	 * @param height
	 * @param Yoffset
	 * @param quadWidthOffset
	 */
	Font (float pxW, float pxH, int startX, int startY, int height, int Yoffset,
			byte[] quadWidthOffset){
		
		quadXoffsets = new byte[96];
		quadWidths = new byte[96];
		texX1 = new float[96];
		texX2 = new float[96];
		
		float tempX1 = startX*pxW;
		 texY1 = startY*pxH;
		 texY2 = (startY+height)*pxH;
		
		int j = 0;
		for (int i = 0; i < quadWidthOffset.length; i+=2){
			texX1[j] = tempX1;
			texX2[j] = tempX1 += (quadWidthOffset[i]*pxW);
			tempX1+= pxW;
			quadWidths[j] = quadWidthOffset[i]; 
			quadXoffsets[j] = quadWidthOffset[i+1];
			j++;
		}
		
		this.Yoffset = height - Yoffset;
		
		this.height = (float) height;
		
	}
	
	private int renderPart(String string, float X1, float Y1, int i1, int i2, float[] color){
		
		for (int i = i1; i < i2; i++){
			ascii = (char) (string.charAt(i) -32);
			if (ascii >= 0 && ascii <= 96){
				Renderer.texture(X1 += quadXoffsets[ascii]*scale, X1 += quadWidths[ascii]*scale, Y1, Y1+height*scale, 
						texX1[ascii], texX2[ascii], texY1, texY2, color);
				X1 += 1;
			}
			
			else if (ascii == -1){
				return i;
			}
			
		}
		return i2;
	}
	
	/**
	 * 
	 * @param string
	 * @param textHeight
	 * @return the width of the longest line of the string.
	 */
	public int getWidth(String string, float textHeight){
		return getWidth(string, textHeight, 0, string.length());
	}
	
	private int getWidth(String string, float textHeight, int s1, int s2){
		
		scale = textHeight/height;
		
		int temp = 0;
		int result = 0;
		
		for (int i = s1; i < s2; i++){
			ascii = string.charAt(i);
			if (ascii > 31 && ascii < 128){
				temp += quadXoffsets[ascii-32]*scale;
				temp += quadWidths[ascii-32]*scale;
			}
			
			else if (ascii == 10){
				if (temp > result){
				result = temp;
				temp = 0;
				}
			}
			
		}
		
		if (temp > result)
			return temp;
		return result;
		
	}
	
	/**
	 * render a string at given coordinate with default size
	 * @param string The string 
	 * @param f coo x1 
	 * @param g coo y1
	 */
	public void render(String string, float X, float Y){
		renderSpec(string, X, Y, (int) (height*CONST.SCALE), QuadColor.fullWhite);
	}
	
	/**
	 * Render string more specific
	 * @param string
	 * @param X
	 * @param Y
	 * @param textHeight
	 * @param font
	 */
	public void renderSpec(String string, float X, float Y, float textHeight, float[] color){
		scale = textHeight/height;
		int i = 0;
		
		while (i < string.length()){
			i = renderPart(string, X, Y, i, string.length(), color);
			Y += textHeight;
			i++;
		}
		
	}
	
	/**
	 * Render String clamped to Y1 
	 * @param string
	 * @param X1
	 * @param Y1
	 * @param textHeight
	 * @param font
	 */
	public void renderAtLine(String string, int X1, int Y1, float textHeight, float[] color){
		scale = textHeight/height;
		renderSpec(string, X1, (int) (Y1 - (Yoffset*scale)), textHeight, color);
	}
	
	/**
	 * REnder the string centered in a box.
	 * 
	 * @param string
	 * @param bounds
	 * @param textHeight
	 * @param font
	 */
	public void renderCentered(String string, ARECTANGLE bounds, float textHeight, float[] color){
		scale = textHeight/height;
		float Y1;
		float X1 = bounds.getX1();
		
		//calc center Y
		tempCount = 1;
		renderedI = 0;
		
		for (int i = 0; i < string.length(); i++){
			ascii = string.charAt(i);
			if (ascii == 10)
				tempCount ++;
			else if (ascii < 32 || ascii > 127)
				return;
		}
		
		Y1 = bounds.getY1() + (bounds.getHeight() - tempCount*textHeight)/2;
		
		//calc center X
		
		for (int i = 0; i < string.length(); i++){
			ascii = string.charAt(i);
			if (ascii == 10){
				X1 += (bounds.getWidth()-tempCount)/2;
				renderPart(string, X1, Y1, renderedI, i, color);
				renderedI = i +1;
				Y1 += height*scale;
				X1 = bounds.getX1();
				tempCount = 0;
				
			}else
			tempCount += (quadXoffsets[ascii-32] + quadWidths[ascii-32])*scale + 1;
		}
		
		X1 += (bounds.getWidth()-tempCount)/2;
		
		renderPart(string, X1, Y1, renderedI, string.length(), color);
	}
	
	/**
	 * renders string in a box
	 * @param string
	 * @param bounds
	 * @param textHeight
	 * @param font
	 * @return the string that didn't fit in the box
	 */
	public String renderBoxed(String string, Rectangle bounds, float textHeight, float[] color){
		
		int wordlength = 0;
		int lineLength = 0;
		tempCount = 0;
		renderedI = 0;
		scale = textHeight/height;
		float Y1 = bounds.getY1();
		float X1 = bounds.getX1();
		
		for (int i = 0; i < string.length(); i++){
				
			ascii = string.charAt(i);
			
			//if text is out of Y bounds
			if (Y1+textHeight > bounds.getY2()){
				return string.substring(renderedI, string.length());
			}
			
			//if normal symbol
			if (ascii > 32 && ascii < 128){
				wordlength += (quadXoffsets[ascii-32]*scale + quadWidths[ascii-32]*scale);
			}
				
			//if space
			else if (ascii == 32){
				if ((lineLength + wordlength) <= bounds.getWidth()){
					lineLength +=  wordlength + (quadXoffsets[ascii-32]*scale + quadWidths[ascii-32]*scale);
					tempCount = i;
					
				}
				else{
					renderedI = renderPart(string, X1, Y1, renderedI, tempCount, color) +1;
					Y1 += height*scale;
					X1 = bounds.getX1();
					lineLength = wordlength;
				}
				wordlength = 0;
			}
			
			//if enter
			else if (ascii == 10){
				renderPart(string, X1, Y1, renderedI, i, color);
				renderedI = i+1;
				Y1 += height*scale;
				X1 = bounds.getX1();
				lineLength = 0;
				wordlength = 0;
			}
			
			//string contains crappy chars
			else {
				return "your string is popo";
			}
		
		}
		
		renderPart(string, X1, Y1, renderedI, string.length(), color);
		
		return "";
			
	}
	
	
}
