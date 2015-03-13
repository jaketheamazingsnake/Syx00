package game.world;

import data.CONST;

class Constants {

	static final int GAME_TILE_SIZE = CONST.TILE_SIZE_WORLDMAP*CONST.SCALE;
	static final int SMALL = 32;
	static final int MEDIUM = 64;
	static final int LARGE = 128;
	
	static final int VARIATIONS = 8;
	static final int SET = VARIATIONS*16;
	static final short INVISABLE = -0x8000;

	static final short DESSERT = 0;
	static final short STEPPE = DESSERT + 4*SET;
	static final short LUSH = STEPPE + 4*SET;
	static final short FOREST = LUSH + 4*SET;
	static final short MOUNTAIN = FOREST + 4*SET;
	static final short LAKE = MOUNTAIN + 2*SET;
	static final short RIVER = LAKE + 2*SET;
	static final short DEEP_WATER = RIVER + 2*SET;
	static final short WATER = DEEP_WATER + SET;

	//Specials
	static final short TEMPERATE = WATER + SET;
	static final short HILL = TEMPERATE + 32;
	static final short EDGES = (HILL + 8);
	static final short DELTAS = TEMPERATE + SET;
	
	static final short COLOR_FILTER = EDGES + 8;
	//settings
	static final byte HABITABLE = 0x03;


}
