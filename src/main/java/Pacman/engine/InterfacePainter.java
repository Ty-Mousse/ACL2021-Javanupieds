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

import java.awt.GradientPaint;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException; 
import javax.imageio.ImageIO;
import javax.swing.JButton;


import main.java.Pacman.elements.Element;
import main.java.Pacman.elements.Player;

public class InterfacePainter extends JButton{
	
	private List<Element> level;
	
	private static boolean gameOver;
	private boolean victory;
	private int tileSize;
	private int windowWidth;
	private int windowHeight;
	private static int lives, score;
    private final Font smallFont = new Font("Arial", Font.BOLD, 14);
	private Image ghost, pacman, heart;
	private int cpt;
	private String bouton;

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
		this.cpt=0;
		this.bouton="start";
		try {
			heart = ImageIO.read(new File("src/main/java/Pacman/images/heart.png"));
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
		    //Gr�ce � cette instruction, notre objet va s'�couter
		    //D�s qu'un �v�nement de la souris sera intercept�, il en sera averti
		    this.addMouseListener((MouseListener) this);
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
		
		if (cpt==0) {
			drawMenu(g2d);
		}
		if (cpt!=0) {
			drawMaze(g2d);
			drawScore(g2d);
		}
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

	
	private void drawMenu(Graphics2D g2d) {
	//	g2d.setColor(Color.red);
		//g2d.fillRect((windowWidth/2)-50, (windowHeight/2)-50, 100, 50);
		GradientPaint gp = new GradientPaint(0, 0, Color.blue, 0, 20, Color.cyan, true);
		g2d.setPaint(gp);
	    g2d.fillRect( windowWidth / 2 - (windowWidth/ 2 /4), (windowHeight/2)-10, windowWidth / 2 - (windowWidth/ 2 /4)-100, (windowHeight/2)+5-225);
	    g2d.setColor(Color.white);
	    g2d.drawString(bouton, windowWidth / 2 - (windowWidth/ 2 /4), (windowHeight / 2) + 5);
		
	}
	//M�thode appel�e lors du clic de souris
	public void mouseClicked(MouseEvent event) { }
	
	private void drawSwitchScreen(Graphics2D g2d) {
		
	}
}







