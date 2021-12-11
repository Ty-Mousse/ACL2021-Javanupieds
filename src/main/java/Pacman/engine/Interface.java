package main.java.Pacman.engine;


import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import main.java.Pacman.elements.Element;
import main.java.Pacman.elements.Level;
import main.java.Pacman.elements.Player;

public class Interface extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int tileSize = 16;
	private int windowWidth;
	private int windowHeight;
	private InterfacePainter interfacepainter;
	private Image icon = new ImageIcon("src/main/java/Pacman/images/pacman.png").getImage();
	
	public Interface(int width, int height, Player player, List<Element> level, Controller controller) {
		super();
		this.windowWidth = width * this.tileSize;
		this.windowHeight = height * (this.tileSize +2) ;
		this.setTitle("Loading ...");
		this.setIconImage(icon);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(this.windowWidth, this.windowHeight);
		this.setResizable(false);
		this.setVisible(true);
		this.addKeyListener(controller);
		this.interfacepainter= new InterfacePainter(level, this.windowHeight, this.windowWidth, player);
		};
		
		public void render(List<Element> level, Player player) {
			interfacepainter.removeAll();
			interfacepainter = new InterfacePainter(level, this.windowHeight, this.windowWidth, player);
			this.add(interfacepainter);
		}

	}