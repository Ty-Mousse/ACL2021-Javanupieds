package main.java.Pacman.elements;

public class Player extends MobileElement{
		
	private int lives = 3;
	
	public Player(int x, int y) {
		super(x, y, 1, 1, '2');
	}

	public int getLives() {
		return lives;
	}
	
}
