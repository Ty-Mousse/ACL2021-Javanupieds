package engine;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Model extends JPanel implements ActionListener {
	
	private Dimension d; //Width + height
	private final Font smallFont = new Font("Ariale",Font.BOLD,14);//To show the score on the bottom right of the screen
	private boolean inGame = false;
	private boolean dying = false;
	
	private final int blockSize = 24;
	private final int nBlocks = 15;
	private final int screenSize = nBlocks*blockSize;
	private final int maxGhosts = 4;
	private final int pacmanSpeed = 6;
	
	private int nGhosts = 2;//initialisation
	private int lives, scores; // nbr de vie + score
	private int[] dx, dy;
	private int[] ghost_x, ghost_y, ghost_dx, ghost_dy, ghostSpeed;
	
	private Image heart, ghost; //For the lives 
	private Image up, down, left, right; //Pacman in every direction
	
	private int pacman_x, pacman_y, pacmand_x, pacmand_y;
	private int req_dx, req_dy;
	
	private final int validSpeeds[] = {1,2,3,4,6,8};
	private final int maxSpeed =6;
	private int currentSpeed = 3;
	private short [] screenData;
	private Timer timer;
	
	public final short levelData[] = {
			
	};
	
	public Model() {
		loadImages();
		initVariables();
		addKeyListener(new TAdapter());
		setFocusable(true);
		initGame();
	}
	
	private void loadImages() {
		up = new ImageIcon("src image").getImage();//Make that for all images
		down = new ImageIcon("src image").getImage();//Make that for all images
		left = new ImageIcon("src image").getImage();//Make that for all images
		right = new ImageIcon("src image").getImage();//Make that for all images
		heart = new ImageIcon("src image").getImage();//Make that for all images
		ghost = new ImageIcon("src image").getImage();//Make that for all images
	}
	
	private void initVariables() {
		screenData = new short[nBlocks*nBlocks];
		d = new Dimension(height,width);//pas sur que c'est height et width a try
		ghost_x = new int[maxGhosts];
		ghost_dx = new int[maxGhosts];
		ghost_y = new int[maxGhosts];
		ghost_dy = new int[maxGhosts];
		ghostSpeed = new int[maxGhosts];
		dx = new int[4];
		dy = new int[4];
		
		timer = new Timer(40, this); //refresh every 40 ms this show speed of ghosts and pacman
		timer.restart();
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
