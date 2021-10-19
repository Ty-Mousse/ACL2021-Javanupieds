package rpgGame;

public class MobileElement extends Element {

	private int vx; // Vitesse en x de l'�l�ment
	private int vy; // Vitesse en y de l'�l�ment
	
	public MobileElement() {
		super();
		this.vx = 0;
		this.vy = 0;
	}

	public int getVx() {
		return vx;
	}

	public void setVx(int vx) {
		this.vx = vx;
	}

	public int getVy() {
		return vy;
	}

	public void setVy(int vy) {
		this.vy = vy;
	}
	
}
