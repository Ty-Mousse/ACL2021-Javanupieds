package main.java.Pacman.elements;

import java.util.Random;

public class NPC extends MobileElement {
	
	
	public NPC(int x, int y) {
		super(x, y, 1, '3'); //le '3' est a modifer, c'etait en attendant
		
	}
	
	public int[] deplacementRandom(Level level){
		Random rn = new Random();
		int dx;
		int dy;
		boolean bloque = true;
		
		while (bloque) {
			dx = rn.nextInt(1);
			dy = rn.nextInt(1);
			
			if (dx!=0 & dy==0 | dx==0 & dy!=0) {
				
				bloque=false;
				int[] dir = {dx,dy};	
			}
			
		}
		
		return dir;
	}
}
