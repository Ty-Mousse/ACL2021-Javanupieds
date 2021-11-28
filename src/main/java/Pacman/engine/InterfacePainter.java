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

public class InterfacePainter extends JPanel{
	
	private Level level;
	
	private static boolean gameOver;
	private boolean victory;

	public InterfacePainter(Level level) {
		this.level = level;
		this.gameOver = false;
		this.victory = false;
	}
	
	public void paintComponent(Graphics g) {
        int x, y;
        for(Element e:level.getLevel()) {
        	x=e.getX()*15;
        	y=e.getY()*15-10;
        	System.out.println("x: "+x+" y: "+y+" elem: "+e.getType());
        	if(e.getType() == '#') {
        		g.fillRect(x, y, 20, 20);
        	}
        	else if(e.getType() == '.') {
                g.setColor(new Color(255,255,255));
                g.fillOval(x + 10, y + 10, 6, 6);
        	}
        	else if(e.getType() == 'O') {
        		g.fillRect(x, y, 24, 24);
        	}else {
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
