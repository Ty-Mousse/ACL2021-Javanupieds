package rpgGame;

import java.util.List;

public class Level {
	
	private List<List<Element>> level; // Tableau d'éléments définissant le level en cours
	
	public Level(String filename) {
		
	}
	
	// Méthode permetant de passer d'un fichier texte à une liste d'éléments
	public void fileToLevel(String filename) {
		
	}

	public List<List<Element>> getLevel() {
		return level;
	}

}
