package sprites;


import util.ARECTANGLE;
import engine.Renderer;

class AnimationV extends Base implements ANIMATIONVI{

	private final float[] x;
	private final float width;
	private final float y;
	private final float height;
	private float freq = 1f/5f;
	private float timer = 0;
	private final int nrOfFrames;
	private int currentFrame = 0;
	private boolean playing = true;
	
	AnimationV(float pxW, float pxH, float[] coo, int variations, int frames) {
		super(pxW, pxH, coo);
		
		x = new float [variations];
		y = coo[2]*pxH;
		width  = coo[1]*pxW;
		height = coo[3]*pxH;
		
		this.nrOfFrames = frames -1;
		
		for (int i = 0; i < variations; i++){
			x[i] = (coo[0] + frames*coo[1]*i)*pxW; 
		}
	}

	@Override
	public boolean render(ARECTANGLE r, float[] color, float dms){
		Renderer.texture(r.getX1(), r.getX2(), r.getY1(), r.getY2(), x[0]+width*currentFrame, x[0]+width*currentFrame+width, 
				y, y+height, color);
		
		if (playing){
			return incrementFrame(dms);
		}
		
		return false;
	}
	
	@Override
	public boolean render(int Nr, ARECTANGLE r, float[] color, float dms){
		Renderer.texture(r.getX1(), r.getX2(), r.getY1(), r.getY2(), x[Nr]+width*currentFrame, x[Nr]+width*currentFrame+width, 
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
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

	@Override
	public int getVariations() {
		return x.length-1;
	}
	
}
