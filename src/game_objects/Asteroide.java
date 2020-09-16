package game_objects;
import javax.swing.JPanel;
import java.awt.geom.Ellipse2D;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Asteroide extends AbstractGameObject{
	private double radius;
	private JPanel parent;
	
	public Asteroide(Vector2D v_pos, Vector2D v_speed, double radius, JPanel parent) {
		super(v_pos,v_speed);
		this.radius=radius;
		this.parent = parent;
	}

	
	public boolean collision(Nave g1, Asteroide g2) {
		//TODO
		return false;
	}



	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLACK);
		g2.draw(new Ellipse2D.Double(this.getX()-this.radius, this.getY()-this.radius, 2*this.radius, 2*this.radius));
	}


	/* Getters and setters*/

	public double getRadius() {
		return radius;
	}


	public void setRadius(double radius) {
		this.radius = radius;
	}


	public JPanel getParent() {
		return parent;
	}


	public void setParent(JPanel parent) {
		this.parent = parent;
	}

}
