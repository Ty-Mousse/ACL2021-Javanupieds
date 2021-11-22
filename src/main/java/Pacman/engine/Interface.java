package main.java.Pacman.engine;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Interface extends JFrame{
	
	private int tileSize = 16;
	
	private int windowWidth;
	private int windowHeight;
	private Image icon = new ImageIcon("src/main/java/Pacman/images/pacman.png").getImage();
	
	public Interface(int width, int height) {
		super();
		this.windowWidth = width * this.tileSize;
		this.windowHeight = height * this.tileSize;
		this.setTitle("Pacman");
		this.setIconImage(icon);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(this.windowWidth, this.windowHeight);
		this.setVisible(true);
	};
	
	public void render() {
		
	};
}
 