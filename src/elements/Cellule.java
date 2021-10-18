package elements;

public class Cellule {
	
	private int x;
	private int y;
	private int value;
	
	public Cellule(int x, int y, int value) {
		this.x = x;
		this.y = y;
		this.value = value;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return "(" + this.x + ", " + this.y + ", " + this.value + ")";
	}
}
