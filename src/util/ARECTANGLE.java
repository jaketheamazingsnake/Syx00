package util;


public abstract class ARECTANGLE implements RECTANGLEBLE{

	private float x;
	private float y;
	private float width;
	private float height;
	
	public ARECTANGLE (float x1, float x2, float y1, float y2){
		x = x1;
		width = x2-x1;
		y = y1;
		height = y2-y1;
	}
	
	/**
	 * 
	 * @param other get copy of this
	 */
	public ARECTANGLE (RECTANGLEBLE other){
		x = other.getX1();
		y = other.getY1();
		width = other.getWidth();
		height = other.getHeight();
	}
	
	public ARECTANGLE (float width, float height, float x1, float x2, float y1, float y2){
		x = x1 + ((x2-x1)-width)/2;
		y = y1 + ((y2-y1)-height)/2;
		this.width = width;
		this.height = height;
	}
	@Override
	public void incrementX(float amount){x+= amount;}
	@Override
	public void incrementY(float amount){y+= amount;}
	
	@Override
	public void move(float X, float Y){x = X; y = Y;}
	@Override
	public void moveX1(float X1){x = X1;}
	@Override
	public void moveX2(float X2){x = X2-width;}
	@Override
	public void moveY1(float Y1){y = Y1;}
	@Override
	public void moveY2(float Y2){y = Y2-height;}
	@Override
	public void move(Vector vector){x = vector.X; y = vector.Y;}
	@Override
	public void setWidth(float width){this.width = width;}
	@Override
	public void setHeight(float height){this.height = height;}
	@Override
	public void scale(float Xmultiplier, float Ymultiplier){width*= Xmultiplier; height*=Ymultiplier;}
	@Override
	public void centerIn(RECTANGLEBLE other, Direction dir){
		switch (dir){
		case C:
			centerX(other.getX1(), other.getX2());
			centerY(other.getY1(), other.getY2());
			break;
		case N:
			centerX(other.getX1(), other.getX2());
			moveY1(other.getY1());
			break;
		case NE:
			moveX2(other.getX2());
			moveY1(other.getY1());
			break;
		case E:
			moveX2(other.getX2());
			centerY(other.getY1(), other.getY2());
			break;
		case SE: 
			moveX2(other.getX2());
			moveY2(other.getY2());
			break;
		case S: 
			centerX(other.getX1(), other.getX2());
			moveY2(other.getY2());
			break;
		case SW:
			moveX1(other.getX1());
			moveY2(other.getY2());
			break;
		case W:
			moveX1(other.getX1());
			centerY(other.getY1(), other.getY2());
			break;
		case NW:
			moveX1(other.getX1());
			moveY1(other.getY1());
			break;
		default:

			break;
		}
	}
	@Override
	public void centerIn(float x1, float x2, float y1, float y2){centerX(x1, x2); centerY(y1, y2);}
	@Override
	public void centerX(float x1, float x2){moveX1(x1 + ((x2-x1)-width)/2);}
	@Override
	public void centerY(float y1, float y2){moveY1(y1 + ((y2-y1)-height)/2);}
	@Override
	public void centerAt(Vector v){x = v.getX()-width/2; y = v.getY()-height/2;}
	@Override
	public void centerAt(float X, float Y){x = X-width/2; y = Y-height/2;}
	@Override
	public float getX1(){return x;}
	@Override
	public float getX2(){return x+width;}
	@Override
	public float getY1(){return y;}
	@Override
	public float getY2(){return y+height;}
	@Override
	public float getHeight() {return height;}
	@Override
	public float getWidth() {return width;}
	@Override
	public float getCentreX(){return x + width/2;}
	@Override
	public float getCentreY(){return y + height/2;}
	
	@Override
	public boolean touch(RECTANGLEBLE other){
		if ((x < other.getX2() && x+width > other.getX1())
				&& (y < other.getY2() && y+height > other.getY1()))
				return true;
			return false;
	}
	@Override
	public void increment(Vector vector) {
		incrementX(vector.getX());
		incrementY(vector.getY());
	}
	
}
