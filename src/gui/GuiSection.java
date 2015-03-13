package gui;

import java.util.LinkedList;

import engine.Sound;
import sprites.RENDERABLE;
import util.Vector;

public class GuiSection {
	
	private LinkedList<RENDERABLE> renderablesTemp = new LinkedList<RENDERABLE>();
	private LinkedList<CLICKABLE> clickablesTemp = new LinkedList<CLICKABLE>();
	
	private RENDERABLE previous;
	
	private RENDERABLE[] renderables = new RENDERABLE[0];
	private CLICKABLE[] clickables = new CLICKABLE[0];
	
	private Sound hover;
	private Sound click;
	
	private CLICKABLE currentlyHovered;
	
	public GuiSection(Sound hover, Sound click){
		this.hover = hover;
		this.click = click;
	}

	public void init(){
		renderables = renderablesTemp.toArray(renderables);
		clickables = clickablesTemp.toArray(clickables);
		renderablesTemp = null;
		clickablesTemp = null;
		currentlyHovered = clickables[0];
	}
	
	public boolean update(Vector mouseCoo){
		int a = currentlyHovered.hover(mouseCoo);
		
		if (a == 0)
			return true;
		if (a == 1){
			hover.play();
			return true;
		}
		
		for (CLICKABLE c : clickables){
			if (c.hover(mouseCoo) == 1){
				hover.play();
				currentlyHovered = c;
				return true;
			}
		}
		return false;
	}
	
	public int click(){
		for (CLICKABLE c : clickables){
			if (c.click()){
				click.play();
				return c.getCode();
			}
		}
		return -1;
	}
	
	public void render(float dms){
		for (RENDERABLE r : renderables)
			r.render(dms);
	}
	
	public void add(RENDERABLE r){
		renderablesTemp.add(r);
		previous = r;
	}
	
	public void add(CLICKABLE c){
		renderablesTemp.add(c);
		clickablesTemp.add(c);
		previous = c;
	}
	
	public float getX1(){
		return previous.getX1();
	}
	
	public float getX2(){
		return previous.getX2();
	}
	
	public float getY1(){
		return previous.getY1();
	}
	
	public float getY2(){
		return previous.getY2();
	}
	
}
