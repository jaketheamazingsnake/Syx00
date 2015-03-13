package sprites;

import util.ARECTANGLE;
import gui.TextType;

/**
 * REnderable text that is changeable and centered within itself.
 * @author mail__000
 *
 */
public class RenTextCentered extends ARECTANGLE implements RENDERABLE{

	private String text;
	private final Font font;
	private float[] color;
	private float textHeight;
	
	public RenTextCentered(String text, float x1, float x2, float y1, float y2, TextType type) {
		this(text, x1, x2, y1, y2, type.getFont(), type.getHeight(), type.getNColor());
	}
	
	public RenTextCentered(String text, float x1, float x2, float y1, float y2, Font font, float textHeight, float[] color) {
		super(x1, x2, y1, y2);
		this.text = text;
		this.font = font;
		this.color = color;
		this.textHeight = textHeight;
	}

	@Override
	public void render(float dms) {
		font.renderCentered(text, this, textHeight, color);
	}
	
	public void setText(String text){
		this.text = text;
	}
	
}
