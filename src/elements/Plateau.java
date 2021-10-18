package elements;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Plateau {
	
	private File file;
	private List<Cellule> board = new ArrayList<Cellule>();
	
	public Plateau(File f) throws FileNotFoundException {
		this.file = f;
		this.fileToBoard();
	}
	
	public void fileToBoard() throws FileNotFoundException{
		Scanner sc = new Scanner(this.file);
		while(sc.hasNextLine()) {
			String line = sc.nextLine();
			char[] elements = line.toCharArray();
			for (char c:elements) {
				this.board.add(new Cellule(0, 0, Character.getNumericValue(c)));
			}
		}
	}

	public File getFile() {
		return file;
	}

	public List<Cellule> getBoard() {
		return board;
	}

}
