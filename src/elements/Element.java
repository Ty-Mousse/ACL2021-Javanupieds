package elements;

public class Element {
	
	private int x; // Position en x de l'élément
	private int y; // Position en y de l'élément
	private char type; // Charactère définissant le type de l'élément
	
	public Element(int x, int y, char type) {
		this.x = x;
		this.y = y;
		this.type = type;
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
	
}
