package elements;

import java.io.FileNotFoundException;
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
	
	// Met à jour la dimension du niveau en cours à partir du fichier texte en entrée
	public void initDimension() {
		
	}
	
	// Méthode permetant de passer d'un fichier texte à un tableau d'éléments
	public List<List<Element>> fileToLevel(String filename) throws IOException {
		FileReader reader = new FileReader(filename);
		int i;
		int indX = 25;
		int indY = 0;
		while ((i = reader.read()) != -1) {
			if (indX == 25) {
				//this.level.add(null);
				indX = 0;
				indY++;
			} else {
				//this.level.add();
				System.out.print((char)i);
				indX++;
			}
		}
		return null;
	}

	public List<List<Element>> getLevel() {
		return level;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
