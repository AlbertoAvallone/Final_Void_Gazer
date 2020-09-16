package execution;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;

import DBase.LevelLoad;
import game_objects.Asteroide;
import game_objects.Border;
import game_objects.Nave;
import game_objects.Traguardo;
import game_objects.Vector2D;

public class Level {
	private List <Asteroide> a, a_o;
	private Nave n, n_o;
	private Traguardo t, t_o;
	private Border b, b_o;
	private LevelLoad l;
	private JPanel p;
	
	
	
	public Level (JPanel p, LevelLoad l) {
		super();
		this.l = l;
		this.p = p;
		
	}
	
	/* extraction of each game object */
	public void NaveLevel (ResultSet risN) {
		Vector2D v_pos;
		try {
			v_pos = new Vector2D(risN.getDouble("xx"),risN.getDouble("yy"));
			Vector2D v_speed = new Vector2D(0, 0);
			Vector2D v_pos_n_o = new Vector2D(v_pos);	
			Vector2D v_speed_n_o = new Vector2D(v_speed);	
			
			n = new Nave(v_pos, v_speed, 0.5, 20, p, 10.0, risN.getDouble("fuel"));
			n_o = new Nave(v_pos_n_o, v_speed_n_o, 0.5, 20, p, 10.0, risN.getDouble("fuel"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error while extracting the Nave");
			e.printStackTrace();
		}
		
	}
	
	public void AsteroideLevel (ResultSet risA)  
	{
		a = new  ArrayList <Asteroide>();
		a_o = new  ArrayList <Asteroide>();
		try {
			while(risA.next()){
				Vector2D v_pos = new Vector2D(risA.getDouble("x"), risA.getDouble("y"));
				Vector2D v_speed = new Vector2D(risA.getDouble("v_x"), risA.getDouble("v_y"));
				Vector2D v_pos_a_o = new Vector2D(v_pos);	
				Vector2D v_speed_a_o = new Vector2D(v_speed);	
				
				a.add(new Asteroide(v_pos, v_speed, risA.getDouble("radius"), p));
				a_o.add(new Asteroide(v_pos_a_o, v_speed_a_o, risA.getDouble("radius"), p));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error while extracting the Asteroide");
			e.printStackTrace();
			
		};
			
	}
	public void TraguardoLevel (ResultSet risT)  {
		Vector2D v_pos;
		Vector2D v_pos_t_o;
		try {
			v_pos = new Vector2D (risT.getDouble("xxx"), risT.getDouble("yyy"));
			v_pos_t_o = new Vector2D (risT.getDouble("xxx"), risT.getDouble("yyy"));
			t = new Traguardo(v_pos, new Vector2D(0.0, 0.0), 50);
			t_o = new Traguardo(v_pos_t_o, new Vector2D(0.0, 0.0), 50);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error while extracting the Traguardo");
			e.printStackTrace();
		}
		
		
		
	}
	public void BorderLevel (ResultSet risB) {
		try {
			double height = risB.getDouble("height");
			double width = risB.getDouble("width");
			double x = risB.getDouble("x");
			double y = risB.getDouble("x");
			b = new Border (new Vector2D(x, y), new Vector2D(0, 0), width, height);
			b_o = new Border (new Vector2D(x, y), new Vector2D(0, 0), width, height);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error while extracting the Border");
			e.printStackTrace();
		}
		
	}
	
	
	/* resetting position method*/
	public void reset() {
		Iterator <Asteroide> it = a_o.iterator();
		int i = 0;
		while(it.hasNext()) {
			Asteroide ast = it.next();
			a.get(i).setV_pos(ast.getV_pos());
			a.get(i).setV_speed(ast.getV_speed());
			++i;
		}
	
		n.setV_pos(n_o.getV_pos());
		n.setV_speed(n_o.getV_speed());
		n.setpitch(n_o.getpitch());
		
		t.setV_pos(t_o.getV_pos());
	}
	
	/* getters and setters */
	public List<Asteroide> getA() {
		return a;
	}

	public void setA(List<Asteroide> a) {
		this.a = a;
	}

	public List<Asteroide> getA_o() {
		return a_o;
	}

	public void setA_o(List<Asteroide> a_o) {
		this.a_o = a_o;
	}
	
	public Nave getN() {
		return n;
	}

	public void setN(Nave n) {
		this.n = n;
	}

	public Nave getN_o() {
		return n_o;
	}

	public void setN_o(Nave n_o) {
		this.n_o = n_o;
	}

	public LevelLoad getL() {
		return l;
	}

	public void setL(LevelLoad l) {
		this.l = l;
	}

	public JPanel getP() {
		return p;
	}

	public void setP(JPanel p) {
		this.p = p;
	}

	public Traguardo getT() {
		return t;
	}

	public void setT(Traguardo t) {
		this.t = t;
	}

	public Traguardo getT_o() {
		return t_o;
	}

	public void setT_o(Traguardo t_o) {
		this.t_o = t_o;
	}

	public Border getB() {
		return b;
	}

	public void setB(Border b) {
		this.b = b;
	}

	public Border getB_o() {
		return b_o;
	}

	public void setB_o(Border b_o) {
		this.b_o = b_o;
	}
}
