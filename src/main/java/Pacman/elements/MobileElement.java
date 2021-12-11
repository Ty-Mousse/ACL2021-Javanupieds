package main.java.Pacman.elements;

import java.util.ArrayList;
import java.util.List;

public class MobileElement extends Element {
	
	private int v;
	private List<Object> infranchissable = new ArrayList<>();
	
	public MobileElement(int x, int y, int v, char type) {
		super(x, y, type);
		this.v = v;
		this.infranchissable.add('#');
	}

	public int getV() {
		return v;
	}
	
	public boolean isFranchissable(char obstacle) {
		Boolean franchissable = true;
		if (this.infranchissable.contains(obstacle)) {
			franchissable = false;
		}
		else {
		}
		return franchissable;
	}

}
