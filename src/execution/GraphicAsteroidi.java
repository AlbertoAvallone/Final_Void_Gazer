package execution;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.List;

import game_objects.Asteroide;
import game_objects.Vector2D;

public class GraphicAsteroidi extends AbstractGraphicGameObject {
	List <Asteroide> as;
	public GraphicAsteroidi(List <Asteroide> as, ShipGraphicCoordinates sgc) {
		super(sgc);
		this.as=as;
	}

	@Override
	public void draw(Graphics g) {

		for(Asteroide a : as) {
	    
	    Vector2D pos=a.getV_pos();
	    Graphics2D g2 = (Graphics2D) g;

	    g2.setColor(Color.BLACK);
	    g2.draw(new Ellipse2D.Double(this.getSgc().getGraphicCoordinates(pos).getX()-a.getRadius(), this.getSgc().getGraphicCoordinates(pos).getY()-a.getRadius(), 2*a.getRadius(), 2*a.getRadius()));
	    
   }
}

}
