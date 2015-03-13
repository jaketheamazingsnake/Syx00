package game.world;

import static game.world.Constants.*;

public class TileMapFixer {

	TileMapFixer(){
		
	}
	
	byte[][] getProperties(short[][][] map){
		byte[][] properties = new byte[map.length][map[0].length];
		
		for (int y = 0; y < map.length; y++){
			for (int x = 0; x < map[0].length; x++){
				if (map[y][x][1] == -1){
					properties[y][x] = 1;
				}else if (map[y][x][1] >= MOUNTAIN && map[y][x][1] < LAKE && (map[y][x][0] < MOUNTAIN || map[y][x][0] >= LAKE)){
					//Dwarves
					properties[y][x] = 1;
				}
				else properties[y][x] = 0;
				
				
			}
			
		}
		
		return properties;
	}
	
}
