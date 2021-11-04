package elements;

public class Element {
	
	private int x; // Position en x de l'�l�ment
	private int y; // Position en y de l'�l�ment
	private char type; // Charact�re d�finissant le type de l'�l�ment
	
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
	public void move(int dx, int dy) {
		x+=dx;
		y+=dy;
	}
	public boolean inHitBox(int x, int y) { /* diff�rente pour chaque �l�ment diff�rent,
											donc � �crire dans les classes filles d'�l�ment ? 
											Renvoie faux dans le doute (aucune hitbox) (R�mi) */
		return false
	}
	
}
