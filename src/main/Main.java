package main;

import java.io.FileNotFoundException;

import game.Game;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		Game game = new Game();
		game.start();
	}

}
