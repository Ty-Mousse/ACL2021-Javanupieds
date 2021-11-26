package main.java.Pacman.engine;

import java.io.IOException;
import java.util.List;

import main.java.Pacman.elements.Level;
import main.java.Pacman.elements.Player;
import main.java.Pacman.elements.Element;

public class Game {
	
	private int delay = 17; // Delai de temps en ms (permet 60fps)
	private long timeRef = System.currentTimeMillis();;
	private long time;
	
	private Level level;
	private Player player;
	//private NPC[] npcs; // Future liste de NPC à instancier en fonction du level en cours
	private Interface displayer;
	
	private Controller controller;
	private int inputX;
	private int inputY;
	
	public Game() throws IOException {
		this.level = new Level("src/main/java/Pacman/level.txt");
		this.initPlayer(this.level.getMobiles());
		this.displayer = new Interface(this.level.getWidth(), this.level.getHeight());
	}
	
	public void start() {
		
		while(this.player.getLives() > 0) {
			
			this.updateInput(); // Recuperation de l'entrée clavier du joueur (si presente) et envoi au controlleur
			this.updatePosition(); // Mise a jour des position en fonction des autorisations de deplacement (collisions, etc...)
			this.updateState(); // Mise a jour des etats en fonction des deplacements
			
			time= System.currentTimeMillis();
			if (time - timeRef >= delay) {
				this.displayer.setTitle("Pacman @" + 1000/(time - timeRef) + "fps");
				timeRef = System.currentTimeMillis();
				//displayer.render(this.level); // Mise a jour de l'affichage une fois toutes les mise a jours faites (60fps)
			}

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
	
	private void updateInput() {
		/**
		 * Recupere la direction grace au controller.
		 * Met a jour les input seulement s'ils sont
		 * differents de 0 chacun (autrement : aucun
		 * input donc la direction reste la meme).
		 */
		String input = this.controller.getInput();
		int[] direction = this.controller.getDirection(input);
		
		int dx = direction[0];
		int dy = direction[1];
		
		if (dx != 0 & dy !=0) {
			this.inputX=dx;
			this.inputY=dy;
		}
	}
	
	private void updateState() {
	
	}
	

	private void updatePosition() {
		this.updatePlayerPosition();
		this.updateNPCPositions();
		
	}
	
	private void updatePlayerPosition() {
		int x = this.player.getX();
		int y = this.player.getY();
	}
	
	private char getObstacle(int x, int y) throws Exception {
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
