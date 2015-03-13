package game.world;


import data.CONST;
import engine.QuadColor;
import game.GameInfo;
import gui.TextType;
import sprites.Gui;
import sprites.RenSprite;
import sprites.RenSpriteV;
import sprites.RenTextCentered;
import util.ARECTANGLE;
import util.Direction;
import util.RECTANGLEBLE;
import util.Vector;

class GUI_TilePanel {

	private final RenSpriteV area;
	
	private final TileMap.Observer obs;
	
	private final RenSprite domainPanel;
	private String domainText;
	
	
	GUI_TilePanel(TileMap.Observer obs){
		this.obs = obs;
		
		domainPanel = new RenSprite(Gui.sprites.world_domain_panel, 0, 0);
		area = new RenSpriteV(Gui.sprites.tile_filter, 0, 0);
		area.scale(3, 3);
	}
	
	void hover(GameInfo nfo, Vector gMouse, RECTANGLEBLE wW){
		
		domainPanel.centerAt(nfo.mouseCoo());
		domainPanel.incrementY(100);
		domainText = obs.getName() +"\n" + Integer.toString((int) gMouse.getX()/Constants.GAME_TILE_SIZE) + ", " + Integer.toString((int) gMouse.getY()/Constants.GAME_TILE_SIZE);
		
//		if (nfo.mouseCoo().getX()< nfo.SWidth()/2){
//			moveX1(nfo.mouseCoo().getX()+16*CONST.SCALE);
//		}else{
//			moveX2(nfo.mouseCoo().getX()-4*CONST.SCALE);
//		}
//		if (nfo.mouseCoo().getY()< nfo.SHeight()/2){
//			moveY1(nfo.mouseCoo().getY());
//		}else{
//			moveY2(nfo.mouseCoo().getY());
//		}
//		
//		area.move(((int) (gMouse.getX()/Constants.GAME_TILE_SIZE)*Constants.GAME_TILE_SIZE - wW.getX1()),
//				((int) (gMouse.getY()/Constants.GAME_TILE_SIZE)*Constants.GAME_TILE_SIZE - wW.getY1()));
//		area.incrementX(-Constants.GAME_TILE_SIZE);
//		area.incrementY(-Constants.GAME_TILE_SIZE);
//		domain.setText(obs.getName());
//		if (obs.getHabitable())
//			area.setVariation(0);
//		else
//			area.setVariation(1);
//		
//		domain.moveX1(getX1());
//		domain.moveY1(getY1());

	}
	
	void render(float dms){
		
		domainPanel.render(dms);
		Gui.fonts.BellS.renderCentered(domainText, domainPanel, 25, QuadColor.fullWhite);
//		area.render(dms);
//		//panel.render(this, QuadColor.fullWhite, dms);
//		domain.render(dms);
//		forest.render(dms);
//		fertility.render(dms);
//		mountain.render(dms);	
		
	}
	
	class Observer{
		
		void setBuild(){
			
		}
		
	}
	
	
}
