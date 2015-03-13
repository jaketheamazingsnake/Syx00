package sprites;

import sprites.Sprite;
import sprites.SpriteV;
import data.Paths;

public class Misk extends ABS_SHEET{

	public Misk() {
		super(Paths.SpriteSheets().misc);
		sprites = new Sprites();
	}

	public Sprites sprites;
	
	public class Sprites{
	
		public final SPRITEVI clouds_large;
		public final SPRITEVI clouds_large_shadows;
		public final SPRITEVI menu_pillars;
		public final SPRITEVI menu_stalagmites;
		public final SPRITEVI menu_stalagmites2;
		public final SPRITEI menu_brokenPillar;
		public final SPRITEI menu_skeleton_pillar;
		//bat animation
		//water drop
		
		public Sprites(){
			clouds_large = new SpriteV(pxW, pxH, new float[]			{0,64,0,64},4);
			clouds_large_shadows = new SpriteV(pxW, pxH, new float[]	{0,64,64,64},4);
			menu_pillars = new SpriteV(pxW, pxH, new float[]			{0,40,128,128},7);
			menu_stalagmites = new SpriteV(pxW, pxH, new float[]{279, 37, 128, 23}, 4);
			menu_stalagmites2 = new SpriteV(pxW, pxH, new float[]{279, 37, 152, 23}, 4);
			menu_brokenPillar = new Sprite(pxW, pxH, new float[]{279, 145, 210, 55});
			menu_skeleton_pillar = new Sprite(pxW, pxH, new float[]{427, 85, 128, 128});
		}
		
	}
}
