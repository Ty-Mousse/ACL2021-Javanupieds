package elements;

public class Element {
	
	private int x; // Position en x de l'�l�ment
	private int y; // Position en y de l'�l�ment
	private char type; // Charact�re d�finissant le type de l'�l�ment
	
	public Element(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public char getType() {
		return type;
	}
	public void move(int dx, int dy) {
		x+=dx;
		y+=dy;
	}
	public boolean isCollision() {
		
	}
	
}
