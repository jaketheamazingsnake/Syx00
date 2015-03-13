package menu;

import java.util.Random;

import menu.MenuCore.UpdateSheet;
import engine.GFX;
import sprites.BigImage;
import sprites.RENDERABLE;
import sprites.RenSprite;
import sprites.RenSpriteV;
import util.Direction;
import util.Rectangle;

class ScreenMainBackground {

	private final Resources res;
	private BigImage.Slice wall;
	private Random rnd = new Random();
	private final RenSpriteV pillars;
	private final RenSpriteV pillars2;
	private final RenSpriteV stalagmites1;
	private final RenSpriteV stalagmites2;
	private final RenSprite brokenp;
	private final RenSprite skelleton;
	private RENDERABLE pill1;
	private RENDERABLE pill2;
	
	ScreenMainBackground(Resources res, Rectangle bounds){
		pillars = new RenSpriteV(res.misk.sprites.menu_pillars, 0, 0);
		pillars2 = new RenSpriteV(res.misk.sprites.menu_pillars, 0, 0);
		pillars2.incrementVariation(2);
		stalagmites1 = new RenSpriteV(res.misk.sprites.menu_stalagmites, 0, 0);
		stalagmites2 = new RenSpriteV(res.misk.sprites.menu_stalagmites2, 0, 0);
		brokenp = new RenSprite(res.misk.sprites.menu_brokenPillar, 0, 0);
		skelleton = new RenSprite(res.misk.sprites.menu_skeleton_pillar, 0, 0);
		pill1 = pillars;
		pill2 = pillars2;
		reinit(res, bounds);
		this.res = res;
	}
	
	void reinit(Resources res, Rectangle bounds){
		wall = res.wall.getSlice(0, bounds.getX2(), 0, 512);
		wall.centerIn(bounds, Direction.C);
		
		pillars.moveY1(wall.getY1());
		pillars.moveX1(wall.getX2());
		pillars2.moveY1(wall.getY1());
		pillars2.moveX2(wall.getCentreX());
		stalagmites1.moveY1(wall.getY1());
		stalagmites2.moveY2(wall.getY2());
		brokenp.moveY2(wall.getY2());
		skelleton.moveY2(wall.getY2());
	}
	
	void update(UpdateSheet s){
		wall.texMove(35*s.ds(), 0);
		pill1.incrementX(-100*s.ds());
		pill2.incrementX(-100*s.ds());
		if (!pill1.touch(wall)){
			if (rnd.nextBoolean())
				pill1 = skelleton;
			else
				pill1 = pillars;
			pill1.moveX1(wall.getX2());
		}
		if (!pill2.touch(wall)){
			if (rnd.nextBoolean())
				pill2 = brokenp;
			else
				pill2 = pillars2;
			pill2.moveX1(wall.getX2());
		}
		
	}
	
	void render(float dms){
		
		res.wall.bind();
		wall.render();
		res.wall.flush();
		res.misk.bind();
		pill1.render(dms);
		pill2.render(dms);
		res.misk.flush();
		GFX.drawVignette(wall, rnd.nextInt(5)+128);
	}
	
	float getY1(){
		return wall.getY1();
	}
	
	float getY2(){
		return wall.getY2();
	}
	
}
