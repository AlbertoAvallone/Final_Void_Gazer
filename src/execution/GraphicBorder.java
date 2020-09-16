package execution;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import game_objects.Border;

public class GraphicBorder extends AbstractGraphicGameObject{
	private Border b;
	private Rectangle2D bgrafici;
	private Graphics2D g2;
	
	protected GraphicBorder(ShipGraphicCoordinates sgc,Border b ) {
		super(sgc);
		this.b=b;
	}
	@Override
	public void draw(Graphics g) {
	        bgrafici=new Rectangle2D.Double(this.getSgc().getGraphicCoordinatesX(b.getBounds().getX()),this.getSgc().getGraphicCoordinatesY(b.getBounds().getY()) , b.getBounds().getWidth(), b.getBounds().getHeight());
	        g2=(Graphics2D) g;
	        g2.setColor(Color.BLACK);
	        g2.draw(bgrafici);

	    }
	
	
}
