package game_objects;

public interface GameObject {
	
	public void update();
	public double getX();
	public double getY();
	public void setX(double x);
	public void setY(double y);
	public double getSpeedX();
	public double getSpeedY();
	public void setSpeedX(double sx);
	public void setSpeedY(double sy);
	
	
}
