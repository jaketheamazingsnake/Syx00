package gui;

public abstract class MultiValueA extends ClickableA {

	protected int currentValue;
	private final int maxValue;
	
	MultiValueA(float x1, float x2, float y1, float y2, int code, int currentValue, int maxValue) {
		super(x1, x2, y1, y2, code);
		this.currentValue = currentValue;
		this.maxValue = maxValue;
	}

	@Override
	public boolean click() {
		if (hovered){
			incrementValue(1);
			return true;
		}
		return false;
	}
	
	protected void incrementValue(int amount){
		currentValue += amount;
		if (currentValue > maxValue)
			currentValue = 0;
		else if (currentValue < 0)
			currentValue = maxValue;
	}
	
	public Observer getObserver(){
		return new Observer();
	}
	
	public class Observer{
		
		private Observer(){
		}
		
		public void setCurrentValue(int value) {
			currentValue = value;
		}

		public void setCode(int newCode) {
			code = newCode;
		}

		public int getCurrentValue() {
			return currentValue;
		}

		public int getCode() {
			return code;
		}

	}
	
}
