package engine;

import java.io.IOException;

//import javax.swing.JFrame;

public class Main /*extends JFrame*/{
	
	/*public Main() {
		add(new Model());
	}*/
	
	public static void main(String[] args) throws IOException{
		
		Game game = new Game();
		game.start();
		
		/*Main pac = new Main();
		pac.setVisible(true);
		pac.setTitle("Pacman");
		pac.setSize(380,420);
		pac.setDefaultCloseOperation(EXIT_ON_CLOSE);
		pac.setLocationRelativeTo(null);*/
		
	}

}
