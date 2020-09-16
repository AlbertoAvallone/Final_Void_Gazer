package execution;

public class Resolution {	
	private int pixelsWide;
	private int pixelsHigh;
	
	public Resolution(int pixelsWide, int pixelsHigh) {
		super();
		this.pixelsWide = pixelsWide;
		this.pixelsHigh = pixelsHigh;
	}

	/* getter and setters */
	public int getPixelsWide() {
		return pixelsWide;
	}

	public void setPixelsWide(int pixelsWide) {
		this.pixelsWide = pixelsWide;
	}

	public int getPixelsHigh() {
		return pixelsHigh;
	}

	public void setPixelsHigh(int pixelsHigh) {
		this.pixelsHigh = pixelsHigh;
	}
	
}
