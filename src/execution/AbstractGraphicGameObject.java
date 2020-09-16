package execution;


/**
 * AbstractGraphicGameObject implements the GraphicGameObject interface, its aim is to separate the ideal map coordinate system from the graphic coordinate system,
 * this way the Gameobjects dont have to ask anyhting to the panel and the database of levels which collects informations on many objects works with the same coordinate system of this 
 * imaginary world coordinate system.
 * @author frenc
 *
 */
public abstract class AbstractGraphicGameObject implements GraphicGameObject{
	private ShipGraphicCoordinates sgc;

	/**
	 * sgc must be initialized already with valid ship object, go is the object of which you want the Graphic Coordinates relative to the ship;
	 */
	protected AbstractGraphicGameObject(ShipGraphicCoordinates sgc) {
		
		this.sgc = sgc;
	}

	
	
	public ShipGraphicCoordinates getSgc() {
		return sgc;
	}


	public void setSgc(ShipGraphicCoordinates sgc) {
		this.sgc = sgc;
	}


	
}
