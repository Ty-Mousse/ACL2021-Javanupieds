package main.java.Pacman.engine;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import main.java.Pacman.elements.Level;
import main.java.Pacman.elements.Player;
import main.java.Pacman.elements.Element;

public class Game {
	
	private Level level;
	private Player player;
	//private NPC[] npcs;
	private Interface displayer;
	
	public Game() throws IOException {
		this.level = new Level("src/main/java/Pacman/level.txt");
		this.initPlayer(this.level.getMobiles());
		this.displayer = new Interface(this.level.getWidth(), this.level.getHeight());
	}
	
	public void start() {
		
		while(true /* a remplacer par la(les) condition(s) de fin de niveau */) {
			displayer.render(this.level);
			this.update();
		}
		
	}
	
	// Méthode permettant d'initialiser une instance de player à partir du tableau d'initialisation venant de la classe Level
	public void initPlayer(List<Element> initList) {
		for (Element e:initList) {
			if (e.getType() == 'O') {
				this.player = new Player(e.getX(), e.getY());
			}
		}
	}
	
	public void update(String key) {
		// Met à jour la direction
	}
	
	public char getObstacle(int x, int y) throws Exception {
		/**
		 * Prend en parametres des coordonnees et renvoie le type d'obstacle qui
		 * correspond parmis ceux de l'objet this.level
		 */
		Element res = this.level.getElement(x, y);
		return res.getType();
	}
	
	public Level getLevel() {
		return level;
	}

	public Player getPlayer() {
		return player;
	}
	
	public Interface getDisplayer() {
		return displayer;
	}
	
}
