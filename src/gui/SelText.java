package gui;

/**
 * Selectable text
 * @author mail__000
 *
 */
public class SelText extends MultiValueA{

	protected String text;
	protected TextType type;
	
	/**
	 * 
	 * @param code
	 * @param text
	 * @param x1
	 * @param y1
	 * @param type
	 */
	public SelText(int code, String text, float x1, float y1, TextType type) {
		super(x1, x1 + type.getFont().getWidth(text, 
				type.getHeight()), y1, y1 + type.getHeight(), code, 0, 1);
		this.text = text;
		this.type = type;
	}

	@Override
	public void render(float dms) {
		if (currentValue == 1){
			type.getFont().renderCentered(text, this, (getHeight()/2)*0.9f, type.getSColor());
		}
		else if (hovered){
			type.getFont().renderCentered(text, this, (getHeight()/2)*0.95f, type.getHColor());
		}
		else{
			type.getFont().renderCentered(text, this, (getHeight()/2), type.getNColor());
		}
	}

	
	
	
}
