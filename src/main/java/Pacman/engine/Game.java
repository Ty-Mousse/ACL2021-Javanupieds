package main.java.Pacman.engine;

import java.io.IOException;
import java.util.List;

import main.java.Pacman.elements.Level;
import main.java.Pacman.elements.MobileElement;
import main.java.Pacman.elements.NPC;
import main.java.Pacman.elements.Player;
import main.java.Pacman.elements.Element;

public class Game {
	
	private int delay = 17; // Delai de temps en ms (permet 60fps)
	private long timeRef = System.currentTimeMillis();;
	private long time;
	
	private Level level;
	private Player player;
	private List<NPC> npcs;
	private Interface displayer;
	
	private Controller controller;
	private int inputX;
	private int inputY;
	
	public Game() throws IOException {
		this.level = new Level("src/main/java/Pacman/level.txt");
		this.initPlayer(this.level.getMobiles());
		this.displayer = new Interface(this.level.getWidth(), this.level.getHeight());
	}
	
	public void start() throws Exception {
		
		while(this.player.getLives() > 0) {
			
			this.updateInput(); // Recuperation de l'entrée clavier du joueur (si presente) et envoi au controlleur
			this.updatePosition(); // Mise a jour des position en fonction des autorisations de deplacement (collisions, etc...)
			this.updateState(); // Mise a jour des etats en fonction des deplacements
			
			time= System.currentTimeMillis();
			if (time - timeRef >= delay) {
				this.displayer.setTitle("Pacman @" + 1000/(time - timeRef) + "fps");
				timeRef = System.currentTimeMillis();
				displayer.render(this.level); // Mise a jour de l'affichage une fois toutes les mise a jours faites (60fps)
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
	
	public void initNPC(List<Element> initList) {
		for (Element e:initList) {
			if (e.getType() == 'N') { // pas sur pour le 'N', a modifier si besoin !
				NPC npc = new NPC(e.getX(), e.getY());
				npcs.add(npc);				
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
/*		String input = this.controller.getInput();
		int[] direction = this.controller.getDirection(input);
		
		int dx = direction[0];
		int dy = direction[1];
		
		if (dx != 0 & dy !=0) {
			this.inputX=dx;
			this.inputY=dy;
		}
*/	}
	
	private void updateState() {
	
	}
	

	private void updatePosition() throws Exception {
		/**
		 * Met a jour les positions des elements mobiles du jeu
		 */
/*		this.updatePlayerPosition();
		this.updateNPCPositions();
*/		
	}
	
	private void updatePlayerPosition() throws Exception {
		/**
		 * Recupere la position et vitesse du personnage, les input, et 
		 * appelle la fonction checkMouvement, qui renvoie la nouvelle
		 * position du personnage.
		 */
/*		int x = this.player.getX();
		int y = this.player.getY();
		int v = this.player.getV();
		
		int[] newposition = this.checkMouvement(this.player,x,y,this.inputX,this.inputY,v);
		int newx = newposition[0];
		int newy = newposition[1];
		
		this.player.setX(newx);
		this.player.setY(newy);
*/	}
	
	private void updateNPCPositions() throws Exception {
		/**
		 * Pour chaque ncp :
		 * - Genere une direction grace a NPC.deplacementRandom()
		 * - Recupere la position et vitesse 
		 * - Appelle la fonction checkMouvement, qui renvoie la nouvelle
		 * position du npc.
		 * - Met a jour la position du npc
		 */
		
/*		for(NPC npc : npcs) {
			int[] direction = npc.deplacementRandom(this.level);
			
			int x = npc.getX();
			int y = npc.getY();
			int v = npc.getV();
			
			int dx = direction[0];
			int dy = direction[1];
			
			int[] newPosition = this.checkMouvement(npc, x, y, dx, dy, v);
			
			int newx = newPosition[0];
			int newy = newPosition[1];
			
			npc.setX(newx);
			npc.setY(newy);
			
		}
		
*/	}
	
	private int[] checkMouvement(MobileElement objet, int x, int y, int dx, int dy, int cpt) throws Exception {
		/**
		 * A partir d'un element mobile, de sa position, de sa vitesse et
		 * d'une direction, renvoie sa nouvelle position. Procede par recurence
		 * afin de ne pas traverser un mur si la vitesse est grande.
		 */
		if (cpt == 0) {
			int[] newposition = {x,y};
			return newposition;
		}
		else {
			x=x+dx;
			y=y+dy;
			char obstacle = getObstacle(x,y);
			boolean franchissable = objet.isFranchissable(obstacle);
			
			if (franchissable) {
				return this.checkMouvement(objet, x, y, dx, dy, cpt-1);
			}
			else {
				int[] newposition = {x,y};
				return newposition; 
			}
		}
		
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
