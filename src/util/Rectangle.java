package util;

public class Rectangle extends ARECTANGLE{
	
	public Rectangle (float x1, float x2, float y1, float y2){
		super(x1, x2, y1, y2);
	}
	
	/**
	 * 
	 * @param other get copy of this
	 */
	public Rectangle (ARECTANGLE other){
		super(other);
	}
	
	public Rectangle (float width, float height, float x1, float x2, float y1, float y2){
		super(width, height, x1, x2, y1, y2);
	}
	
	
//	public void setX1(float x) {coordinates[0] = x;}
//	public void setX2(float x) {coordinates[1] = x;}
//	public void setY1(float y) {coordinates[2] = y;}
//	public void setY2(float y) {coordinates[3] = y;}

}
