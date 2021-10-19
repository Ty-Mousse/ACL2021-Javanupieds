package rpgGame;

public class Element {
	
	private int x; // Position en x de l'�l�ment
	private int y; // Position en y de l'�l�ment
	private char type; // Charact�re d�finissant le type de l'�l�ment
	
	public Element() {
		this.x = 0;
		this.y = 0;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}
	
}
