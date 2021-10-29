package elements;

public class MobileElement extends Element {

	private int vx; // Vitesse en x de l'élément
	private int vy; // Vitesse en y de l'élément
	
	public MobileElement( int x, int y, int vx, int vy, char type) {
		super(x, y, type);
		this.vx = vx;
		this.vy = vy;
	}

	public int getVx() {
		return vx;
	}

	public int getVy() {
		return vy;
	}
	
}
