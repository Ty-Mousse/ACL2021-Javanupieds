package elements;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Level {
	
	private List<List<Element>> level; // Tableau d'�l�ments d�finissant le niveau en cours
	private int width; // Largeur du niveau en case
	private int height; // Longueur du niveau en case
	
	public Level(String filename) throws IOException {
		this.fileToLevel(filename);
	}
		
	// M�thode permetant de passer d'un fichier texte � un tableau d'�l�ments
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
