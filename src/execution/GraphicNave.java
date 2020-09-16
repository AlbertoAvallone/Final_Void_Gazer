package execution;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Ellipse2D;

import game_objects.Nave;
import game_objects.Vector2D;

/**
 * the graphic nave is the graphic version of nave, it contains the nave object that must be instantiated before the creation of its graphic version
 * 
 * @author frenc
 *
 */
public class GraphicNave extends AbstractGraphicGameObject implements GraphicGameObject{
	
	private Nave n;
	private Graphics2D g2;
	
	public GraphicNave(Nave n,ShipGraphicCoordinates sgc) {
		super(sgc);
		this.n=n;
	}

	
	public void thrustGraphicOn(Graphics g) {
		//n.thrustOn();
		
		g2 = (Graphics2D) g;
		g2.draw(new Ellipse2D.Double(this.getSgc().getGraphicCoordinatesX(n.getV_pos().getX()-Math.cos(n.getpitch()*Math.PI)*0.6*n.getSize() - n.getSize()*0.2),this.getSgc().getGraphicCoordinatesY(n.getV_pos().getY()+Math.sin(n.getpitch()*Math.PI)*n.getSize()*0.6 - n.getSize()*0.2),n.getSize() * 0.2 * 2,n.getSize() * 0.2 * 2));
		g2.draw(new Ellipse2D.Double(this.getSgc().getGraphicCoordinatesX(n.getV_pos().getX()-Math.cos(n.getpitch()*Math.PI)*n.getSize() - n.getSize()*0.1),this.getSgc().getGraphicCoordinatesY(n.getV_pos().getY()+Math.sin(n.getpitch()*Math.PI)*n.getSize() - n.getSize()*0.1),n.getSize() * 0.1 * 2,n.getSize() * 0.1 * 2));	
	}

	
	@Override
	public void draw(Graphics g) {
		
		/* getting Nave points */
		Vector2D[] va = n.vertici();
		int[] xpoints = new int[3];
		int[] ypoints = new int[3];
		int i = 0;
		
		/* getting each point vector*/
		for(Vector2D v : va) {
			v=this.getSgc().getGraphicCoordinates(v);
			xpoints[i]=(int) v.getX();
			ypoints[i]=(int) v.getY();
			++i;
		}
		
		Polygon p = new Polygon(xpoints, ypoints, 3);
		Graphics2D g2 = (Graphics2D) g;
		
		/* setting color of her light radius*/
		g2.setColor(Color.WHITE);
		g2.fill(new Ellipse2D.Double(this.getSgc().getGraphicCoordinatesX(n.getX()) - n.getLightRadius(), this.getSgc().getGraphicCoordinatesY(n.getY())- n.getLightRadius(), 2*n.getLightRadius(), 2*n.getLightRadius()));
		
		/* drawing herself */
		g2.setColor(Color.BLACK);
		g2.draw(p);
		
		
		
	}
	
}
