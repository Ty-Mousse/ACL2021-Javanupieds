package main.java.Pacman.elements;

import java.util.ArrayList;
import java.util.List;

public class MobileElement extends Element {
	
	private int v;
	private List<Object> infranchissable = new ArrayList<>();
	
	public MobileElement(int x, int y, int v, char type) {
		super(x, y, type);
		this.v = v;
		this.getInfranchissable().add('#');
	}

	public int getV() {
		return v;
	}
	
	public boolean isFranchissable(char obstacle) {
		Boolean franchissable = true;
		if (this.getInfranchissable().contains(obstacle)) {
			franchissable = false;
		}
		else {
		}
		return franchissable;
	}

	public List<Object> getInfranchissable() {
		return infranchissable;
	}

	public void setInfranchissable(List<Object> infranchissable) {
		this.infranchissable = infranchissable;
	}

}
