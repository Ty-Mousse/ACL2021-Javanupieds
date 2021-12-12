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
import main.java.Pacman.elements.Player;

public class InterfacePainter extends JPanel{
	
	private List<Element> level;
	
	private static boolean gameOver;
	private boolean victory;
	private int tileSize;
	private int windowWidth;
	private int windowHeight;
	private static int lives, score;
    private final Font smallFont = new Font("Arial", Font.BOLD, 14);
	private Image ghost, pacman, heart;

	public InterfacePainter(List<Element> level, int height, int width, Player player, int score) {
		loadImages();
		this.level = level;
		this.gameOver = false;
		this.victory = false;
		this.tileSize = 16;
		this.windowHeight= height;
		this.windowWidth= width;
		this.lives = player.getLives();
		this.score=score;
	}
	
	
	private void loadImages() {
		ghost = new ImageIcon("src/main/java/Pacman/images/ghost.gif").getImage();
		pacman = new ImageIcon("src/main/java/Pacman/images/pacman.png").getImage();
		heart = new ImageIcon("src/main/java/Pacman/images/heart.png").getImage();

	}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, windowWidth, windowHeight);
		
		drawMaze(g2d);
		drawScore(g2d);
    }
	
	private void drawMaze(Graphics2D g2d){
		int x, y;
        for(Element e:level) {
        	x=e.getX()*15;
        	y=e.getY()*15-5;
        	if(e.getType() == ' ') {
        		g2d.setColor(Color.black);
        		g2d.fillRect(x, y, 15, 15);
        	}if(e.getType() == '#') {
        		g2d.setColor(new Color(0,72,251));
        		g2d.fillRect(x, y, 15, 15);
        	}if(e.getType() == '.') {
        		g2d.setColor(new Color(255,255,255));
        		g2d.fillOval(x + 5, y + 5, 6, 6);
        	}if(e.getType() == '2') {
        		x-=3;
        		y-=3;
        		g2d.drawImage(pacman, x, y, this);
        	}if(e.getType() == '3') {
        		g2d.drawImage(ghost, x, y, this);
        	}
        }
	}
	
	private void drawScore(Graphics2D g2d) {
	//	System.out.println(lives);
		g2d.setFont(smallFont);
		g2d.setColor(new Color(5, 181, 79));
		String s = "Score: " + score;
		g2d.drawString(s, windowWidth - 8*tileSize, windowHeight - 4*tileSize);//x width y height
		for (int i = 0; i < lives; i++) {
			g2d.drawImage(heart, i * 28 + 8, windowWidth + 4*(tileSize + 1), this);
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



