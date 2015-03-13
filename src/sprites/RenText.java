package sprites;

import gui.TextType;
import util.ARECTANGLE;

public class RenText extends ARECTANGLE implements RENDERABLE{

	private final String text;
	private final Font font;
	private float[] color;
	
	public RenText(String text, float x1, float y1, TextType type) {
		this(text, x1, y1, type.getFont(), type.getHeight(), type.getNColor());
		
	}
	
	public RenText(String text, float x1, float y1, Font font, float textHeight, float[] color) {
		super(x1, x1 + font.getWidth(text, textHeight), y1, y1 + textHeight);
		this.text = text;
		this.font = font;
		this.color = color;
	}

	@Override
	public void render(float dms) {
		font.renderSpec(text, getX1(), getY2(), getHeight(), color);
	}

}
