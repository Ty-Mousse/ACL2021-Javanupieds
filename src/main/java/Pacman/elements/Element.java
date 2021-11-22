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
	
	// Pourquoi move est dans Element ??? C'est plutôt dans mobile Element non ? 
	public void move(int dx, int dy) { /* Est-ce utile ? Les deplacements ne devraient pas etre
	faits de cette maniere il me semble. On determine la position d'arrivee et on met a jour 
	x et y, il ne devrait pas y avoir de notion de distance de deplacement au sein de la classe
	Element. A discuter ! */
		x+=dx;
		y+=dy;
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
