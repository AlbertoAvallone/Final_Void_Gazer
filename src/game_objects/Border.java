package game_objects;

import java.awt.geom.Rectangle2D;

public class Border extends AbstractGameObject {
	private Rectangle2D bounds;


	public Border(Vector2D v_pos, Vector2D v_speed, double width,double height) {
		super(v_pos, v_speed);
		v_speed=new Vector2D(0,0);
		this.bounds=new Rectangle2D.Double(v_pos.getX()-0.5*width, v_pos.getY()-0.5*height, width, height);
		
	}
	
	/* getters and setters */
	public Rectangle2D getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle2D bounds) {
		this.bounds = bounds;
	}

}
