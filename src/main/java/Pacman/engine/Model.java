package main.java.Pacman.engine;

import java.awt.BasicStroke;
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
	private final int nBlocks = 12;
	private final int screenSize = nBlocks*blockSize;
	private final int pacmanSpeed = 6;
	
	private int[] dx, dy;
	private int ghost_x, ghost_y, ghost_dx, ghost_dy, ghostSpeed;
	
	private Image ghost; //For the lives 
	private Image up, down, left, right; //Pacman in every direction
	
	private int pacman_x, pacman_y, pacmand_x, pacmand_y;
	private int req_dx, req_dy;
	
	
	public final short levelData[] = {
	    	19, 26, 18, 24, 26, 18, 26, 26, 26, 24, 24, 22,
	    	21, 0, 21, 0, 0, 21, 0, 0, 0, 0, 0, 21,
	    	21, 0, 21, 0, 0, 17, 26, 26, 26, 26, 26, 20,
	    	21, 0, 21, 0, 0, 21, 0, 0, 0, 0, 0, 21,
	    	17, 26, 24, 26, 26, 16, 26, 18, 26, 18, 26, 20,
	    	21, 0, 0, 0, 0, 21, 0, 21, 0, 21, 0, 21,
	    	21, 0, 0, 0, 0, 21, 0, 21, 0, 21, 0, 21,
	    	21, 0, 19, 26, 26, 20, 0, 21, 0, 21, 0, 21,
	    	21, 0, 21, 0, 0, 21, 0, 17, 26, 24, 26, 20,
	    	20, 0, 21, 0, 0, 21, 0, 21, 0, 0, 0, 17,
	    	20, 0, 21, 0, 0, 21, 0, 21, 0, 0, 0, 17,
	    	25, 26, 24, 18, 26, 24, 26, 24, 26, 18, 18, 28
	};
	

	private short [] screenData;
	private Timer timer;
	
	public Model() {
		loadImages();
		initVariables();
		addKeyListener(new TAdapter());
		setFocusable(true);
		initGame();
	}
	
	private void loadImages() {
		/*
		 * Load the images for the game all the image must be load
		 */
		up = new ImageIcon("src\\main\\java\\Pacman\\images\\up.gif").getImage();
		down = new ImageIcon("src\\main\\java\\Pacman\\images\\down.gif").getImage();
		left = new ImageIcon("src\\main\\java\\Pacman\\images\\left.gif").getImage();
		right = new ImageIcon("src\\main\\java\\Pacman\\images\\right.gif").getImage();
		ghost = new ImageIcon("src\\main\\java\\Pacman\\images\\ghost.gif").getImage();
	}
	
	private void initVariables() {
		screenData = new short[nBlocks*nBlocks];
		d = new Dimension(400,400);
		ghost_x = 0;
		ghost_dx = 0;
		ghost_y = 0;
		ghost_dy = 0;
		ghostSpeed = 0;
		dx = new int[4];
		dy = new int[4];
		
		timer = new Timer(40, this); //refresh every 40 ms this show speed of ghosts and pacman
		timer.restart();
		
	}
	
	private void playGame(Graphics2D g2d) {
		if (dying) {
			death();
		} else {
			movePacman();
			drawPacman(g2d);
			moveGhosts(g2d);
			checkMaze();
		}
	}

	
    private void showIntroScreen(Graphics2D g2d) {
    	 
    	String start = "Press SPACE to start";
    	g2d.setFont(smallFont);
        g2d.setColor(Color.yellow);
        g2d.drawString(start, (screenSize)/4, 150);
    }

	
    private void movePacman() {
    	/*
    	 * Check if the pacman can move and if he can we update it position
    	 */
        int pos;
        short ch;

        if (pacman_x % blockSize == 0 && pacman_y % blockSize == 0) {
            pos = pacman_x / blockSize + nBlocks * (int) (pacman_y / blockSize);
            ch = screenData[pos];

            if ((ch & 16) != 0) {
                screenData[pos] = (short) (ch & 15);
            }

            if (req_dx != 0 || req_dy != 0) {
                if (!((req_dx == -1 && req_dy == 0 && (ch & 1) != 0)
                        || (req_dx == 1 && req_dy == 0 && (ch & 4) != 0)
                        || (req_dx == 0 && req_dy == -1 && (ch & 2) != 0)
                        || (req_dx == 0 && req_dy == 1 && (ch & 8) != 0))) {
                    pacmand_x = req_dx;
                    pacmand_y = req_dy;
                }
            }

            // Check for standstill
            if ((pacmand_x == -1 && pacmand_y == 0 && (ch & 1) != 0)
                    || (pacmand_x == 1 && pacmand_y == 0 && (ch & 4) != 0)
                    || (pacmand_x == 0 && pacmand_y == -1 && (ch & 2) != 0)
                    || (pacmand_x == 0 && pacmand_y == 1 && (ch & 8) != 0)) {
                pacmand_x = 0;
                pacmand_y = 0;
            }
        }
        pacman_x = pacman_x + pacmanSpeed * pacmand_x;
        pacman_y = pacman_y + pacmanSpeed * pacmand_y;
        if (pacman_x>264) {
        	pacman_x=0;
        }
        if (pacman_x<-5) {
        	pacman_x=264;
        }
        if (pacman_y>264) {
        	pacman_y=0;
        }
        if (pacman_y<-5) {
        	pacman_y=264;
        }
    }

    private void drawPacman(Graphics2D g2d) {
        if (req_dx == -1) {
        	g2d.drawImage(left, pacman_x + 1, pacman_y + 1, this);
        } else if (req_dx == 1) {
        	g2d.drawImage(right, pacman_x + 1, pacman_y + 1, this);
        } else if (req_dy == -1) {
        	g2d.drawImage(up, pacman_x + 1, pacman_y + 1, this);
        } else {
        	g2d.drawImage(down, pacman_x + 1, pacman_y + 1, this);
        }
    }
	
    private void moveGhosts(Graphics2D g2d) {
    	/*
    	 * Check if the ghost can go to this direction and if he can we update it position
    	 */
		int pos;
		int count;
		if (ghost_x%blockSize ==0 && ghost_y % blockSize ==0) {
			pos = ghost_x / blockSize + nBlocks * (int) (ghost_y / blockSize);
			count=0;
			if((screenData[pos] & 1) == 0 && ghost_dx!= 1) {
				dx[count] = -1;
				dy[count] =0;
				count++;
			}
			if((screenData[pos] & 2) == 0 && ghost_dy!= 1) {
				dx[count] = 0;
				dy[count] =-1;
				count++;
			}
			if((screenData[pos] & 4) == 0 && ghost_dx!=-1) {
				dx[count] = 1;
				dy[count] =0;
				count++;
			}
			if((screenData[pos] & 8) == 0 && ghost_dy!=-1) {
				dx[count] = 0;
				dy[count] =1;
				count++;
			}
			
			if (count == 0) {
				if ((screenData[pos] & 15)==15) {
					ghost_dy=0;
					ghost_dx=0;
				}else {
					ghost_dy=-ghost_dy;
					ghost_dx=-ghost_dx;
				}
			}else {
				count = (int) (Math.random()*count);
				if (count>3) {
					count=3;
				}
				
				ghost_dx=dx[count];
				ghost_dy=dy[count];
			}
		}
		ghost_x = ghost_x + (ghost_dx*ghostSpeed);
		ghost_y = ghost_y + (ghost_dy*ghostSpeed);
        if (ghost_x>264) {
        	ghost_x=0;
        }
        if (ghost_x<0) {
        	ghost_x=264;
        }
        if (ghost_y>264) {
        	ghost_y=0;
        }
        if (ghost_y<0) {
        	ghost_y=264;
        }
		drawGhost(g2d,ghost_x+1,ghost_y+1);
		
		if (pacman_x > (ghost_x - 12) && pacman_x < (ghost_x+12)
			&& pacman_y > (ghost_y - 12) && pacman_y < (ghost_y+12)
			&& inGame) {
			dying=true;
		}

	}
	
	private void drawGhost(Graphics2D g2d, int x, int y) {
		g2d.drawImage(ghost, x, y, this);
	}
	
	private void checkMaze() {
		/*
		 * Check if the game is finished
		 */
		int i=0;
		boolean finished =true;
		
		while(i<nBlocks * nBlocks && finished) {
			if((screenData[i] & 48) != 0) {
				finished = false;
			}i++;
		}
		if (finished) {
			
			inGame=false;
		} 
	}
	
	private void death() {
		inGame=false;
	}

    private void drawMaze(Graphics2D g2d) {
    	/*
    	 * Draw the maze : 
    	 * With a coin : 18 top border, 17 left border, 20 right border, 24 bottom border, 16 no border, 19 top and left border, 25 bottom and left border
    	 * 				 28 bottom and right border, 22 top and right border, 21 left and right border, 26 top and bottom border
    	 * Without a coin: 0	
    	 */

        short i = 0;
        int x, y;

        for (y = 0; y < screenSize; y += blockSize) {
            for (x = 0; x < screenSize; x += blockSize) {

                g2d.setColor(new Color(0,72,251));
                g2d.setStroke(new BasicStroke(5));
                
                if ((levelData[i] == 0)) { 
                	g2d.fillRect(x, y, blockSize, blockSize);
                }

                if ((screenData[i] & 1) != 0) { 
                    g2d.drawLine(x, y, x, y + blockSize - 1);
                }

                if ((screenData[i] & 2) != 0) { 
                    g2d.drawLine(x, y, x + blockSize - 1, y);
                }

                if ((screenData[i] & 4) != 0) { 
                    g2d.drawLine(x + blockSize - 1, y, x + blockSize - 1,
                            y + blockSize - 1);
                }

                if ((screenData[i] & 8) != 0) { 
                    g2d.drawLine(x, y + blockSize - 1, x + blockSize - 1,
                            y + blockSize - 1);
                }

                if ((screenData[i] & 16) != 0) { 
                    g2d.setColor(new Color(255,255,255));
                    g2d.fillOval(x + 10, y + 10, 6, 6);
               }

                i++;
            }
        }
    }
	
	private void initGame() {
		/*
		 * initialize the game
		 */
		initLevel();
	}
	
	private void initLevel() {
		/*
		 * Initialize the level
		 */
		for(int i=0;i<nBlocks*nBlocks;i++) {
			screenData[i]=levelData[i];
		}
		
		ghost_y=4*blockSize; //start position
		ghost_x=4*blockSize;
		ghost_dy=0;
		ghost_dx=1;
		ghostSpeed=3;
		
		pacman_x = 7*blockSize; //start position
		pacman_y = 11*blockSize;
		pacmand_x = 0; //reset direction move
		pacmand_y = 0;
		req_dx=0; //reset direction controls
		req_dy=0;
		dying=false;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, d.width, d.height);
		
		drawMaze(g2d);

		if(inGame) {
			playGame(g2d);
		}else {
			showIntroScreen(g2d);
		}
		Toolkit.getDefaultToolkit().sync();
		g2d.dispose();
	}

	class TAdapter extends KeyAdapter{
		/*
		 * Get the key you pressed
		 */
		@Override
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
				else if (key == KeyEvent.VK_ESCAPE && timer.isRunning()) {
					inGame = false;
				}
			} else {
				if(key == KeyEvent.VK_SPACE) {
					inGame=true;
					initGame();
			}
		}
	}
}
	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		
	}

}
