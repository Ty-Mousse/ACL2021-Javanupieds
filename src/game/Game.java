package game;

import java.io.File;
import java.io.FileNotFoundException;

import elements.Plateau;

public class Game {

	public Game() {
	
	}
	
	public void start() throws FileNotFoundException {
		File file = new File("assets/level.txt");
		Plateau plateau = new Plateau(file);
		plateau.fileToBoard();
		System.out.println(plateau.getBoard());
	}
	
}
