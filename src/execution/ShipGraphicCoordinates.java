package execution;
import game_objects.*;

/**
 * important class that calculates a translation between the world coordinate system and the graphic coordinate system which can therefore be calculated relative to the ship; 
 * @author frenc
 *
 */
public class ShipGraphicCoordinates {
	private Vector2D origin;
	private Nave n;
	private Resolution r;

	public ShipGraphicCoordinates(Nave n, Resolution r) {
		super();
		this.origin = new Vector2D(n.getX()-r.getPixelsWide()*0.5,n.getY()-r.getPixelsHigh()*0.5);
		this.n = n;
		this.r = r;
	}
	
	/* updating method*/
	public void update() {
		this.origin = new Vector2D(this.n.getX()-this.r.getPixelsWide()*0.5,n.getY()-this.r.getPixelsHigh()*0.5);
	}
	
	
	/* getters */
	public double getGraphicCoordinatesX(double x ) {
		return x-origin.getX();
	}
	
	public double getGraphicCoordinatesY(double y ) {
		return y-origin.getY();
	}
	
	public Vector2D getGraphicCoordinates(Vector2D v) {
		return new Vector2D(v.sub(origin));
	}
	
}
