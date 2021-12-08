package main.java.Pacman.engine;

import java.util.List;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;

import main.java.Pacman.elements.Element;
import main.java.Pacman.elements.Level;
import main.java.Pacman.elements.NPC;
import main.java.Pacman.elements.Player;

public class InterfacePainter2 extends JPanel{
	
	private Level level;
	
	private int c; /* compteur de calcul affiché sur la fenêtre */
	private static boolean gameOver;
	private boolean victory;

	public InterfacePainter2(Level level) {
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
                g.fillRect(x, y, 20, 20);
                //g.fillOval(x + 10, y + 10, 6, 6);
        	}
        	else if(e.getType() == 'O') {
        		g.fillRect(x, y, 24, 24);
        	}else {
        	}
        }
	  }

}
