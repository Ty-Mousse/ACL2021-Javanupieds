package main.java.Pacman.elements;

public class MobileElement extends Element {

	private int vx; // Vitesse en x de l'element
	private int vy; // Vitesse en y de l'element
	
	public MobileElement(int x, int y, int vx, int vy, char type) {
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
