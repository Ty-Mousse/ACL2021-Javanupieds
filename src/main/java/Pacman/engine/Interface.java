package main.java.Pacman.engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import main.java.Pacman.elements.Element;
import main.java.Pacman.elements.Level;

public class Interface extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int tileSize = 16;
	InterfacePainter interfacepainter;
	private int windowWidth;
	private int windowHeight;
	private Level level;
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
		JPanel content = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponents(g);
				
				Graphics2D g2d = (Graphics2D) g;
				g2d.setColor(Color.black);
				g2d.fillRect(0, 0, windowWidth, windowHeight);
				
				Interface.this.interfacepainter.drawMaze(g2d, level.getLevel());
			}
			
		};

	/**
	 * Calcul/Affichage
	 */

	// creer un thread infini (lien entre deux programmes en prallèles)
	// ici, le thread fais le lien Modele/Fenetre
	Thread thread = new Thread(new Runnable() {
		@Override
		public void run() {
			while (true)
			/*
			 * boucle infinie chaque fois que la boucle est executee, la
			 * methode de calcul du jeu est appelee. comme la boucle est
			 * infinie, la methode de calcul sera appelee indefiniement.
			 */
			{
				// demande à l'EDT de redessiner le conteneur
				content.repaint();

				// temporisation
				try {
					Thread.sleep(17);
				} catch (InterruptedException e) {
					//
				}
			}
		}
	});

	// lancer le thread
	thread.start();

	// par default, le focus (des touches) s'applique à la fenetre
	// on indique donc au focus
	// de se concentrer sur le conteneur et pas sur la fenetre
	setFocusable(false);
	content.setFocusable(true);

}
}
 