package main.java.Pacman.engine;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import main.java.Pacman.elements.Level;
import  main.java.Pacman.elements.Player;
import  main.java.Pacman.elements.Element;

public class Game {
	
	private Level level;
	private Player player;
	
	public Game() throws IOException {
		this.level = new Level("src/level.txt");
		this.player = new Player(10, 10);
	}
	
	public void start() {
		
		Scanner input = new Scanner(System.in);
		
		while(true /* a remplacer par la(les) condition(s) de fin de niveau */) {
			this.render();
			String data = "Position joueur : (" + this.player.getX() + ", " + this.player.getY() + ")";
			System.out.println(data);
			System.out.println("Deplacement (U/D/L/R) : ");
			String key = input.nextLine();
			this.update(key);
		}
		
	}
	
	public void update(String key) {
		if (key.equals("U")) { /* obsolete */
			this.player.setY(this.player.getY() - this.player.getVy());
		} else if (key.equals("D")) {
			this.player.setY(this.player.getY() + this.player.getVy());
		} else if (key.equals("L")) {
			this.player.setX(this.player.getX() - this.player.getVx());
		} else if (key.equals("R")) {
			this.player.setX(this.player.getX() + this.player.getVx());
		}
	}
	
	public void render() {
		int player_x = this.player.getX();
		int player_y = this.player.getY();
		for (Element element : this.level.getLevel()) {
			if ((element.getX() == player_x) && (element.getY() == player_y)) {
				System.out.print(this.player.getType());
			} else {
				System.out.print(element.getType());
			}
			System.out.print("\n");
		}
	}
	
	public Level getLevel() {
		return level;
	}

	public Player getPlayer() {
		return player;
	}
	
	public char getObstacle(int x, int y) throws Exception {
		Element res = this.level.getElement(x, y);
		return res.getType();
		
	}
}
