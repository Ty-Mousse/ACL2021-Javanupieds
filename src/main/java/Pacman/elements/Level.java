package main.java.Pacman.elements;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Level {
	
	private List<Element> level; // Tableau d'elements definissant le niveau en cours
	private int width; // Largeur du niveau en case
	private int height; // Longueur du niveau en case
	
	public Level(String filename) throws IOException {
		this.fileToLevel(filename);
	}
		
	// Methode permetant de passer d'un fichier texte a un tableau d'elements
	public void fileToLevel(String filename) throws IOException {
		List<Element> list = new ArrayList<Element>();
		File file = new File(filename);
	    Scanner myReader = new Scanner(file);
	    boolean firstline=true;
	    int i=0;
	    while (myReader.hasNextLine()) {
	    	String data = myReader.nextLine();
	    	for (int j = 0; j < data.length(); j++) {
	    		list.add(new Element(j,i,data.charAt(j)));
	    	}
	    	
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

	public List<Element> getLevel() {	
		return level;
	}
	
	public Element getElement(int x, int y) throws Exception {
		/**
		 * Prend en parametres des coordonnees x,y et renvoie l'element qui correspond.
		 * Leve une exception si coo ne correspondent pas a la hauteur et largeur de la
		 * grille.
		 */
		if(x>this.width | y>this.height) {
			throw new Exception("coordinate out of the grid");
		}
		Element res = null ;
		for(Element elt : this.level) {
			if(x==elt.getX() & y==elt.getY()) {
				res = elt;
			}
		}
		return res;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
