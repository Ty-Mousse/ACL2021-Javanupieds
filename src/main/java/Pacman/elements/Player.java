package main.java.Pacman.elements;

public class Player extends MobileElement{
		
	private int lives = 3;
	
	public Player(int x, int y) {
		super(x, y, 1, '2');
		this.getInfranchissable().add('3'); //les ennemis sont infranchissables
	}

	public int getLives() {
		return lives;
	}
	
	public void setLives(int newLives) {
		this.lives = newLives;
	}
	
}
