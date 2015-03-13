package gui;

/**
 * A clickable text
 * @author mail__000
 *
 */
public class ClText extends ClickableA{

	private final String text;
	private final TextType type;
	
	public ClText(int code, String text, float x1, float y1, TextType type) {
		super(x1, x1 + type.getFont().getWidth(text, type.getHeight()), y1, y1 + type.getHeight(), code);
		this.text = text;
		this.type = type;
	}

	@Override
	public void render(float dms) {
		if (hovered)
			type.getFont().renderCentered(text, this, getHeight()*1.10f, type.getHColor());
		else
			type.getFont().renderCentered(text, this, getHeight(), type.getNColor());
	}

}
