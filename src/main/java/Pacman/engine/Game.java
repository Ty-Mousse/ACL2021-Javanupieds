package main.java.Pacman.engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import main.java.Pacman.elements.Level;
import main.java.Pacman.elements.MobileElement;
import main.java.Pacman.elements.NPC;
import main.java.Pacman.elements.Player;
import main.java.Pacman.elements.Element;

public class Game {
	
	private int delay = 17; // Delai de temps en ms (permet 60fps)
	private int speedDelay = 100;
	private long timeRef = System.currentTimeMillis();
	private long speedTimeRef = System.currentTimeMillis();
	private long time;
	
	private Level level;
	private Player player;
	private List<NPC> npcs;
	private Interface displayer;
	private int goal;
	private int score = 0; // Stoque le score du joueur
	
	private Controller controller;
	private int inputX;
	private int inputY;
	
	public Game() throws IOException {
		this.controller = new Controller();
		this.inputX = 0;
		this.inputY = 0;
		this.level = new Level("src/main/java/Pacman/level.txt");
		this.goal = this.level.getPieces().size()*100;
		this.initPlayer(this.level.getMobiles());
		this.initNPC(this.level.getMobiles());
		this.displayer = new Interface(this.level.getWidth(), this.level.getHeight(), this.player, this.getListAll(), this.controller);
	}
	
	public void start() throws Exception {

		while(this.player.getLives() > 0 & this.score != this.goal) {
			this.updateInput(); // Recuperation de l'entrée clavier du joueur (si presente) et envoi au controlleur
			List<Element> allElement = this.getListAll();
			
			// Boucle de deplacement du joueur
			// Optimisation probablement faisable sur les délais de temps differents
			time= System.currentTimeMillis();
			if (time - speedTimeRef >= speedDelay) {
				this.updatePosition();
				this.updateState(); // Mise a jour des etats en fonction des deplacements
				speedTimeRef = System.currentTimeMillis();
			}
			
			// Boucle de deplacement du joueur
			time= System.currentTimeMillis();
			if (time - timeRef >= delay) {
				this.displayer.setTitle("Pacman @" + 1000/(time - timeRef) + "fps");
				timeRef = System.currentTimeMillis();
				this.displayer.render(allElement, this.player, this.score); // Mise a jour de l'affichage une fois toutes les mise a jours faites (60fps)
			}
		}
		
		// Dernier render pour afficher le cas ou il reste zero vie
		List<Element> allElement = this.getListAll();
		this.displayer.render(allElement, this.player, this.score); // Mise a jour de l'affichage une fois toutes les mise a jours faites (60fps)
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
		/***
		 * Initialise la liste de npcs a partir du tableau du level
		 */
		List<NPC> npcs = new ArrayList<NPC>();
		for (Element e:initList) {
			if (e.getType() == 'N') { // pas sur pour le 'N', a modifier si besoin !
				NPC npc = new NPC(e.getX(), e.getY());
				npcs.add(npc);				
			}
		}
		this.npcs = npcs;
	}
	
	public void replaceNPC(List<Element> initList) {
		/***
		 * Replace les npcs a leurs positions initiale
		 */
		
		List<Integer> npcsX = new ArrayList<Integer>();
		List<Integer> npcsY = new ArrayList<Integer>();
		int n =0;
		for (Element e:initList) {
			if (e.getType() == 'N') { // pas sur pour le 'N', a modifier si besoin !
				npcsX.add(e.getX());
				npcsY.add(e.getY());
				n++;
			}
		}
				
		for (int i = 0; i<n ; i++) {
			this.npcs.get(i).setX(npcsX.get(i));
			this.npcs.get(i).setY(npcsY.get(i));

			
		}
		
	}
	
	private void updateInput() throws Exception {
		/**
		 * Recupere la direction grace au controller.
		 * Met a jour les input seulement s'ils sont
		 * differents de 0 chacun (autrement : aucun
		 * input donc la direction reste la meme).
		 */
		int[] direction = this.controller.getInputEnCours();
		
		int dx = direction[0];
		int dy = direction[1];
		char obstacle = this.getObstacle(this.player.getX()+dx, this.player.getY()+dy);
		boolean directionPossible = this.player.isFranchissable(obstacle);
		
		if (directionPossible) {
			this.inputX=dx;
			this.inputY=dy;
		}
		//System.out.println(Integer.toString(dx)+Integer.toString(dx));
	}
	
	private void updateState() {
		/***
		 * Met a jour les etats des elements en fonction de leurs positions
		 */
		
		int xPlayer = this.player.getX();
		int yPlayer = this.player.getY();
		
		this.score += this.level.removeCoin(xPlayer, yPlayer); // on onleve la pièce		
		//System.out.println(this.score);
		
		for (NPC npc : this.npcs) { //on regarde pour chaque npc s'il est en contact avec le player
			if (npc.getX()==xPlayer & npc.getY()==yPlayer) {
				int currentLife = this.player.getLives();
				this.player.setLives(currentLife - 1);
			
				for (Element e: this.level.getMobiles()) { // si oui vie-=1 et on reset position joueur/npc
					if (e.getType() == 'O') {
						this.player.setX(e.getX());
						this.player.setY(e.getY());
						}
					}
				
				this.replaceNPC(this.level.getMobiles());
				int[] reset = {0,0};
				this.controller.setInputEnCours(reset);
				
				}
		
			}
		
		}
		
	private void updatePosition() throws Exception {
		/**
		 * Met a jour les positions des elements mobiles du jeu
		 */
		this.updatePlayerPosition();
		this.updateState(); //correction du bug de traversement d'ennemi
		this.updateNPCPositions();
		
	}
	
	private void updatePlayerPosition() throws Exception {
		/**
		 * Recupere la position et vitesse du personnage, les input, et 
		 * appelle la fonction checkMouvement, qui renvoie la nouvelle
		 * position du personnage.
		 */
		int x = this.player.getX();
		int y = this.player.getY();
		int v = this.player.getV();
		
		int width = this.level.getWidth();
		int height = this.level.getHeight();
		
		int[] newposition = this.checkMouvement(this.player, x, y, this.inputX, this.inputY, v, width,height);
		int newx = newposition[0];
		int newy = newposition[1];
		
		this.player.setX(newx);
		this.player.setY(newy);
	}
	
	private void updateNPCPositions() throws Exception {
		/**
		 * Pour chaque ncp :
		 * - Genere une direction grace a NPC.deplacementRandom()
		 * - Recupere la position et vitesse 
		 * - Appelle la fonction checkMouvement, qui renvoie la nouvelle
		 * position du npc.
		 * - Met a jour la position du npc
		 */
		
		for(NPC npc : this.npcs) {
			
			int x = npc.getX();
			int y = npc.getY();
			
			int v = npc.getV();
			int[] newDir;
			
			int dx = npc.getdx();
			int dy = npc.getdy();
			
			int width = this.level.getWidth();
			int height = this.level.getHeight();
			
			int[] newPosition = this.checkMouvement(npc, x, y, dx, dy, v, width, height);
			
			while(newPosition[0]==x & newPosition[1]==y) {
				newDir = npc.deplacementRandom();
				dx=newDir[0];
				dy=newDir[1];
				newPosition = this.checkMouvement(npc, x, y, dx, dy, v, width, height);
				
			}
			
			npc.setdx(dx);
			npc.setdy(dy);
			
			npc.setX(newPosition[0]);
			npc.setY(newPosition[1]);
			
		}
		
	}
	
	private int[] checkMouvement(MobileElement objet, int x, int y, int dx, int dy, int cpt, int width, int height) throws Exception {
		/**
		 * A partir d'un element mobile, de sa position, de sa vitesse et
		 * d'une direction, renvoie sa nouvelle position. Procede par recurence
		 * afin de ne pas traverser un mur si la vitesse est grande.
		 */
		if (cpt == 0) {
			//System.out.println("fini");
			int[] newposition = {x,y};
			return newposition;
		}
		else {
			int oldx=x;
			int oldy=y;
			x=x+dx;
			y=y+dy;
			
			// labirynthe infini
			if (x>width) {
				x=x-width;
				System.out.println("a droite");
			}
			
			if (y>height) {
				y=y-height;
			}
			
			if (x<0) {
				x=x+width;
				System.out.println("a gauche");
			}
			
			if (y<0) {
				y=y+height;
			}
			
			char obstacle = getObstacle(x, y);
			//System.out.println("obstacle");
			boolean franchissable = objet.isFranchissable(obstacle);
			
			if (franchissable) {
				return this.checkMouvement(objet, x, y, dx, dy, cpt-1, width, height);
			}
			else {
				int[] newposition = {oldx,oldy};
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
		if (res==null) {
			return ' ';
		}
		else {
			return res.getType();
		}
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
	
	@SuppressWarnings("null")
	private List<Element> getListAll() {
		/***
		 * Renvoie une liste contenant tous les elements d'une partie en cours
		 */
		List<Element> murs = this.level.getLevel();
		List<Element> pieces = this.level.getPieces();
		List<Element> mobiles = new ArrayList<>();
		mobiles.add(this.player);
		for (NPC npc : this.npcs) {
			mobiles.add(npc);
		}
		List<Element> listImobiles = Stream.concat(murs.stream(), pieces.stream()).collect(Collectors.toList());
		List<Element> listAll = Stream.concat(listImobiles.stream(), mobiles.stream()).collect(Collectors.toList());
		
		return listAll;
	}
	
}
