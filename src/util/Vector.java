package util;

public class Vector {

	float X;
	float Y;
	
	public Vector(float x, float y){
		X = x;
		Y = y;
	}
	
	public float getX(){return X;}
	public float getY(){return Y;}
	
	public void setX(float x){X = x;}
	public void setY(float y){Y = y;}
	public void incrementX(float amout){X += amout;}
	public void incrementY(float amount){Y += amount;}
	public void increment(Vector other) {
		X += other.X;
		Y += other.Y;
	}
	public void reverseX(){X *= -1f;}
	public void reverseY(){Y *= -1f;}
	public boolean isWithin(ARECTANGLE other){
		if (X <= other.getX1() || X > other.getX2() || Y <= other.getY1() || Y > other.getY2())
			return false;
		return true;
	}
	
}