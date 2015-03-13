package game.world;

import sprites.Gui;
import sprites.RenSpriteV;
import util.Rectangle;
import util.Vector;
import menu.GameSpark;
import engine.PSettings;
import game.GameCore;
import game.Resources;
import game.STATE;
import game.GameInfo;

public class WorldState implements STATE{

	//world
	private final Rectangle worldWindow;
	private final Vector worldMouse;
	private final TileMap map;
	
	//gui
	private final GUI_TilePanel browser;
	private final RenSpriteV mouseP;
	
	private final GuiStatic buttons;
	
	private boolean pinned;
	
	public WorldState(Resources res, GameSpark gs){
		
		worldWindow = new Rectangle(0, res.screenWidth, 0, res.screenHeight);
		map = new TileMap(res, gs);
		
		mouseP = new RenSpriteV(Gui.sprites.mouse_cruiser(), 0, 0);
		browser = new GUI_TilePanel(map.getObserver());
		worldMouse = new Vector(0,0);
		buttons = new GuiStatic(res);
	}
	
	@Override
	public void render(float dms, GameInfo s) {
		
		map.render(dms, worldWindow);
		
		Gui.bind();
	
		browser.render(dms);
		
		buttons.render(dms);
		mouseP.render(dms);
		Gui.flush();
	}
	
	@Override
	public int update(float dms, GameInfo s) {


		return 0;
	}

	@Override
	public int input(PSettings.KEY k){
		if (pinned)
			return GameCore.WORLD_MAP;
		
		switch (k){
		case UP: worldWindow.incrementY(-15); 
			break;
		case DOWN: worldWindow.incrementY(15); 
			break;
		case LEFT: worldWindow.incrementX(-15); 
			break;
		case RIGHT: worldWindow.incrementX(15); 
			break;
		case MAP: map.changeSeason();
			break;
		default:
			break;
		}
		
		return GameCore.WORLD_MAP;
		
	}

	@Override
	public int click(int mouseButton, boolean pressed, GameInfo nfo) {
		
		if (!pressed)
			return GameCore.WORLD_MAP;
		
		switch (mouseButton){
		case 0: if (buttons.click()) return GameCore.BACK; break; //pin(nfo); break;
		case 1: pinned= false; break;
		case 2: System.out.println("wheel"); break;
		default: System.out.println("other"); break;
		}
		
		return GameCore.WORLD_MAP;
	}

	private void pin(GameInfo nfo){
		pinned = true;
		worldWindow.centerAt(worldMouse);
		nfo.centerMouse();
		
		
	}
	
	@Override
	public void hover(GameInfo nfo) {
		
		mouseP.move(nfo.mouseCoo());
		
		if (buttons.hover(nfo.mouseCoo()))
			return;
		
		if(pinned)
			return;
		
		worldMouse.setX(nfo.mouseCoo().getX() + worldWindow.getX1());
		worldMouse.setY(nfo.mouseCoo().getY() + worldWindow.getY1());
		
		if (map.hover(worldMouse)){
			browser.hover(nfo, worldMouse, worldWindow);
		}
		
	}
	
	@Override
	public String hover(float ms){
		return "Hello!";
	}
}
