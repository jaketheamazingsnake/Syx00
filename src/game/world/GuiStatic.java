package game.world;

import engine.QuadColor;
import sprites.Gui;
import sprites.RenSprite;
import util.Vector;
import game.Resources;
import gui.CLICKABLE;
import gui.ClButt;
import gui.SelButt;

class GuiStatic {

	private final RenSprite compass;

	
	private final CLICKABLE[] basics;
	private final int build = 0;
	private final int road = 1;
	private final int expedition = 2;
	private final int military = 3;
	private final CLICKABLE cancel;
	
	private int pinI = -1;
	private int subI = -1;
	private final SelButt[][] subs;
	
	GuiStatic(Resources res){
		
		
		basics = new CLICKABLE[4];
		subs = new SelButt[4][];
		
		final float bSize = Gui.sprites.world_button_cancel.getWidth();
		final float startX = res.screenWidth - bSize*5.5f;
		final float startY = res.screenHeight - bSize*1.5f;
		float x = startX;
		float y = startY;
		int i = 0;
		
		compass = new RenSprite(Gui.sprites.world_compass, res.screenWidth - Gui.sprites.world_compass.getWidth() - bSize/2, bSize/2);
		
		basics[i++] = new ClButt(build, Gui.sprites.world_button_build, x, y);
		x+= Gui.sprites.world_button_cancel.getWidth();
		basics[i++] = new ClButt(road, Gui.sprites.world_button_road, x, y);
		x+= Gui.sprites.world_button_cancel.getWidth();
		basics[i++] = new ClButt(expedition, Gui.sprites.world_button_expedition, x, y);
		x+= Gui.sprites.world_button_cancel.getWidth();
		basics[i++] = new ClButt(military, Gui.sprites.world_button_military, x, y);
		x+= Gui.sprites.world_button_cancel.getWidth();
		cancel = new ClButt(-10, Gui.sprites.world_button_cancel, x, y);
		
		subs[build] = new SelButt[4];
		i = 0;
		x = startX;
		
		y -= bSize;
		subs[build][i] = new SelButt(i++, Gui.sprites.world_button_build_hunt, x, y);
		y-= bSize;
		subs[build][i] = new SelButt(i++, Gui.sprites.world_button_build_wood, x, y);
		y-= bSize;
		subs[build][i] = new SelButt(i++, Gui.sprites.world_button_build_mine, x, y);
		y-= bSize;
		subs[build][i] = new SelButt(i++, Gui.sprites.world_button_build_settlement, x, y);

		subs[road] = new SelButt[3];
		i = 0;
		x += bSize;
		y = startY;
		
		y -= bSize;
		subs[road][i] = new SelButt(i++, Gui.sprites.world_button_road_path, x, y);
		y-= bSize;
		subs[road][i] = new SelButt(i++, Gui.sprites.world_button_road_paved, x, y);
		y-= bSize;
		subs[road][i] = new SelButt(i++, Gui.sprites.world_button_road_highway, x, y);
		
		subs[expedition] = new SelButt[1];
		i = 0;
		x += bSize;
		y = startY;
		
		y -= bSize;
		subs[expedition][i] = new SelButt(i++, Gui.sprites.world_button_expedition_ph, x, y);
		
		subs[military] = new SelButt[1];
		i = 0;
		x += bSize;
		y = startY;
		
		y -= bSize;
		subs[military][i] = new SelButt(i++, Gui.sprites.world_button_military_ph, x, y);

	}
	
	void render(float dms){
		compass.render(dms);
		Gui.fonts.BellS.renderCentered("Hanarion\n1678 I.C.", compass, 30, QuadColor.fullWhite);
		for (CLICKABLE c: basics){
			c.render(dms);
		}
		cancel.render(dms);
		if (subI == -1 && pinI == -1)
			return;
		for (SelButt s: subs[subI]){
			s.render(dms);
		}
		
	}
	
	boolean hover(Vector mCoo){
		
		boolean ishovered = false;
		
		if (cancel.hover(mCoo) > -1)
			return true;
		
		if (pinI > -1){
			for (SelButt s: subs[subI]){
				if (s.hover(mCoo) > -1){
					ishovered = true;
				}
			}
			return ishovered;
		}
		
		if (subI > -1){
			for (SelButt s: subs[subI]){
				if (s.hover(mCoo) > -1)
					ishovered = true;
			}
			if (ishovered)
				return true;
		}
		
		subI = -1;
		for (int i = 0; i < basics.length; i++){
			if (basics[i].hover(mCoo) > -1){
				ishovered = true;
				subI = basics[i].getCode();
			}
		}
		
		return ishovered;
	}
	
	boolean click(){
		
		if (cancel.click()){
			return true;
		}
		
		if (subI == -1)
			return false;
		
		for (SelButt s: subs[subI]){
			if (s.click()){
				if (pinI == s.getCode()){
					pinI = -1;
					return false;
				}
				if (pinI > -1)
					subs[subI][pinI].deSelect();
				pinI = s.getCode();	
				break;
			}
		}

		return false;
	}
	
}
