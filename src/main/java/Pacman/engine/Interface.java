package main.java.Pacman.engine;

import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

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
	private int[] i= {0,0};
	private InterfacePainter interfacepainter;
	private Image icon = new ImageIcon("src/main/java/Pacman/images/pacman.png").getImage();
	private Controller controller;
	
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
		this.controller = controller;
		this.interfacepainter= new InterfacePainter(level, this.windowHeight, this.windowWidth, player, 0,i) ;
		this.add(interfacepainter);
		SwingUtilities.updateComponentTreeUI(interfacepainter);
	};
		
		
		public void render(List<Element> level, Player player, int score,int[] direction) {
			this.getContentPane().removeAll();
			//System.out.println(direction[0]+' '+direction[1]);
			interfacepainter = new InterfacePainter(level, this.windowHeight, this.windowWidth, player, score,direction);
			if (this.controller.getState() == 1) {
				this.interfacepainter.setCpt(1);
			}
			this.add(interfacepainter);
			SwingUtilities.updateComponentTreeUI(interfacepainter);
		}

	}