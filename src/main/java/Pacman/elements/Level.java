package main.java.Pacman.elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Level {
	
	/*
	Definition des types des elements du level :
	 -   : sol sans piece (espace)
	 - . : sol avec piece
	 - # : mur classique
	 - O : position initiale du joueur
	 - N : position initiale d'un NPC (fantome)
	*/
	
	private List<Element> level; // Tableau d'elements definissant la structure du niveau en cours
	private List<Element> pieces; // Tableau d'element definissant la position des pieces du niveau en cours (en cas de defaite permet de memoriser les pieces deja prises)
	private List<Element> mobiles; // Tableau des positions initiales des elements mobiles du niveau en cours (a utiliser en cas de defaite => sauvegarde des positions initiales)
	private int width; // Largeur du niveau en case
	private int height; // Longueur du niveau en case
	
	public Level(String filename) throws IOException {
		this.fileToLevel(filename);
	}
		
	// Methode permetant de passer d'un fichier texte a un tableau d'elements
	public void fileToLevel(String filename) throws IOException {
		List<Element> level_list = new ArrayList<Element>();
		List<Element> pieces_list = new ArrayList<Element>();
		List<Element> mobiles_list = new ArrayList<Element>();
		File file = new File(filename);
	    Scanner myReader = new Scanner(file);
	    boolean firstline=true;
	    int i = 0;
	    while (myReader.hasNextLine()) {
	    	String data = myReader.nextLine();
	    	for (int j = 0; j < data.length(); j++) {
	    		char current = data.charAt(j);
	    		if (current == '.') {
	    			pieces_list.add(new Element(j, i, '.'));
	    		} else if (current == 'O' | current == 'N') {
	    			mobiles_list.add(new Element(j, i, data.charAt(j)));
	    		} else {
	    			level_list.add(new Element(j, i, data.charAt(j)));
	    		}
	    	}
	    	
	    	if (firstline) {
		    	this.width=data.length();
		    	firstline=false;
	    	}
	       	this.height+=1;
	       	i++;
	    }
	    myReader.close();
	    this.level = level_list;
	    this.pieces = pieces_list;
	    this.mobiles = mobiles_list;
	}
	
	public Element getElement(int x, int y) throws Exception {
		/**
		 * Prend en parametres des coordonnees x,y et renvoie l'element qui correspond.
		 */
		// labirynthe infini
					if (x>this.width) {
						x=x-this.width;
					}
					
					if (y>this.height) {
						y=y-height;
					}
					
					if (x<0) {
						x=x+this.width;
					}
					
					if (y<0) {
						y=y+this.height;
					}
		Element res = null ;
		for(Element elt : this.level) {
			if(x==elt.getX() & y==elt.getY()) {
				res = elt;
			}
		}
		return res;
	}

	public List<Element> getLevel() {	
		return level;
	}	
	
	public int removeCoin(int x, int y) {
		/***
		 * Si une piece non recuperee se trouve (x,y), elle devient
		 * recuperee et on revoie 100. Sinon on renvoie 0.
		 */
		for (Element piece : this.pieces) {
			if (piece.getX()==x & piece.getY()==y & piece.getType()=='.') {
				// On enleve la piece de la liste
				piece.setType(' ');
				return 100;
			}
		}
		return 0;
	}
	
	public List<Element> getPieces() {
		return pieces;
	}

	public List<Element> getMobiles() {
		return mobiles;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
