package main.java.Pacman.engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener{
	int[] inputEnCours= {0,0};

	
	public Controller() {

	}
	
	public int[] getInputEnCours() {
		System.out.println("bbs");
		return inputEnCours;
	}

	
	@Override
	public void keyPressed(KeyEvent e) {
		int[] dir = {0,0};
		switch( e.getKeyCode() ) {
		case KeyEvent.VK_UP:
			dir[0]=1;
			dir[1]=0;
			break;
		case KeyEvent.VK_DOWN:
			dir[0]=-1;
			dir[1]=0;
			break;
		case KeyEvent.VK_RIGHT:
			dir[0]=0;
			dir[1]=1;
			break;
		case KeyEvent.VK_LEFT:
			dir[0]=0;
			dir[1]=-1;
			break;
		}
		this.inputEnCours=dir;
		System.out.println(Integer.toString(dir[0])+Integer.toString(dir[1]));
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	

}
