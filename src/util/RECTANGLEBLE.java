package util;


public abstract interface RECTANGLEBLE {
	
	public void incrementX(float amount);
	public void incrementY(float amount);
	
	public void move(float X, float Y);
	public void moveX1(float X1);
	public void moveX2(float X2);
	public void moveY1(float Y1);
	public void moveY2(float Y2);
	public void move(Vector vector);
	public void setWidth(float width);
	public void setHeight(float width);
	public void scale(float Xmultiplier, float Ymultiplier);
	
	public void centerIn(RECTANGLEBLE other, Direction dir);
	public void centerIn(float x1, float x2, float y1, float y2);
	public void centerAt(Vector c);
	public void centerAt(float X, float Y);
	public void centerX(float x1, float x2);
	public void centerY(float y1, float y2);
	
	public float getX1();
	public float getX2();
	public float getY1();
	public float getY2();
	public float getHeight();
	public float getWidth();
	public float getCentreX();
	public float getCentreY();
	
	//public boolean isWithin(RECTANGLEBLE other);
	public boolean touch(RECTANGLEBLE other);
	
	public void increment(Vector vector);
	
}
