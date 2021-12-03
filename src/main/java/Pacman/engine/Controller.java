package main.java.Pacman.engine;

import java.awt.event.KeyEvent;

public class Controller {
	
	public Controller() {
		
	}
	
	public String getInput() {
		String nul = "nul";
		return nul;
	}
	
	public int[] getDirection(KeyEvent e) {
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
		return dir;
		
	}
	

}
