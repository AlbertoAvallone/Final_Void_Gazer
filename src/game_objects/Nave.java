package game_objects;

import java.awt.Graphics2D;
import java.awt.geom.Line2D.Double;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;
import execution.Resolution;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.Color;
import java.awt.Graphics;


public class Nave extends AbstractGameObject {
	
	private double pitch;
	private JPanel parent;
	private double carburante;
	private double thrustForce;
	private double size;
	

	private double collisionSphere1DistanceRatio=-0.079;
    private double collisionSphere2DistanceRatio=0.354;
    private double collisionSphere3DistanceRatio=0.613115;

   /* private double collisionSphere1RadiusRatio=0.271;
    private double collisionSphere2RadiusRatio=0.162;
    private double collisionSphere3RadiusRatio=0.097115;
    */
    private double RadiusRatioVector[]= {0.271,0.162,0.097115};
	
	public double getLightRadius() {
		return this.carburante;
	}
	
	
	public Nave(Vector2D v_pos, Vector2D v_speed, double pitch, double size, JPanel parent, double thrustForce, double carburante) {
		super(v_pos,v_speed);
		this.pitch=pitch;
		this.parent=parent;
		this.carburante = carburante;
		this.thrustForce = thrustForce;
		this.size=size;
		return;
	}
	
	
	public Vector2D[] vertici() {
		Vector2D a = new Vector2D((this.getX()+ Math.cos(this.pitch*Math.PI)*this.size), (this.getY() - this.size*Math.sin(this.pitch*Math.PI)));
		Vector2D b = new Vector2D((this.getX()+Math.cos((this.pitch+0.75)*Math.PI)*this.size*0.5), (this.getY()-Math.sin((this.pitch+0.75)*Math.PI)*this.size*0.5)); 
		Vector2D c = new Vector2D((this.getX()+Math.cos((this.pitch-0.75)*Math.PI)*this.size*0.5), (this.getY()-Math.sin((this.pitch-0.75)*Math.PI)*this.size*0.5));
		Vector2D[] s = {a,b,c};
		return s;
	}
	
	public boolean collision(Asteroide a) {

        Vector2D sfera1centro,sfera2centro,sfera3centro;
        sfera1centro=this.getV_pos().add(new Vector2D(-Math.cos(this.pitch*Math.PI)*this.collisionSphere1DistanceRatio*size,Math.sin(this.pitch*Math.PI)*this.collisionSphere1DistanceRatio*size));
        sfera2centro=this.getV_pos().add(new Vector2D(Math.cos(this.pitch*Math.PI)*this.collisionSphere2DistanceRatio*size,-Math.sin(this.pitch*Math.PI)*this.collisionSphere2DistanceRatio*size));
        sfera3centro=this.getV_pos().add(new Vector2D(Math.cos(this.pitch*Math.PI)*this.collisionSphere3DistanceRatio*size,-Math.sin(this.pitch*Math.PI)*this.collisionSphere3DistanceRatio*size));
        Vector2D sfere[]= {sfera1centro,sfera2centro,sfera3centro};
        
        int i=0;
        for(Vector2D v:sfere) {
            Vector2D vettoredistanza=v.sub(a.getV_pos());

            double distanza=Math.sqrt(Math.pow(vettoredistanza.getX(), 2)+Math.pow(vettoredistanza.getY(), 2));
            double somma=this.RadiusRatioVector[i]*this.size+a.getRadius();
            if(somma>=distanza) {
                return true;
            }

            ++i;

        }

        return false;
    }
	
	
	public boolean collision(Traguardo t) {
        Vector2D sfera1centro,sfera2centro,sfera3centro;
        sfera1centro=this.getV_pos().add(new Vector2D(-Math.cos(this.pitch* Math.PI)*this.collisionSphere1DistanceRatio*size,Math.sin(this.pitch*Math.PI)*this.collisionSphere1DistanceRatio*size));
        sfera2centro=this.getV_pos().add(new Vector2D(Math.cos(this.pitch* Math.PI)*this.collisionSphere2DistanceRatio*size,-Math.sin(this.pitch*Math.PI)*this.collisionSphere2DistanceRatio*size));
        sfera3centro=this.getV_pos().add(new Vector2D(Math.cos(this.pitch*Math.PI)*this.collisionSphere3DistanceRatio*size,-Math.sin(this.pitch*Math.PI)*this.collisionSphere3DistanceRatio*size));
        Vector2D sfere[]= {sfera1centro,sfera2centro,sfera3centro};
        
        int i=0;
        Vector2D vertici[]=t.getVertici();
        for(Vector2D v:sfere) {

            Rectangle2D r2= new Rectangle2D.Double(vertici[1].getX(), vertici[1].getY(),0.5*t.getSize() , t.getSize());
            Ellipse2D s=new Ellipse2D.Double(v.getX()-this.RadiusRatioVector[i]*this.size, v.getY() - this.RadiusRatioVector[i]*this.size,2*this.RadiusRatioVector[i]*this.size,2*this.RadiusRatioVector[i]*this.size);
            Rectangle2D r1=s.getBounds2D();
            if(r1.intersects(r2)) {
                return true;
            }
            ++i;
        }

        return false;

    }
	
	// outdated
	public boolean collision(Border b) {
        Vector2D sfera1centro,sfera2centro,sfera3centro;
        sfera1centro=this.getV_pos().add(new Vector2D(-Math.cos(this.pitch * Math.PI)*this.collisionSphere1DistanceRatio*size,Math.sin(this.pitch*Math.PI)*this.collisionSphere1DistanceRatio*size));
        sfera2centro=this.getV_pos().add(new Vector2D(Math.cos(this.pitch * Math.PI)*this.collisionSphere2DistanceRatio*size,-Math.sin(this.pitch*Math.PI)*this.collisionSphere2DistanceRatio*size));
        sfera3centro=this.getV_pos().add(new Vector2D(Math.cos(this.pitch * Math.PI)*this.collisionSphere3DistanceRatio*size,-Math.sin(this.pitch*Math.PI)*this.collisionSphere3DistanceRatio*size));

        Vector2D sfere[]= {sfera1centro,sfera2centro,sfera3centro};
        int i=0;

        for(Vector2D v:sfere) {


            Ellipse2D s=new Ellipse2D.Double(v.getX()-this.RadiusRatioVector[i]*this.size, v.getY()-this.RadiusRatioVector[i]*this.size,2*this.RadiusRatioVector[i]*this.size,2*this.RadiusRatioVector[i]*this.size);

            if(!s.getBounds2D().intersects(b.getBounds())) {
                return true;
            }
            ++i;
        }

        return false;

    }
	
	
	public boolean collisionVert(Border b) {
		Vector2D sfera1centro,sfera2centro,sfera3centro;
        sfera1centro=this.getV_pos().add(new Vector2D(-Math.cos(this.pitch*Math.PI)*this.collisionSphere1DistanceRatio*size,Math.sin(this.pitch*Math.PI)*this.collisionSphere1DistanceRatio*size));
        sfera2centro=this.getV_pos().add(new Vector2D(Math.cos(this.pitch*Math.PI)*this.collisionSphere2DistanceRatio*size,-Math.sin(this.pitch*Math.PI)*this.collisionSphere2DistanceRatio*size));
        sfera3centro=this.getV_pos().add(new Vector2D(Math.cos(this.pitch*Math.PI)*this.collisionSphere3DistanceRatio*size,-Math.sin(this.pitch*Math.PI)*this.collisionSphere3DistanceRatio*size));

        Vector2D sfere[]= {sfera1centro,sfera2centro,sfera3centro};
        int i=0;
        Line2D l[]= {new Line2D.Double(new Point2D.Double(b.getBounds().getMaxX(), b.getBounds().getMinY()),new Point2D.Double(b.getBounds().getMaxX(), b.getBounds().getMaxY()) ), new Line2D.Double(new Point2D.Double(b.getBounds().getMinX(), b.getBounds().getMaxY()),new Point2D.Double(b.getBounds().getMinX(), b.getBounds().getMinY()) )};
        for(Vector2D v:sfere) {
        	
        	
        	Ellipse2D s=new Ellipse2D.Double(v.getX()-this.RadiusRatioVector[i]*this.size,v.getY()-this.RadiusRatioVector[i]*this.size,2*this.RadiusRatioVector[i]*this.size,2*this.RadiusRatioVector[i]*this.size);
        	
        	for(Line2D o:l) {
        		if(s.getBounds2D().intersectsLine(o)){
        			return true;
        		}
        	}
        }
        return false;
	}
	
	public boolean collisionOrizz(Border b) {
		Vector2D sfera1centro,sfera2centro,sfera3centro;
        sfera1centro=this.getV_pos().add(new Vector2D(-Math.cos(this.pitch*Math.PI)*this.collisionSphere1DistanceRatio*size,Math.sin(this.pitch*Math.PI)*this.collisionSphere1DistanceRatio*size));
        sfera2centro=this.getV_pos().add(new Vector2D(Math.cos(this.pitch*Math.PI)*this.collisionSphere2DistanceRatio*size,-Math.sin(this.pitch*Math.PI)*this.collisionSphere2DistanceRatio*size));
        sfera3centro=this.getV_pos().add(new Vector2D(Math.cos(this.pitch*Math.PI)*this.collisionSphere3DistanceRatio*size,-Math.sin(this.pitch*Math.PI)*this.collisionSphere3DistanceRatio*size));

        Vector2D sfere[]= {sfera1centro,sfera2centro,sfera3centro};
        int i=0;
        Line2D l[]= {new Line2D.Double(new Point2D.Double(b.getBounds().getMaxX(), b.getBounds().getMinY()),new Point2D.Double(b.getBounds().getMinX(), b.getBounds().getMinY()) ), new Line2D.Double(new Point2D.Double(b.getBounds().getMinX(), b.getBounds().getMaxY()),new Point2D.Double(b.getBounds().getMaxX(), b.getBounds().getMaxY()) )};
        for(Vector2D v:sfere) {
        	
        	
        	Ellipse2D s=new Ellipse2D.Double(v.getX()-this.RadiusRatioVector[i]*this.size,v.getY()-this.RadiusRatioVector[i]*this.size,2*this.RadiusRatioVector[i]*this.size,2*this.RadiusRatioVector[i]*this.size);
        	
        	for(Line2D o:l) {
        		if(s.getBounds2D().intersectsLine(o)){
        			return true;
        		}
        	}
        }
        return false;
	}
	
	
	
	
	public void thrustOn() {
		
		
		this.setSpeedX((getSpeedX()+this.thrustForce*0.01667*Math.cos(getpitch()*Math.PI)));
		this.setSpeedY((getSpeedY()-this.thrustForce*0.01667*Math.sin(getpitch()*Math.PI)));
		
		return;
	}
	
	
	public void thrustOff(Graphics g) {
		return;
	}
	
	
	public double getpitch() {
		return this.pitch;
	}
	
	
	public void setpitch(double pitch) {
		this.pitch=pitch;
	}
	
	public Vector2D getGraphicCoordinates(Vector2D v_pos, Resolution r) {
		v_pos.add(new Vector2D(this.getX()-r.getPixelsWide()*0.5,this.getY()-r.getPixelsHigh()));
		
		
		return v_pos;
	}
	
	public void draw(Graphics g,Resolution r) {
		g.translate((int) this.getGraphicCoordinates(this.getV_pos(), r).getX(),(int) this.getGraphicCoordinates(this.getV_pos(), r).getX());
		Graphics2D g2 = (Graphics2D) g;
		Vector2D[] v = this.vertici();
		Double l1 = new Double(v[0].getX(),v[0].getY(),v[1].getX(),v[1].getY());
		Double l2 = new Double(v[1].getX(),v[1].getY(),v[2].getX(),v[2].getY());
		Double l3 = new Double(v[0].getX(),v[0].getY(),v[2].getX(),v[2].getY());
		g2.setColor(Color.BLACK);
		g2.draw(l1);
		g2.draw(l2);
		g2.draw(l3);
		return;
	}

	

	/* getters and setters */
	public JPanel getParent() {
		return parent;
	}


	public void setParent(JPanel parent) {
		this.parent = parent;
	}


	public double getSize() {
		return size;
	}


	public void setSize(double size) {
		this.size = size;
	}
	

	
	
}
