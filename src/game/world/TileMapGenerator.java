package game.world;

import java.util.LinkedList;
import static game.world.Constants.*;
import java.util.Random;
import engine.GFX;
import menu.GameSpark;


class TileMapGenerator {

	private final Random rnd;
	
	private int[][] ground;
	private int[][] terrain;
	private int maxX;
	private int maxY;
	private int max;
	private int landmass;
	
	TileMapGenerator(){
		rnd = new Random();
	}
	
	short[][][] create(GameSpark gs){
		
		//width and height of map
		maxY = Constants.LARGE;
		maxX = maxY*2;
		max = maxX*maxY;
		ground = new int[maxY][maxX];
		terrain = new int[maxY][maxX];
		
		GFX.drawLoadingText("Seasoning the wolrdMap");
		season();
		
		
		GFX.drawLoadingText("elevating...");
		new Elevator();
		
		//fix mysterious moutain bug
		for (int y = 0; y < maxY; y++){
			for(int x = 0; x < maxX; x++){
				if (checkTile(terrain, MOUNTAIN, MOUNTAIN, y-1, x) == 1 && checkTile(terrain, MOUNTAIN, MOUNTAIN, y+1, x) == 1 
						&& checkTile(terrain, MOUNTAIN, MOUNTAIN, y, x-1) == 1 && checkTile(terrain, MOUNTAIN, MOUNTAIN, y, x+1) == 1 ){
					if (terrain[y][x] == HILL && rnd.nextBoolean() && rnd.nextBoolean()){
						terrain[y][x] = LAKE;
					}else if (terrain[y][x] == HILL && rnd.nextBoolean()){
						terrain[y][x] = MOUNTAIN;
					}else if (terrain[y][x] == -1){
						terrain[y][x] = MOUNTAIN;
					}
				}
			}
		}
		
		
		GFX.drawLoadingText("creating some rivers...");
		new River();
		
		GFX.drawLoadingText("fertilizing the earth...");
		new Lusher();
		
		GFX.drawLoadingText("foresting...");
		int amount = landmass /4 + rnd.nextInt(landmass/2);
		while (landmass > amount)
			new Forest();
		
		GFX.drawLoadingText("fixing...");
		new TileFixer();
		
		for (int y = 0; y < maxY; y++){
			for(int x = 0; x < maxX; x++){
				if (terrain[y][x]%SET == 15 && !(terrain[y][x] >= RIVER &&  terrain[y][x] < RIVER + SET)){
					ground[y][x] |= 0x00008000; 
				}
			}
		}
		
		short[][][] finalMap = new short[maxY][maxX][4];
		
		//merge both
		for (int y = 0; y < maxY; y++){
			for(int x = 0; x < maxX; x++){
				finalMap[y][x][0] = (short) (ground[y][x]);
				finalMap[y][x][1] = (short) (terrain[y][x]);
				finalMap[y][x][2] = INVISABLE;
				
				if (y == 0 && x == 0){
					finalMap[y][x][3] = EDGES;
				}else if (y == maxY-1 && x == 0){
					finalMap[y][x][3] = EDGES+6;
				}else if (y == maxY-1 && x == maxX-1){
					finalMap[y][x][3] = EDGES+4;
				}else if (y == 0 && x == maxX-1){
					finalMap[y][x][3] = EDGES+2;
				}else if (x == 0){
					finalMap[y][x][3] = EDGES+7;
				}else if (x == maxX-1){
					finalMap[y][x][3] = EDGES+3;
				}else if (y == 0){
					finalMap[y][x][3] = EDGES+1;
				}else if (y == maxY -1){
					finalMap[y][x][3] = EDGES+5;
				}else{
				
					finalMap[y][x][3] = INVISABLE;
				}
			}
		}
		
		return finalMap;
			
	}
	
	private void season(){
		for (int y = 0; y<maxY; y++){
			
			for(int x = 0; x < maxX; x++){

				if (rnd.nextInt(maxY)/(y/8+1) + 3 > y)
					ground[y][x] = STEPPE;
				else if (y > rnd.nextInt(maxY/5)+(maxY/5)*4)
					ground[y][x] = DESSERT;
				else
					ground[y][x] = TEMPERATE;
			}
		}
	}
	
	private int checkTile(int[][] m, int lookfor, int lookfor2, int y, int x){
		if (x < 0 || x > maxX-1 || y < 0 || y > maxY-1)
			return 0;
		if ((m[y][x] >= lookfor && m[y][x] < lookfor +SET) || (m[y][x] >= lookfor2 && m[y][x] < lookfor2 +SET))
			return 1;
		else
			return -1;
	}
	
	private int checkTile(int[][] m, int lookfor, int lookfor2, int lookfor3, int y, int x){
		if (x < 0 || x > maxX-1 || y < 0 || y > maxY-1)
			return 0;
		if ((m[y][x] >= lookfor && m[y][x] < lookfor +SET) || (m[y][x] >= lookfor2 && m[y][x] < lookfor2 +SET)
				|| (m[y][x] >= lookfor3 && m[y][x] < lookfor3 +SET))
			return 1;
		else
			return -1;
	}
	
	private void setTile(int[][] map, int SET, int y, int x){
		if (x < 0 || x > maxX-1 || y < 0 || y > maxY-1)
			return;
		map[y][x] = SET;
		
	}
	
	private class TileFixer{
		
		boolean[][] mountain = new boolean[maxY][maxX];
		
		private TileFixer(){

			fixShorelines();
			for (int y = 0; y<maxY; y++){
				for(int x = 0; x < maxX; x++){
					fix(terrain, y, x);
					fix(ground, y, x);
				}
			}
			fixMountains();
		}
		
		private void fixShorelines(){

			for (int y = 0; y<maxY; y++){
				for(int x = 0; x < maxX; x++){
					if (terrain[y][x] == DEEP_WATER){
						if (checkTile(terrain, WATER, DEEP_WATER, y-1, x) == -1){
							terrain[y][x] = WATER;
						}
						else if (checkTile(terrain, WATER, DEEP_WATER, y, x+1) == -1)
							terrain[y][x] = WATER;
						else if (checkTile(terrain, WATER, DEEP_WATER, y+1, x) == -1)
							terrain[y][x] = WATER;
						else if (checkTile(terrain, WATER, DEEP_WATER, y, x-1) == -1)
							terrain[y][x] = WATER;
						else
							ground[y][x] = WATER | 0b1111;
					}
				}
			}
			
			for (int y = 0; y<maxY; y++){
				for(int x = 0; x < maxX; x++){
					if (terrain[y][x] == WATER){
						if (checkTile(terrain, WATER, DEEP_WATER, DELTAS, y-1, x) > -1){
							terrain[y][x] |= 0b0001;
						}
						if (checkTile(terrain, WATER, DEEP_WATER, DELTAS, y, x+1) > -1)
								terrain[y][x] |= 0b0010;
						if (checkTile(terrain, WATER, DEEP_WATER, DELTAS, y+1, x) > -1)
							terrain[y][x] |= 0b0100;
						if (checkTile(terrain, WATER, DEEP_WATER, DELTAS, y, x-1) > -1)
							terrain[y][x] |= 0b1000;
					}	
				}
			}
		}
		
		private void fix(int[][] m, int y, int x){
			
			final int LOOKFOR = m[y][x];
			final int LOOKFOR2;
			
			if (LOOKFOR >= WATER)
				return;
			
			if (LOOKFOR == RIVER || LOOKFOR == LAKE){
				LOOKFOR2 = DELTAS;
			}else{
				LOOKFOR2 = LOOKFOR;
			}
			
			if (checkTile(m, LOOKFOR, LOOKFOR2, y-1, x) > -1)
				m[y][x] |= 0b0001;
			if (checkTile(m, LOOKFOR, LOOKFOR2, y, x+1) > -1)
					m[y][x] |= 0b0010;
			if (checkTile(m, LOOKFOR, LOOKFOR2, y+1, x) > -1)
				m[y][x] |= 0b0100;
			if (checkTile(m, LOOKFOR, LOOKFOR2, y, x-1) > -1)
				m[y][x] |= 0b1000;
			
		}
			
		private void fixMountains(){
			boolean b = true;
			
			for (int y = 0; y<maxY; y++){
				for(int x = 0; x < maxX; x++){
					if (terrain[y][x] == MOUNTAIN + 15){
						ground[y][x] = MOUNTAIN + 15;
						mountain[y][x] = true;
					}
				}
			}
			
			while (b){
				b = false;
				for (int y = 0; y<maxY; y++){
					for(int x = 0; x < maxX; x++){
						if (mountain[y][x]){
							b = true;
							terrain[y][x] &= 0xFFF0;
							if (checkmountian(y-1, x) && checkTile(terrain, MOUNTAIN, MOUNTAIN, y-1, x) > -1)
								terrain[y][x] |= 0b0001;
							if (checkmountian(y, x+1) && checkTile(terrain, MOUNTAIN, MOUNTAIN, y, x+1) > -1)
								terrain[y][x] |= 0b0010;
							if (checkmountian(y+1, x) && checkTile(terrain, MOUNTAIN, MOUNTAIN, y+1, x) > -1)
								terrain[y][x] |= 0b0100;
							if (checkmountian(y, x-1) && checkTile(terrain, MOUNTAIN, MOUNTAIN, y, x-1) > -1)
								terrain[y][x] |= 0b1000;
						}
					}
				}
				for (int y = 0; y<maxY; y++){
					for(int x = 0; x < maxX; x++){
						if (terrain[y][x] == MOUNTAIN + 15){
							mountain[y][x] = true;
						}else
							mountain[y][x] = false;
					}
				}
				
			}
			
			
		}
		
		boolean checkmountian(int y, int x){
			if (x < 0 || x > maxX-1 || y < 0 || y > maxY-1 || mountain[y][x])
				return true;
			return false;
		}
		
	}
	
	private class Lusher{
		
		private Lusher(){
			int c = 1;
			int chance = 0;
			for (int y = 0; y<maxY; y++){
				if (y == maxY/2)
					c = -1;
				chance += c;
				chance += c;
				for(int x = 0; x < maxX; x++){
					if (ground[y][x] == DESSERT){
						if (terrain[y][x] == LAKE || terrain[y][x] == RIVER){
							if (rnd.nextInt() == 3)
								setLush(ground, y, x);
						}
							
					}else if (ground[y][x] == STEPPE){
						if (terrain[y][x] == LAKE || terrain[y][x] == RIVER){
							if (rnd.nextInt() == 3)
								setLush(ground, y, x);
							else if(rnd.nextBoolean())
								ground[y][x] = TEMPERATE;
						}	
					}else{
						if (terrain[y][x] == LAKE || terrain[y][x] == RIVER)
							setLush(ground, y, x);
						else if (rnd.nextInt(maxY) < chance+2)
							setLush(ground, y, x);
							
					}	
				}
			}
		}
		
		private void setLush(int[][] map, int y, int x){
			
			map[y][x] = LUSH;
			
			if (checkTile(map, LUSH, TEMPERATE, y-1, x) == -1){
				map[y-1][x] = TEMPERATE;
			}
			if (checkTile(map, LUSH, TEMPERATE, y, x+1) == -1)
				map[y][x+1] = TEMPERATE;
			if (checkTile(map, LUSH, TEMPERATE, y+1, x) == -1)
				map[y+1][x] = TEMPERATE;
			if (checkTile(map, LUSH, TEMPERATE, y, x-1) == -1)
				map[y][x-1] = TEMPERATE;
		}
		
	}
	
	private class Elevator{
		
		private int lakemass = 0;
		private int mountainmass = 0;
		private int seamass = 0;
		
		private Elevator(){
			landmass = max;
			while ( landmass < maxY || lakemass < maxY/10 || mountainmass < maxY/10 || seamass < maxY/10){
				landmass = max;
				lakemass = 0;
				mountainmass = 0;
				seamass = 0;
				
				elevate();
				
			}	
		}
		
		private void elevate(){
			
			landmass = max;
			lakemass = 0;
			mountainmass = 0;
			seamass = 0;
			
			int height;
			int steepness;
			
			int x;
			int y;
			for (y = 0; y < maxY; y++)
				for (x = 0; x < maxX; x++)
					terrain[y][x] = 0;
			
			for (int i = 0; i < 0.007*max; i++){
				x = rnd.nextInt(maxX);
				y = rnd.nextInt(maxY);
				steepness = 1 + rnd.nextInt(25);
				height = 1000+ rnd.nextInt(1000);
				terrain[y][x] += height; 
				level(terrain, y, x, steepness, 8);
			}
			
			TileGraph g = new TileGraph(ground[0].length, ground.length);
			
			for (y = 0; y < maxY; y++){
				for (x = 0; x < maxX; x++){
					height = terrain[y][x];
					if (height < 800){
						terrain[y][x] = DEEP_WATER;
						seamass++;
						landmass--;
					}else if (height < 1300){
						terrain[y][x] = WATER;
						seamass++;
						landmass--;
					}else if (height < 4000){
						terrain[y][x] = -1;
					}else if (height < 5000){
						terrain[y][x] = HILL;	
					}else{
						terrain[y][x] = MOUNTAIN;
						mountainmass++;
						landmass--;
					}
				}
			}
			
			for (y = 0; y < maxY; y++){
				for (x = 0; x < maxX; x++){
					if (terrain[y][x] == WATER ||  terrain[y][x] == DEEP_WATER )
						join(g, x, y);
				}
			}
			
			for (y = 0; y < ground.length; y++){
				for (x = 0; x < ground[0].length; x++){
					if ((terrain[y][x] == WATER || terrain[y][x] == DEEP_WATER) && g.getRoot(x, y) != 0){
						terrain[y][x] = LAKE;
					lakemass ++;
					}
				}
			}

		}
		
		private void join(TileGraph g, int x, int y){
			if (x < 1 || x > maxX-2 || y < 1 || y > maxY-2)
				return;
			if (checkTile(terrain, WATER, DEEP_WATER, y-1, x) > -1){
				g.join(x, y-1, x, y);
			}
			if (checkTile(terrain, WATER, DEEP_WATER, y, x+1) > -1)
				g.join(x+1, y, x, y);
			if (checkTile(terrain, WATER, DEEP_WATER, y+1, x) > -1)
				g.join(x, y+1, x, y);
			if (checkTile(terrain, WATER, DEEP_WATER, y, x-1) > -1)
				g.join(x-1, y, x, y);
		}
		
		private void level(int[][] aboveMap, int cy, int cx, int steepness, int smoothness){
			
			int height = aboveMap[cy][cx];
			
			int startX;
			int endX;
			int startY;
			int endY;
			final int slope = height/steepness;
		
			if (cx < slope){
				startX = 0;
				endX = cx+slope;
			}
			else if (cx > (maxX-1)-slope){
				startX = cx-slope;
				endX = maxX-1;
			}else{
				startX = cx-slope;
				endX = cx+slope;
			}
			if (cy < slope){
				startY = 0;
				endY = cy+slope;
			}
			else if (cy > (maxY-1)-slope){
				startY = cy-slope;
				endY = maxY-1;
			}else{
				startY = cy-slope;
				endY = cy+slope;
			}
			
			if (endX > maxX-1)
				endX = maxX-1;
			else if (startX < 0)
				startX = 0;
			if (endY > maxY-1)
				endY = maxY-1;
			else if (startY < 0)
				startY = 0;
			
			int elevation;
			for(int y = startY; y <= endY; y++){
				for (int x = startX; x <= endX; x++){
					if (x == cx && y == cy)
						continue;
					elevation = (int) Math.sqrt((cx - x)*(cx - x) + (cy -y)*(cy -y));
					if (elevation > steepness)
						continue;
					elevation = (int) (height - (int)Math.sqrt((cx - x)*(cx - x) + (cy -y)*(cy -y))*slope); 
					elevation += (elevation/2 - rnd.nextInt(1 + Math.abs(elevation)))/smoothness;
					if (aboveMap[y][x] < elevation)
						aboveMap[y][x] += elevation;
				}

			}
		}
		
	}
	
	class River{
		
		private int directionX; 
		private int directionY;
		private int trials = 0;

		River(){
			
			LinkedList<Integer> coo = new LinkedList<Integer>();
			
			for (int y = 0; y < maxY; y++){
				for (int x = 0; x < maxX; x++){
					if (terrain[y][x] == -1 && (checkTile(terrain, WATER, DEEP_WATER, y-1, x) == 1 ||
						checkTile(terrain, WATER, DEEP_WATER, y+1, x) == 1 || checkTile(terrain, WATER, DEEP_WATER, y, x-1) == 1 
						 || checkTile(terrain, WATER, DEEP_WATER, y, x+1) == 1)){
						 coo.add(y);
						 coo.add(x);
					}
				}
			}
			
			int x;
			int y;
			
			loop:
			while(!coo.isEmpty()){
				
				y = coo.removeFirst();
				x = coo.removeFirst();
				
				if (rnd.nextInt(maxY) < maxY/15){
					for (int x1 = x-1; x1 <= x+1; x1++){
						for (int y1 = y-1; y1 <= y+1; y1++)
							if (checkTile(terrain, DELTAS, DELTAS, y1, x1) == 1)
								continue loop;
					}
				
					if (checkTile(terrain, WATER, DEEP_WATER, y-1, x) == 1){
						directionX = 0;
						directionY = 1;
					}else if(checkTile(terrain, WATER, DEEP_WATER, y, x-1) == 1){
						directionX = 1;
						directionY = 0;	
					}else if(checkTile(terrain, WATER, DEEP_WATER, y+1, x) == 1){
						directionX = 0;
						directionY = -1;
					}else if(checkTile(terrain, WATER, DEEP_WATER, y, x+1) == 1){
						directionX = -1;
						directionY = 0;	
					}
					
					trials = 0;
					
					if (start(y, x, 1, false, false, 0)){
						if (directionX == 1){
							terrain[y][x] = DELTAS;
							terrain[y-1][x] = WATER;
							terrain[y+1][x] = WATER;
							terrain[y][x-1] = WATER;
							terrain[y-1][x-1] = WATER;
							terrain[y+1][x-1] = WATER;
						}else if (directionX == -1){
							terrain[y][x] = DELTAS + 24;
							terrain[y-1][x] = WATER;
							terrain[y+1][x] = WATER;
							terrain[y][x+1] = WATER;
							terrain[y-1][x+1] = WATER;
							terrain[y+1][x+1] = WATER;
							
						}else if (directionY == 1){
							terrain[y][x] = DELTAS + 8;
							terrain[y][x+1] = WATER;
							terrain[y][x-1] = WATER;
							terrain[y-1][x] = WATER;
							terrain[y-1][x+1] = WATER;
							terrain[y-1][x-1] = WATER;
							
						}else if (directionY == -1){
							terrain[y][x] = DELTAS + 16;
							terrain[y][x+1] = WATER;
							terrain[y][x-1] = WATER;
							terrain[y+1][x] = WATER;
							terrain[y+1][x+1] = WATER;
							terrain[y+1][x-1] = WATER;
						}
					}
				}
			}
		}
		
		private boolean start(int y, int x, int straight, boolean left, boolean right, int length){
			
			trials ++;
			
			if (trials > 1000)
				return false;
				
			if (length > maxX)
				return true;
			
			
			if (length > 5){
				for (int y1 = y-2; y1 <= y+2; y1++)
					for (int x1 = x-2; x1 <= x+2; x1++)
						if (checkTile(terrain, WATER, DEEP_WATER, y1, x1) == 1){
							return false;
						}
			}
			
			if ((x > maxX-1 || y > maxY-1 || x <0 || y <0)){
				return false;
			}
			
			if ((x == maxX-1 || y == maxY-1 || x == 0 || y == 0)){
				if (length < 3){
				return false;
				}
				terrain[y][x] = RIVER;
				return true;
			}
			
			if (terrain[y][x] == WATER || terrain[y][x] == DEEP_WATER){
				return false;
			}
			
			if (checkTile(terrain, RIVER, LAKE, y, x) == 1){
				if (length > 3){
					
					
					terrain[y][x] = RIVER;
					return true;
				}
				return false;
			}
			
			if (checkTile(terrain, RIVER, RIVER, y+1, x) == 1){
				if (length > 4){
					terrain[y][x] = RIVER;
					return true;
				}
				return false;
			}
			if (checkTile(terrain, RIVER, RIVER, y-1, x) == 1 ){
				if (length > 4 ){
					terrain[y][x] = RIVER;
					return true;
				}
				return false;
			}
			if (checkTile(terrain, RIVER, RIVER, y, x-1) == 1){
				if (length > 4 ){
					terrain[y][x] = RIVER;
					return true;
				}
				return false;
			}
			if (checkTile(terrain, RIVER, RIVER, y, x+1) == 1){
				if (length > 4){
					terrain[y][x] = RIVER;
					return true;
				}
				return false;
			}
			
			if (checkTile(terrain, LAKE, LAKE, y+1, x) == 1){
				if (length > 4 && straight == 2){
					terrain[y][x] = RIVER;
					terrain[y+1][x] = DELTAS + 32 + 16;
					setTile(terrain, LAKE, y+2, x);
					terrain[y+1][x-1] = -1;
					terrain[y+1][x+1] = -1;
					return true;
				}
				return false;
			}
			if (checkTile(terrain, LAKE, LAKE, y-1, x) == 1 ){
				if (length > 4 && straight == 2){
					terrain[y][x] = RIVER;
					terrain[y-1][x] = DELTAS + 32 + 8;
					setTile(terrain, LAKE, y-2, x);
					terrain[y-1][x-1] = -1;
					terrain[y-1][x+1] = -1;
					return true;
				}
				return false;
			}
			if (checkTile(terrain, LAKE, LAKE, y, x-1) == 1){
				if (length > 4 && straight == 2){
					terrain[y][x] = RIVER;
					terrain[y][x-1] = DELTAS + 32;
					setTile(terrain, LAKE, y, x-2);
					terrain[y-1][x-1] = -1;
					terrain[y+1][x-1] = -1;
					return true;
				}
				return false;
			}
			if (checkTile(terrain, LAKE, LAKE, y, x+1) == 1){
				if (length > 4 && straight == 2){
					terrain[y][x] = RIVER;
					terrain[y][x+1] = DELTAS + 32 + 24;
					setTile(terrain, LAKE, y, x+2);
					terrain[y-1][x+1] = -1;
					terrain[y+1][x+1] = -1;
					return true;
				}
				return false;
			}
			
			
			if(terrain[y][x] == MOUNTAIN){
				if (rnd.nextInt(5) == 4){
					return true;
				}
			}
			
			if (straight == 2){
				straight = 0;
				left = true;
				right = true;
			}
			
			if (directionX == 0){
				if (rnd.nextInt(4) == 1 && right){
					if (start(y, x+1, 0, false, true, length+1)){
						terrain[y][x] = RIVER;
						return true;
					}
				}else if (rnd.nextInt(4) == 1 && left){
					if (start(y, x-1, 0, true, false, length+1)){
						terrain[y][x] = RIVER;
						return true;
					}
				}else{
					if (start(y + directionY, x, straight+1, left, right, length+1)){
						terrain[y][x] = RIVER;
						return true;
					}else{
						if (rnd.nextBoolean()){
							if (right && start(y, x+1, 0, false, true, length+1)){
								terrain[y][x] = RIVER;
								return true;
							}
						}else{
							if (left && start(y, x-1, 0, true, false, length+1)){
								terrain[y][x] = RIVER;
								return true;
							}
						}
						
					}
				}
			}
			
			if (directionY == 0){
				if (rnd.nextInt(4) == 1 && right){
					if (start(y+1, x, 0, false, true, length+1)){
						terrain[y][x] = RIVER;
						return true;
					}
				}else if (rnd.nextInt(4) == 1 && left){
					if (start(y-1, x, 0, true, false, length+1)){
						terrain[y][x] = RIVER;
						return true;
					}
				}else{
					if (start(y, x + directionX, straight+1, left, right, length+1)){
						terrain[y][x] = RIVER;
						return true;
					}else{
						if (rnd.nextBoolean()){
							if (right && start(y+1, x, 0, false, true, length+1)){
								terrain[y][x] = RIVER;
								return true;
							}
						}else{
							if (left && start(y-1, x, 0, true, false, length+1)){
								terrain[y][x] = RIVER;
								return true;
							}
						}
						
					}
				}
			}
			
			return false;

		}
		
	}
	
	class Forest{
		
		int amount = 1 + rnd.nextInt(5)*rnd.nextInt(5)*rnd.nextInt(7);
		private int x = rnd.nextInt(ground[0].length);;
		private int y  = rnd.nextInt(ground.length);
		
		Forest(){
			while (terrain[y][x] != -1 && terrain[y][x] != HILL && ground[y][x] != DESSERT){

				x = rnd.nextInt(ground[0].length);
				y = rnd.nextInt(ground.length);
			}
			
			build(x, y);
			
		}
		
		private void build(int x, int y){
			final int[] x1 = new int[3];
			final int[] y1 = new int[3];

			if (x < 0 || y < 0 || x > maxX-1 || y > maxY -1 || (terrain[y][x] != -1 && terrain[y][x] != HILL) || ground[y][x] == DESSERT || amount <0)
				return;
			terrain[y][x] = FOREST;
			amount --;
			landmass --;
			
			x1[0] = rnd.nextInt(3) -1;
			y1[0] = rnd.nextInt(3) -1;
			
			int c = rnd.nextInt(3) -1;
			while(c == x1[0]){
				c = rnd.nextInt(3) -1;
			}
			x1[1] = c;
			while(c == x1[1] || c == x1[0]){
				c = rnd.nextInt(3) -1;
			}
			x1[2] = c;
			c = rnd.nextInt(3) -1;
			while(c == y1[0]){
				c = rnd.nextInt(3) -1;
			}
			y1[1] = c;
			while(c == y1[1] || c == y1[0]){
				c = rnd.nextInt(3) -1;
			}
			y1[2] = c;
			
			for (int j : y1){
				for(int i: x1){
					build (x+i,y+j);
				}
			}
			
		}
		
	}
	
}
