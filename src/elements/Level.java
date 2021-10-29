package elements;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Level {
	
	private List<List<Element>> level; // Tableau d'éléments définissant le niveau en cours
	private int width; // Largeur du niveau en case
	private int height; // Longueur du niveau en case
	
	public Level(String filename) throws IOException {
		this.fileToLevel(filename);
	}
		
	// Méthode permetant de passer d'un fichier texte à un tableau d'éléments
	public void fileToLevel(String filename) throws IOException {
		
	}

	public List<List<Element>> getLevel() {
		for(int i=0; i<4; i++){
			i+=1;
			if(i>3) {
				i+=4;
			}
		}
		return level;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
