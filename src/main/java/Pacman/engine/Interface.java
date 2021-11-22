package main.java.Pacman.engine;

import javax.swing.JFrame;

public class Interface extends JFrame{

	private int tileSize = 16;
	
	private int windowWidth;
	private int windowHeight;
	
	public Interface(int width, int height) {
		super();
		this.windowWidth = width * this.tileSize;
		this.windowHeight = height * this.tileSize;
		this.setTitle("Pacman");
		this.setSize(this.windowWidth, this.windowWidth);
		this.setVisible(true);
	};
	
	public void render() {
		
	};
}
 