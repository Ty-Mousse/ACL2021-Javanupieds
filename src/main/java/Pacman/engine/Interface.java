package main.java.Pacman.engine;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

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
		this.setTitle("Loading ...");
		this.setIconImage(icon);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(this.windowWidth, this.windowHeight);
		this.setResizable(false);
		this.setVisible(true);
	};
	
	public void render(Graphics g) {
		super.paintComponents(g);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, windowWidth, windowHeight);
		
		drawMaze(g2d);
	};
	
	   private void drawMaze(Graphics2D g2d) {
	    	/*
	    	 * Draw the maze : 
	    	 * # wall   . coin   O player   N NPC
	    	 */

	        short i = 0;
	        int x, y;

	        for (y = 0; y < screenSize; y += blockSize) {
	            for (x = 0; x < screenSize; x += blockSize) {

	                g2d.setColor(new Color(0,72,251));
	                g2d.setStroke(new BasicStroke(5));
	                
	                if ((levelData[i] == 0)) { 
	                	g2d.fillRect(x, y, blockSize, blockSize);
	                }

	                if ((screenData[i] & 1) != 0) { 
	                    g2d.drawLine(x, y, x, y + blockSize - 1);
	                }

	                if ((screenData[i] & 2) != 0) { 
	                    g2d.drawLine(x, y, x + blockSize - 1, y);
	                }

	                if ((screenData[i] & 4) != 0) { 
	                    g2d.drawLine(x + blockSize - 1, y, x + blockSize - 1,
	                            y + blockSize - 1);
	                }

	                if ((screenData[i] & 8) != 0) { 
	                    g2d.drawLine(x, y + blockSize - 1, x + blockSize - 1,
	                            y + blockSize - 1);
	                }

	                if ((screenData[i] & 16) != 0) { 
	                    g2d.setColor(new Color(255,255,255));
	                    g2d.fillOval(x + 10, y + 10, 6, 6);
	               }

	                i++;
	            }
	        }
	    }
}
 