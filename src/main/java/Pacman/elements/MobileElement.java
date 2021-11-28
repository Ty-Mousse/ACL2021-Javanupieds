package main.java.Pacman.elements;

public class MobileElement extends Element {

	private int v;
	
	public MobileElement(int x, int y, int v, char type) {
		super(x, y, type);
		this.v = v;
	}

	public int getV() {
		return v;
	}
	
	public boolean isFranchissable(char obstacle) {
		return true;
	}

}
