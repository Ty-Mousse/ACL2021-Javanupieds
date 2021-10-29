package engine;

import java.io.IOException;

import elements.Level;
import elements.Player;

public class Game {
	
	private Level level;
	private Player player;
	
	public Game() throws IOException {
		this.level = new Level("src/level.txt");
		this.player = new Player(10, 10);
	}
	
	public void start() {
		
		while(true) {
			this.update();
			this.render();
		}
		
	}
	
	public void update() {
		
	}
	
	public void render() {
		
	}
	
	public Level getLevel() {
		return level;
	}

	public Player getPlayer() {
		return player;
	}
	
}
