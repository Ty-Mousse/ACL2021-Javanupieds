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
	public void move(int dx, int dy) { /* Est-ce utile ? Les déplacements ne devraient pas être
	faits de cette manière il me semble. On détermine la position d'arrivée et on met à jour 
	x et y, il ne devrait pas y avoir de notion de distance de déplacement au sein de la classe
	Element. A discuter ! */
		x+=dx;
		y+=dy;
	}
	public boolean inHitBox(int x, int y) { /* différente pour chaque élément différent,
											donc à écrire dans les classes filles d'élément ? 
											Renvoie faux dans le doute (aucune hitbox) (Rémi) */
		/**
		 * Vérifie que le point (x,y) se trouve dans la hitbox de l'élément.
		 */
		return false
	}
	
	public List<List<Integer>> getHitBox(int x, int y){ /* idem inHitBox */
		/**
		 * Renvoie la liste des points qui forment la surface de la hitbox
		 * de l'élément s'il se trouve au point (x,y).
		 * 
		 * ATTENTION, on utilise pas la position réelle de l'élement (this.x, this.y)
		 * mais une position (x,y) théorique passée en paramètre. Permet de vérifier une
		 * mouvement avant de l'effectuer réellement (pas comme dans inHitBox).
		 */
		
		List<List<Integer>> HitBox = new List<List<Integer>>;
		return HitBox;
		
	}
	
}
