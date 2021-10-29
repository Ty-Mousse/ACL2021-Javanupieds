package elements;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Level {
	
	private List<List<Element>> level; // Tableau d'éléments définissant le niveau en cours
	private int width; // Largeur du niveau en case
	private int height; // Longueur du niveau en case
	
	public Level(String filename) throws IOException {
		this.fileToLevel(filename);
	}
		
	// Méthode permetant de passer d'un fichier texte à un tableau d'éléments
	public void fileToLevel(String filename) throws IOException {
		List<List<Element>> list = new ArrayList<List<Element>>();
		File file = new File(filename);
	    Scanner myReader = new Scanner(file);
	    boolean firstline=true;
	    int i=0;
	    while (myReader.hasNextLine()) {
	    	String data = myReader.nextLine();
	    	List<Element> line = new ArrayList<Element>();
	    	for (int j = 0; j < data.length(); j++) {
	    		line.add(new Element(j,i,data.charAt(j)));
	    	}
	    	list.add(line);
	    	if (firstline) {
		    	this.width=data.length();
		    	firstline=false;
	    	}
	       	this.height+=1;
	       	i++;
	    }
	    myReader.close();
	    this.level = list;
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
