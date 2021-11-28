package main.java.Pacman.engine;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.List;
import java.awt.Font;
import java.awt.FontMetrics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import main.java.Pacman.elements.Element;
import main.java.Pacman.elements.Level;
import main.java.Pacman.elements.NPC;
import main.java.Pacman.elements.Player;

public class InterfacePainter extends JPanel{
	
	private Level level;
	
	private static boolean gameOver;
	private boolean victory;
	private int tileSize;
	private int windowWidth;
	private int windowHeight;
	private Image ghost = new ImageIcon("src/main/java/Pacman/images/ghost.gif").getImage();
	private Image pacman = new ImageIcon("src/main/java/Pacman/images/pacman.png").getImage();


	public InterfacePainter(Level level) {
		this.level = level;
		this.gameOver = false;
		this.victory = false;
		this.tileSize = 16;
		this.windowWidth = level.getWidth();
		this.windowHeight= level.getHeight();
	}
	
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, windowWidth*tileSize, windowHeight*tileSize);
		System.out.println(level.getWidth()+" et "+level.getHeight());
        int x, y;
    	for(Element p:level.getPieces()) {
        	x=p.getX()*15;
        	y=p.getY()*15-15;
            g.setColor(new Color(255,255,255));
            g.fillOval(x + 10, y + 10, 6, 6);
        	}
        for(Element e:level.getLevel()) {
            g.setColor(new Color(0,72,251));
        	x=e.getX()*15;
        	y=e.getY()*15-10;
        	if(e.getType() == '#') {
        		g.fillRect(x, y, 20, 20);
        	}else {
        	}
        	for(Element p:level.getMobiles()) {
        		System.out.println(p.getType());
            	x=p.getX()*15;
            	y=p.getY()*15-15;
            	if(p.getType() == 'O') {
            		g.drawImage(pacman, x, y, this);
            	}else{
            		g.drawImage(ghost, x, y, this);
            	}
        }
	  }



/*		// affichage du game over
			if (this.gameOver) {

				String str = "Game Over!";
				g.setColor(Color.RED);
				g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
				FontMetrics fm = g.getFontMetrics();
				int x = (g.getClipBounds().width - fm.stringWidth(str)) / 2;
				int y = (g.getClipBounds().height / 2) + fm.getMaxDescent();
				g.drawString(str, x, y);
			}
			if (this.victory) {
				String str = "Victoire!";
				g.setColor(Color.BLUE);
				g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
				FontMetrics fm = g.getFontMetrics();
				int x = (g.getClipBounds().width - fm.stringWidth(str)) / 2;
				int y = (g.getClipBounds().height / 2) + fm.getMaxDescent();
				g.drawString(str, x, y);
			}
*/
}
}

