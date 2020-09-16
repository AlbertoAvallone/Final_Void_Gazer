package game_objects;


public class Traguardo extends AbstractGameObject{

	private int size ;


	public Traguardo(Vector2D v_pos, Vector2D v_speed, int size) {
		super(v_pos, v_speed);
		this.setV_speed(new Vector2D(0,0));
		this.size = size;
	}
	public Vector2D[] getVertici() {
		Vector2D a=new Vector2D(this.getV_pos());
		Vector2D b= new Vector2D(this.getV_pos().add(new Vector2D(0,-this.size)));
		Vector2D c= new Vector2D(this.getV_pos().add(new Vector2D(0.5*this.size,-0.75*this.size)));
		Vector2D d= new Vector2D(this.getV_pos().add(new Vector2D(0,-0.5*size)));
		Vector2D e= new Vector2D(this.getV_pos().add(new Vector2D(-0.1*size,0)));
		Vector2D f= new Vector2D(this.getV_pos().add(new Vector2D(0.1*size,0)));

		Vector2D[] v= {a,b,c,d,e,f};
		return v;
	}

	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}

}
