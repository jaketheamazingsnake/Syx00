package gui;

import util.ARECTANGLE;
import util.Vector;

abstract class ClickableA extends ARECTANGLE implements CLICKABLE{

	protected boolean hovered;
	protected int code;
	
	ClickableA(float x1, float x2, float y1, float y2, int code) {
		super(x1, x2, y1, y2);
		this.code = code;
	}

	@Override
	public int hover(Vector mouseCoo){
		if (mouseCoo.isWithin(this)){
			if (!hovered){
				hovered = true;
				return 1;
			}
			hovered = true;
			return 0;
		}else{
			if (hovered)
			hovered = false;
		}
		return -1;
	}
	
	@Override
	public boolean click() {
		if (hovered){
			return true;
		}
		return false;
	}
	
	@Override
	public int getCode(){
		return code;
	}
}
