package main.java.Pacman.engine;


import java.awt.Image;


import javax.swing.ImageIcon;
import javax.swing.JFrame;


import main.java.Pacman.elements.Level;

public class Interface extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int tileSize = 16;
	private int windowWidth;
	private int windowHeight;
	private Image icon = new ImageIcon("src/main/java/Pacman/images/pacman.png").getImage();
	
	public Interface(int width, int height) {
		super();
		this.windowWidth = width * this.tileSize;
		this.windowHeight = height * this.tileSize;
		this.setTitle("Loading ...");
		this.setIconImage(icon);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(this.windowWidth, this.windowHeight);
		this.setResizable(false);
		this.setVisible(true);		
		};
		
		public void render(Level level) {
			InterfacePainter interfacepainter = new InterfacePainter(level);
			this.add(interfacepainter);
		}

	}