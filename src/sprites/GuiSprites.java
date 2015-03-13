package sprites;

import sprites.AnimationV;
import sprites.Sprite;
import sprites.SpriteV;

public class GuiSprites {

	private final float pxW;
	private final float pxH;
	
	public final SPRITEVI button_large;
	public final SPRITEI button_large_top;
	public final SPRITEI button_large_bottom;
	public final SPRITEVI button_medium;
	public final SPRITEI scrollable_box;
	public final SPRITEVI arrow_relief_left;
	public final SPRITEVI arrow_relief_right;
	public final SPRITEVI arrow_left;
	public final SPRITEVI arrow_right;
	public final SPRITEVI checkBox_Large;
	public final SPRITEI logo_launcher;
	public final SPRITEI logo_menu;
	public final SPRITEI world_compass;
	public final SPRITEI world_domain_panel;
	public final SPRITEVI world_button_cancel;
	public final SPRITEVI world_button_expedition;
	public final SPRITEVI world_button_expedition_ph;
	public final SPRITEVI world_button_military;
	public final SPRITEVI world_button_military_ph;
	public final SPRITEVI world_button_road;
	public final SPRITEVI world_button_road_path;
	public final SPRITEVI world_button_road_paved;
	public final SPRITEVI world_button_road_highway;
	public final SPRITEVI world_button_build;
	public final SPRITEVI world_button_build_wood;
	public final SPRITEVI world_button_build_mine;
	public final SPRITEVI world_button_build_hunt;
	public final SPRITEVI world_button_build_settlement;

	public final SPRITEVI tile_filter;
	
	public ANIMATIONVI mouse_cruiser(){
		return new AnimationV(pxW, pxH, new float[]{0, 10, 192, 10}, 3, 8);
	}
	
	
	GuiSprites(float pxW, float pxH){
		this.pxW = pxW;
		this.pxH = pxH;
		
		button_large = 						new SpriteV(pxW, pxH,  		new float[]{0,56,0,21}, 3);
		button_large_top = 					new Sprite(pxW, pxH, 		new float[]{169,56,0f,10f});
		button_large_bottom = 				new Sprite(pxW, pxH,		new float[]{169,56,10,11});
		button_medium = 					new SpriteV(pxW, pxH,		new float[]{0f,56f,22f,15f},3);
		scrollable_box = 					new Sprite(pxW, pxH, 		new float[]{0, 56, 38, 45});
		arrow_relief_left =					new SpriteV(pxW, pxH, 		new float[]{57,20,38,11},2);
		arrow_relief_right =				new SpriteV(pxW, pxH, 		new float[]{57,20,49,11},2);
		arrow_left= 						new SpriteV(pxW, pxH, 		new float[]{57,30,72,11},2);
		arrow_right =						new SpriteV(pxW, pxH, 		new float[]{57,30,61,11},2);
		checkBox_Large = 					new SpriteV(pxW, pxH, 		new float[]{226, 21, 0, 21},3);
		logo_launcher = 					new Sprite(pxW, pxH, 		new float[]{0,129,84,57});
		logo_menu =		 					new Sprite(pxW, pxH, 		new float[]{0,148,149,42});
		tile_filter = 						new SpriteV(pxW, pxH, 		new float[]{0,8,281,8},2);
		world_compass = 					new Sprite(pxW, pxH, 		new float[]{149,97,126f,65f});
		world_domain_panel = 				new Sprite(pxW, pxH, 		new float[]{130,69,95f,30f});
		world_button_cancel =				new SpriteV(pxW, pxH,		new float[]{0,22,203,22},2);
		world_button_expedition =			new SpriteV(pxW, pxH,		new float[]{45,22,203,22},2);
		world_button_expedition_ph =		new SpriteV(pxW, pxH,		new float[]{694,22,203,22},3);
		world_button_military =				new SpriteV(pxW, pxH,		new float[]{90,22,203,22},2);
		world_button_military_ph =			new SpriteV(pxW, pxH,		new float[]{761,22,203,22},3);
		world_button_build =				new SpriteV(pxW, pxH,		new float[]{135,22,203,22},2);
		world_button_road =					new SpriteV(pxW, pxH,		new float[]{180,22,203,22},2);
		world_button_road_path =			new SpriteV(pxW, pxH,		new float[]{493,22,203,22},3);
		world_button_road_paved =			new SpriteV(pxW, pxH,		new float[]{560,22,203,22},3);
		world_button_road_highway =			new SpriteV(pxW, pxH,		new float[]{627,22,203,22},3);
		world_button_build_wood =			new SpriteV(pxW, pxH,		new float[]{292,22,203,22},3);
		world_button_build_mine =			new SpriteV(pxW, pxH,		new float[]{359,22,203,22},3);
		world_button_build_hunt =			new SpriteV(pxW, pxH,		new float[]{426,22,203,22},3);
		world_button_build_settlement =		new SpriteV(pxW, pxH,		new float[]{225,22,203,22},3);
		
	}
	
}
