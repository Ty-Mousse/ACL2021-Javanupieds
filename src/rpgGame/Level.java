package rpgGame;

import java.util.List;

public class Level {
	
	private List<List<Element>> level; // Tableau d'�l�ments d�finissant le level en cours
	
	public Level(String filename) {
		
	}
	
	// M�thode permetant de passer d'un fichier texte � une liste d'�l�ments
	public void fileToLevel(String filename) {
		
	}

	public List<List<Element>> getLevel() {
		return level;
	}

}
