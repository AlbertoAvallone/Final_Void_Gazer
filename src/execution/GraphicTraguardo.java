package execution;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

import game_objects.Traguardo;
import game_objects.Vector2D;

public class GraphicTraguardo extends AbstractGraphicGameObject{
	
	private Traguardo t;
	
	protected GraphicTraguardo(ShipGraphicCoordinates sgc, Traguardo t) {
		super(sgc);
		this.t = t;
		
	}

	@Override
	public void draw(Graphics g) {
		
		/* getting Traguardo vectors*/
		Vector2D v[]=t.getVertici();
		int i=0;
		int x[]=new int[3];
		int y[]=new int[3];
		
		for(Vector2D s:v) {
			v[i]=this.getSgc().getGraphicCoordinates(s);
			++i;
		}
		
		
		/* getting coordinates of v*/
		i=0;
		for(int j=1;j<4;++j ) {
			x[i]=(int) v[j].getX();
			y[i]=(int) v[j].getY();
			++i;
			
			
		}
		
		/* drawing each poligon that made the traguardo */
		Graphics2D g2=(Graphics2D) g;
		g2.draw(new Polygon(x,y,3));
		g2.drawLine((int) v[0].getX(),(int)  v[0].getY(),(int) v[3].getX(),(int) v[3].getY());
		g2.drawLine((int) v[4].getX(),(int)  v[4].getY(),(int) v[5].getX(),(int) v[5].getY());
		return;
	}

}
