package engine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
	private int lives, score; // nbr de vie + score
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
	
	private void initGame() {
		lives = 3;
		score = 0;
		initlevel();
		nGhosts = 2;
		currentSpeed=3;
	}
	
	private void initlevel() {
		for(int i=0;i<nBlocks*nBlocks;i++) {
			screenData[i]=levelData[i];
		}
	}
	
	private void playGame(Graphics2D g2d) {
		if (dying) {
			death();
		}else {
			movePacman();
			drawPacman(g2d);
			moveGhosts(g2d);
			checkMaze();
		}
	}
	
	public void  movePacman() {
		int pos;
		short ch;
		
		if(pacman_x%blockSize ==0 && pacman_y % blockSize ==0) {
			pos = pacman_x / blockSize + nBlocks * (int) (pacman_y / blockSize)
					ch=screenData[pos];
		}
		if ((ch & 16) !=0) {
			screenData[pos] = (short) (ch & 15);
			score++;
		}
		if (req_dx != 0 || req_dy !=0) {
			if(!((req_dx == -1 && req_dy ==0 && (ch & 1) != 0)
			|| (req_dx == 1 && req_dy ==0 && (ch & 4) != 0)
			|| (req_dx == 0 && req_dy ==-1 && (ch & 2) != 0)
			|| (req_dx == 0 && req_dy ==1 && (ch & 8) != 0))) {
				pacmand_x = req_dx;
				pacmand_y = req_dy;
			}
		}
		
		if(!((pacmand_x == -1 && pacmand_y ==0 && (ch & 1) != 0)
		|| (pacmand_x == 1 && pacmand_y ==0 && (ch & 4) != 0)
		|| (pacmand_x == 0 && pacmand_y ==-1 && (ch & 2) != 0)
		|| (pacmand_x == 0 && pacmand_y ==1 && (ch & 8) != 0))) {
			pacmand_x = 0;
			pacmand_y = 0;
		}
		
		pacman_x = pacman_x+pacmanSpeed + pacmand_x;
		pacman_y = pacman_y+pacmanSpeed + pacmand_y;
	}
	
	// 4:41 deuxième vidéo
	
	private void continueLevel() {
		int dx=1;
		int random;
		
		for(int i = 0; i<nGhosts;i++) {
			ghost_y[i]=4*blockSize;
			ghost_x[i]=4*blockSize;
			ghost_dy[i]=0;
			ghost_dx[i]=dx;
			dx=-dx;
			random= (int) (Math.random() * (currentSpeed + 1));
			
			if (random>currentSpeed) {
				random= currentSpeed;
			}
			ghostSpeed[i]=validSpeeds[random];
		}
		
		pacman_x = 7*nBlocks;
		pacman_y = 11*nBlocks;
		pacmand_x = 0;
		pacmand_y = 0;
		req_dx=0;
		req_dy=0;
		dying=false;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, d.width, d.height);
		
		drawMaze(g2d);
		drawScore(g2d);
		
		if(inGame) {
			playGame(g2d);
		}else {
			showIntroScreen(g2d);
		}
		Toolkit.getDefaultToolkit().sync();
	}

	class TAdapter extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if (inGame) {
				if (key==KeyEvent.VK_LEFT) {
					req_dx=-1;
					req_dy=0;
				}
				else if (key==KeyEvent.VK_RIGHT) {
					req_dx=1;
					req_dy=0;
				}
				else if (key==KeyEvent.VK_UP) {
					req_dx=0;
					req_dy=-1;
				}
				else if (key==KeyEvent.VK_DOWN) {
					req_dx=0;
					req_dy=1;
				}
				else if (key==KeyEvent.VK_ESCAPE) {
					inGame = false;
				}
			} else {
				if (key == KeyEvent.VK_SPACE) {
					inGame=true;
					initGame();
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
