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
	public void move(int dx, int dy) { /* Est-ce utile ? Les d�placements ne devraient pas �tre
	faits de cette mani�re il me semble. On d�termine la position d'arriv�e et on met � jour 
	x et y, il ne devrait pas y avoir de notion de distance de d�placement au sein de la classe
	Element. A discuter ! */
		x+=dx;
		y+=dy;
	}
	public boolean inHitBox(int x, int y) { /* diff�rente pour chaque �l�ment diff�rent,
											donc � �crire dans les classes filles d'�l�ment ? 
											Renvoie faux dans le doute (aucune hitbox) (R�mi) */
		/**
		 * V�rifie que le point (x,y) se trouve dans la hitbox de l'�l�ment.
		 */
		return false
	}
	
	public List<List<Integer>> getHitBox(int x, int y){ /* idem inHitBox */
		/**
		 * Renvoie la liste des points qui forment la surface de la hitbox
		 * de l'�l�ment s'il se trouve au point (x,y).
		 * 
		 * ATTENTION, on utilise pas la position r�elle de l'�lement (this.x, this.y)
		 * mais une position (x,y) th�orique pass�e en param�tre. Permet de v�rifier une
		 * mouvement avant de l'effectuer r�ellement (pas comme dans inHitBox).
		 */
		
		List<List<Integer>> HitBox = new List<List<Integer>>;
		return HitBox;
		
	}
	
}
