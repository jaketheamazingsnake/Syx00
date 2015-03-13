package game.world;

import game.Resources;
import game.GameInfo;
import sprites.TileSheet;
import util.RECTANGLEBLE;
import util.Vector;
import menu.GameSpark;
import static game.world.Constants.*;

class TileMap {

	//ground, terrain, road, city/frame/habability
	private final short[][][] map;
	private final byte[][] properties;
	private final int maxTileX;
	private final int maxTileY;
	private final float maxMapX;
	private final float maxMapY; 
	private final TileSheet sheet;
	
	//spring
	private short season = 0;
	
	private final Observer obs;
	
	TileMap(Resources res, GameSpark gs){
		sheet = res.worldMap;
		maxTileY = Constants.LARGE;
		maxTileX = maxTileY*2;
		maxMapX = maxTileX*GAME_TILE_SIZE;
		maxMapY = maxTileY*GAME_TILE_SIZE;
		
		map = new TileMapGenerator().create(gs);
		properties = new TileMapFixer().getProperties(map);
		obs = new Observer();
	}
	
	boolean hover(Vector wM){
		
		
		return obs.hover(wM);
	}
	
	boolean update(GameInfo s){
		return false;
	}
	
	void render (float dms, RECTANGLEBLE wW){
		
		final float startX;
		final int startTileX;
		final float maxX;
		
		if (wW.getX1() < 0){
			startX = -wW.getX1();
			startTileX = 0;
			maxX = maxMapX-wW.getX1();
		}else if (wW.getX2() > maxMapX){
			startX = -wW.getX1()%GAME_TILE_SIZE;
			startTileX = (int) (wW.getX1()/GAME_TILE_SIZE);
			maxX = maxMapX-wW.getX1();
		}else{
			startX = -wW.getX1()%GAME_TILE_SIZE;
			startTileX = (int) (wW.getX1()/GAME_TILE_SIZE);
			maxX = wW.getWidth();
		}
		
		final float startY;
		final int startTileY;
		final float maxY;
		
		if (wW.getY1() < 0){
			startY = -wW.getY1();
			startTileY = 0;
			maxY = maxMapY-wW.getY1();
		}else if (wW.getY2() > maxMapY){
			startY = -wW.getY1()%GAME_TILE_SIZE;
			startTileY = (int) (wW.getY1()/GAME_TILE_SIZE);
			maxY = maxMapY-wW.getY1();
		}else{
			startY = -wW.getY1()%GAME_TILE_SIZE;
			startTileY = (int) (wW.getY1()/GAME_TILE_SIZE);
			maxY = wW.getHeight();
		}
		
		
		float X = startX;
		int tileX = startTileX;
		float Y = startY;
		int tileY = startTileY;
		sheet.bind();
		while (Y < maxY){
			while (X < maxX){
				sheet.render(map[tileY][tileX], X, Y);
				X += GAME_TILE_SIZE;
				tileX++;
			}
			tileY++;
			Y += GAME_TILE_SIZE;
			X = startX;
			tileX = startTileX;
		}
		
		sheet.flush();

	}
	
	void changeSeason(){
		
		season += SET;
		int tempSeason;
		if (season > 3*SET){
			season = 0;
			tempSeason = -3*SET; 
		}else{
			tempSeason = SET;
		}
		
		for (int y = 0; y < maxTileY; y++){
			for (int x = 0; x < maxTileX; x++){
				if (map[y][x][0] < FOREST){
					map[y][x][0] += tempSeason;
				}else if (map[y][x][0] >= TEMPERATE){
					map[y][x][0] += tempSeason/16;
				}
				
				
				if (map[y][x][1] != -1){
					if (map[y][x][1] < MOUNTAIN){
						map[y][x][1] += tempSeason;
					}else if (map[y][x][1] < DEEP_WATER){
						if (season == 3*SET){
							map[y][x][1] += SET;
						}else if (season == 0){
							map[y][x][1] -= SET;
						}
						
					}
					
				}
				
			}
		}
		
		
	}
	
	Observer getObserver(){
		return obs;
	}
	
	class Observer{
		
		
		private int mCount;
		private int fCount;
		private int hTileX;
		private int hTileY;
		private Observer(){
			
		}
		
		private boolean hover(Vector wM){
			hTileX = (int) (wM.getX()/GAME_TILE_SIZE);
			hTileY = (int) (wM.getY()/GAME_TILE_SIZE);
			if (hTileX < 1 || hTileX >= maxTileX-1 || 
					hTileY < 1 || hTileY >= maxTileY-1)
				return false;
			return true;
//			mCount = 0;
//			fCount = 0;
//			habitable = false;
//			
//			short type;
//			for (int y = hTileY-1; y <= hTileY+1; y++){
//				for (int x = hTileX-1; x <= hTileX+1; x++){
//					type = map[y][x][1];
//					if (type < FOREST){
//						
//					}else if (type < MOUNTAIN){
//						fCount++;
//					}else if (type < RIVER){
//						mCount++;
//					}
//				}
//			}
//			if (map[hTileY][hTileX][3] < -1){
//				habitable = true;
//			}
			
			
		}
		
		boolean getHabitable(){
			return (map[hTileY][hTileX][3] < -1);
		}
		
		int getMountain(){
			return mCount;
		}
		
		int getForest(){
			return fCount;
		}
		
		String getName(){
			return "Unclaimed Land";
		}
	}
	
}
