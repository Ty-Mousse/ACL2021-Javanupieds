package main.java.Pacman.engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.util.List;
import java.awt.Font;
import java.awt.FontMetrics;

import javax.swing.JPanel;

import main.java.Pacman.elements.Element;
import main.java.Pacman.elements.Level;
import main.java.Pacman.elements.NPC;
import main.java.Pacman.elements.Player;

public class InterfacePainter{
	
	private Level level;
	
	private int c; /* compteur de calcul affiché sur la fenêtre */
	private static boolean gameOver;
	private boolean victory;

	public InterfacePainter() {
		this.gameOver = false;
		this.victory = false;
	}

	   public void drawMaze(Graphics2D g2d, List<Element> level) {
	    	/*
	    	 * Draw the maze : 
	    	 * # wall   . coin   O player   N NPC
	    	 */
			for(Element e:level) {
				System.out.println(e.getType());
				};
	        int x, y;
	        
	        for(Element e:level) {
	        	x=e.getX();
	        	y=e.getY();
	        	if(e.getType() == '#') {
	        		g2d.fillRect(x, y, 24, 24);
	        	}
	        	else if(e.getType() == '.') {
	        		g2d.fillRect(x, y, 24, 24);
	        	}
	        	else if(e.getType() == 'O') {
	        		g2d.fillRect(x, y, 24, 24);
	        	}else {
	        		g2d.fillRect(x, y, 24, 24);
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
	        }*/
	    }
}
