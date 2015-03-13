package launcher;

import java.util.Random;

import sprites.BigImage;
import sprites.RenSpriteV;
import sprites.SPRITEVI;
import util.Direction;
import util.Rectangle;
import util.Vector;
import engine.QuadColor;
import engine.Sound;

public class FloatingBackgroundMap {

	private Rectangle quadBounds;
	private BigImage.Slice map;
	private final SPRITEVI shadowSprite;
	private final SPRITEVI sprite; 
	private Cloud[] clouds;
	private BigCloud[] bigClouds;
	private Vector mapMax;
	private Vector mapAcc;
	private Vector mapSpeed;
	private Sound cloudRelease;
	private Random rnd;
	private int cloudTimer;
	Resources res;
	
	FloatingBackgroundMap (Resources res) {
		
		cloudRelease = res.wind;
		rnd = new Random();
		mapMax = new Vector(res.map.getWidth()-500, res.map.getHeight()-500);
		mapAcc = new Vector(10, 10);
		mapSpeed = new Vector(40 + 20*rnd.nextFloat(), 40 + 20*rnd.nextFloat());
		this.res = res; 
		
		int x = rnd.nextInt((int) (mapMax.getX()-1000))+500;
		int y = rnd.nextInt((int) (mapMax.getY()-1000))+500;
		
		quadBounds = new Rectangle(0, Launcher.LAUNCHER_WIDTH, 0, Launcher.LAUNCHER_HEIGHT);
		
		map = res.map.getSlice(0, Launcher.LAUNCHER_WIDTH, 0,Launcher.LAUNCHER_HEIGHT);
		map.texMove(x, y);
		
		if (rnd.nextBoolean())
			mapSpeed.reverseX();
		if (rnd.nextBoolean())
			mapSpeed.reverseY();
		
		shadowSprite = res.misk.sprites.clouds_large_shadows;
		sprite = res.misk.sprites.clouds_large;
		
		clouds = new Cloud[20];
		for (int i = 0; i < clouds.length; i++)
			clouds[i] = new Cloud(rnd.nextFloat() + 1f);
		bigClouds = new BigCloud[25];
		for (int i = 0; i < bigClouds.length; i++)
			bigClouds[i] = new BigCloud(rnd.nextFloat()*2 + 3f);
		releaseTheClouds();
		cloudTimer = rnd.nextInt(60)+60;
		
	}
	
	void update(float ms, int s){
		
		if (mapSpeed.getX() >= 100f || mapSpeed.getX() <= -100f)
			mapAcc.reverseX();
		if (mapSpeed.getY() >= 100f || mapSpeed.getY() <= -100f)
			mapAcc.reverseY();

		else if (s%10 == 0){
			if (rnd.nextBoolean())
			mapAcc.reverseX();
			if (rnd.nextBoolean())
				mapAcc.reverseY();
		}
		
		if (map.texGetX2() > mapMax.getX()){
			mapAcc.setX(-10);
		}else if (map.texGetX1() < 500){
			mapAcc.setX(10);
		}
		
		if (map.texGetY2() > mapMax.getY()) {
			mapAcc.setY(-10);
		}else if (map.texGetY1() < 500){
			mapAcc.setY(10);
		}
		
		mapSpeed.incrementX(mapAcc.getX()*ms);
		mapSpeed.incrementY(mapAcc.getY()*ms);
		map.texMove(mapSpeed.getX()*ms, mapSpeed.getY()*ms);

		
		for (Cloud cloud: clouds)
			if (!cloud.update(ms))
				cloud.reIni();
		
		if (s%cloudTimer == 0)
			releaseTheClouds();
			
		for (BigCloud cloud: bigClouds)
			cloud.update(ms);
		
	}
	
	void render(float ms){
		res.map.bind();
		map.render();
		res.map.flush();
		res.misk.bind();
		for (Cloud cloud: clouds)
			cloud.render(ms);
		for (Cloud cloud: clouds)
			cloud.renderSprite(ms);
		res.misk.flush();
	}
	
	public void renderClouds() {
		res.misk.bind();
		for (BigCloud cloud: bigClouds)
			cloud.render(0);
		res.misk.flush();
	}
	
	private void releaseTheClouds(){
		cloudTimer = rnd.nextInt(50)+50;
		for (BigCloud c: bigClouds)
			c.reIni();
		cloudRelease.play();
	}
	
	class Cloud extends RenSpriteV{
		
		/**
		 * clouds oppacity = shadows oppacity lala, you know what i'm talking about, right asshole? 
		 */
		private static final float ySpeed = -15f;
		private static final float xSpeed = 8f; 
		private final float scale;
		private final Rectangle spriteBounds;
		
		Cloud (float scale){
			super(shadowSprite, 0, 0);
			scale(scale*1.5f, scale*1.5f);
			moveX1(- quadBounds.getX2() + 2*rnd.nextInt((int)quadBounds.getX2()));
			moveY1(rnd.nextInt((int)  quadBounds.getY2()));
			this.scale = scale;
			incrementVariation(rnd.nextInt(sprite.getVariations()));
			spriteBounds = new Rectangle(0, sprite.getWidth()*scale, 0, sprite.getHeight()*scale);
			setColor(new float[]{1,1,1,0.3f,1,1,1,0.9f,1,1,1,0.3f,1,1,1,0.3f});
		}
		
		boolean update(float ms){
			incrementY(ySpeed*scale*scale*ms - mapSpeed.getY()*ms);
			incrementX(xSpeed*scale*ms - mapSpeed.getX()*ms);
			spriteBounds.centerIn(this, Direction.SW);

			return touch(map);
		}
		
		void reIni(){
			incrementVariation(rnd.nextInt(sprite.getVariations()));
			moveX1(- quadBounds.getX2() + 2*rnd.nextInt((int)quadBounds.getX2()));
			
			if (mapSpeed.getY() < ySpeed*scale*scale){
				moveY2(0);
			}else{
				moveY1(quadBounds.getY2());
			}
			
			spriteBounds.centerIn(this, Direction.E);

		}

		private void renderSprite(float dms){
			sprite.render(variation, spriteBounds, QuadColor.fullWhite, dms);
		}

		
	}
	
	class BigCloud extends RenSpriteV{
		
		private static final float ySpeed = -15f;
		private static final float xSpeed = 8f; 
		private final float scale;
		
		BigCloud (float scale){
			super(sprite, 0, 0);
			scale (scale, scale);
			moveX1(- quadBounds.getX2() + 2*rnd.nextInt((int)quadBounds.getX2()));
			moveY1(rnd.nextInt((int)  quadBounds.getY2()));
			this.scale = scale;
			incrementVariation(rnd.nextInt(sprite.getVariations()));
		}
		
		void update(float ms){
			incrementY(ySpeed*scale*scale*scale*ms - mapSpeed.getY()*ms);
			incrementX(xSpeed*scale*ms - mapSpeed.getX()*ms);
		}
		
		void reIni(){
			incrementVariation(rnd.nextInt(sprite.getVariations()));
			moveX1(- quadBounds.getX2() + 2*rnd.nextInt((int)quadBounds.getX2()));
			
			if (mapSpeed.getY() < ySpeed*scale*scale){
				moveY2(0);
			}else{
				moveY1(quadBounds.getY2());
			}

		}
		
	}

	
}
