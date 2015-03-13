package sprites;

import util.ARECTANGLE;
import engine.Renderer;

class Animation extends Sprite implements ANIMATIONI{

	private float freq = 1f/5f;
	private float timer = 0;
	private final int nrOfFrames;
	private int currentFrame = 0;
	private boolean playing = true;
	
	Animation(float pxW, float pxH, float[] coo, int frames){
		super(pxW, pxH, coo);
		this.nrOfFrames = frames -1;
	}
	
	@Override
	public boolean render(ARECTANGLE r, float[] color, float dms) {
		Renderer.texture(r.getX1(), r.getX2(), r.getY1(), r.getY2(), x+width*currentFrame, x+width*(currentFrame+1), 
				y, y+height, color);
		
		if (playing){
			return incrementFrame(dms);
		}
		
		return false;
		
	}

	private boolean incrementFrame(float dms){
		timer+= dms;
		if (timer > freq){
			timer -= freq;
			currentFrame++;
		}
		if (currentFrame > nrOfFrames){
			currentFrame = 0;
			return true;
		}
		return false;
	}
	
	@Override
	public void setSpeed(float FPS) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() {
		playing = false;
	}

	@Override
	public void start() {
		playing = true;
		
	}

	@Override
	public int getCurrentFrame() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getFrames() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
