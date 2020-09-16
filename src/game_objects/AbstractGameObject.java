package game_objects;


public abstract class AbstractGameObject implements GameObject{
	
	private Vector2D v_pos; 
	private Vector2D v_speed;
	
	protected AbstractGameObject(Vector2D v_pos, Vector2D v_speed) {
		this.v_pos=v_pos;
		this.v_speed=v_speed;
	}
	
	/* update method */
	public void update() {
		this.setX(this.getX()+this.getSpeedX());
		this.setY(this.getY()+this.getSpeedY());
		
	}
	
	/* getter and setters */
	
	public Vector2D getV_pos() {
		return v_pos;
	}


	public void setV_pos(Vector2D v_pos) {
		this.v_pos = new Vector2D(v_pos);
	}


	public Vector2D getV_speed() {
		return v_speed;
	}


	public void setV_speed(Vector2D v_speed) {
		this.v_speed = new Vector2D(v_speed);
	}


	public double getX() {
		return v_pos.getX();
	}

	
	public double getY() {
		
		return v_pos.getY();
	}

	
	public void setX(double x) {
		this.v_pos.setX(x);
	}

	@Override
	public void setY(double y) {	
		this.v_pos.setY(y);
		
	}

	@Override
	public double getSpeedX() {
		
		return this.v_speed.getX();
	}

	@Override
	public double getSpeedY() {
		return this.v_speed.getY();
	}

	@Override
	public void setSpeedX(double sx) {
		this.v_speed.setX(sx);
		
	}

	@Override
	public void setSpeedY(double sy) {
		this.v_speed.setY(sy);
		
	}
	
	
}
