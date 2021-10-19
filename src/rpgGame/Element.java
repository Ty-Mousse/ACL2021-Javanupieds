package rpgGame;

public class Element {
	
	private int x; // Position en x de l'élément
	private int y; // Position en y de l'élément
	private char type; // Charactère définissant le type de l'élément
	
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
