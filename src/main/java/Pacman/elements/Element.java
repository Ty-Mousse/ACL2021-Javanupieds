package main.java.Pacman.elements;

public class Element {
	
	private int x; // Position en x de l'element
	private int y; // Position en y de l'element
	private char type; // Charactere definissant le type de l'element
	
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
	
	public void setType(char type) {
		this.type = type;
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
