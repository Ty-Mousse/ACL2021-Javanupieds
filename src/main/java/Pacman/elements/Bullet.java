package main.java.Pacman.elements;

public class Bullet extends MobileElement{
	private int dx;
	private int dy;
	
	public Bullet(int x, int y, int dx, int dy) {
		super(x, y, 2, 'p');
		this.dx = dx;
		this.dy = dy;
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
