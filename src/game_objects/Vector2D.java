package game_objects;

public class Vector2D {
	private double x;
	private double y;

	
	public Vector2D(double x, double y) {
		this.x = x;
		this.y = y;
	}
	public Vector2D(Vector2D v) {
		this.x = v.x;
		this.y = v.y;
	}
	
	/* static method to calculate the distance between two points in the space*/
	public static double distance(Vector2D max, Vector2D min) {

		double ris = Math.sqrt(Math.pow(max.x-min.x, 2) + Math.pow(max.y - min.y, 2)); 
		return ris;
	}

	/*base operations*/
	public Vector2D add(Vector2D v) {
		return new Vector2D(x + v.x, y + v.y);
	}

	public Vector2D sub(Vector2D v) {
		return new Vector2D(x - v.x, y - v.y);
	}

	public Vector2D multiply(int f) {
		return new Vector2D(x * f, y * f);
	}

	public Vector2D normalize() {
		int l = (int) Math.sqrt((x * x) + (y * y));
		return new Vector2D(x / l, y / l);
	}

	public double dot(Vector2D v) {
		return x * v.x + y * v.y;
	}

	public double length() {
		return  Math.sqrt((x * x) + (y * y));
	}

	public double lengthSquared() {
		return (x * x) + (y * y);
	}
	
	/*getters and setters */
	
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}

}
