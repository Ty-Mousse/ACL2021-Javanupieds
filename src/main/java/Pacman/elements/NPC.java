package main.java.Pacman.elements;

import java.util.Random;

public class NPC extends MobileElement {
	int dx;
	int dy;
	
	
	public NPC(int x, int y) {
		super(x, y, 1, '3'); //le '3' est a modifer, c'etait en attendant
		
	}
	
	public int[] deplacementRandom(){
		Random rn = new Random();
		int[] dir = {0,0};
		int newdx;
		int newdy;
		boolean invalid = true;
		
		while (invalid) { // tourne a l'infini,Remi doit corriger
			newdx = rn.nextInt(3)-1;
			newdy = rn.nextInt(3)-1;
			
			
			if ((newdx!=0 & newdy==0) | (newdx==0 & newdy!=0)) {

				invalid=false;
				dir[0] = newdx;
				dir[1] = newdy;
			}			
		}
		return dir;
	}
	
	public int getdx() {
		return this.dx;
	}
	
	public int getdy() {
		return this.dy;
	}
	
	public void setdx(int dx) {
		this.dx = dx;
	}
	
	public void setdy(int dy) {
		this.dy = dy;
	}
}
