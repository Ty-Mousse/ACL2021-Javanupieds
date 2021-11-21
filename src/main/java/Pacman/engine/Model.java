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
	
	private int nGhosts = 2;//initialisation
	private int[] dx, dy;
	private int[] ghost_x, ghost_y, ghost_dx, ghost_dy, ghostSpeed;
	
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
		up = new ImageIcon("src\\main\\java\\Pacman\\images\\up.gif").getImage();//Make that for all images
		down = new ImageIcon("src\\main\\java\\Pacman\\images\\down.gif").getImage();//Make that for all images
		left = new ImageIcon("src\\main\\java\\Pacman\\images\\left.gif").getImage();//Make that for all images
		right = new ImageIcon("src\\main\\java\\Pacman\\images\\right.gif").getImage();//Make that for all images
		ghost = new ImageIcon("src\\main\\java\\Pacman\\images\\ghost.gif").getImage();//Make that for all images
	}
	
	private void initVariables() {
		screenData = new short[nBlocks*nBlocks];
		d = new Dimension(400,400);//pas sur que c est height et width a try
		ghost_x = new int[nGhosts];
		ghost_dx = new int[nGhosts];
		ghost_y = new int[nGhosts];
		ghost_dy = new int[nGhosts];
		ghostSpeed = new int[nGhosts];
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
			System.out.println(pacman_x+" "+pacman_y);
		}
	}

	
    private void showIntroScreen(Graphics2D g2d) {
    	 
    	String start = "Press SPACE to start";
    	g2d.setFont(smallFont);
        g2d.setColor(Color.yellow);
        g2d.drawString(start, (screenSize)/4, 150);
    }

	
    private void movePacman() {

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
		int pos;
		int count;
		for (int i =0; i<nGhosts;i++) {
			if (ghost_x[i]%blockSize ==0 && ghost_y[i] % blockSize ==0) {
				pos = ghost_x[i] / blockSize + nBlocks * (int) (ghost_y[i] / blockSize);
				count=0;
				if((screenData[pos] & 1) == 0 && ghost_dx[i]!= 1) {
					dx[count] = -1;
					dy[count] =0;
					count++;
				}
				if((screenData[pos] & 2) == 0 && ghost_dy[i]!= 1) {
					dx[count] = 0;
					dy[count] =-1;
					count++;
				}
				if((screenData[pos] & 4) == 0 && ghost_dx[i]!=-1) {
					dx[count] = 1;
					dy[count] =0;
					count++;
				}
				if((screenData[pos] & 8) == 0 && ghost_dy[i]!=-1) {
					dx[count] = 0;
					dy[count] =1;
					count++;
				}
				
				if (count == 0) {
					if ((screenData[pos] & 15)==15) {
						ghost_dy[i]=0;
						ghost_dx[i]=0;
					}else {
						ghost_dy[i]=-ghost_dy[i];
						ghost_dx[i]=-ghost_dx[i];
					}
				}else {
					count = (int) (Math.random()*count);
					if (count>3) {
						count=3;
					}
					
					ghost_dx[i]=dx[count];
					ghost_dy[i]=dy[count];
				}
			}
			ghost_x[i] = ghost_x[i] + (ghost_dx[i]*ghostSpeed[i]);
			ghost_y[i] = ghost_y[i] + (ghost_dy[i]*ghostSpeed[i]);
	        if (ghost_x[i]>264) {
	        	ghost_x[i]=0;
	        }
	        if (ghost_x[i]<0) {
	        	ghost_x[i]=264;
	        }
	        if (ghost_y[i]>264) {
	        	ghost_y[i]=0;
	        }
	        if (ghost_y[i]<0) {
	        	ghost_y[i]=264;
	        }
			drawGhost(g2d,ghost_x[i]+1,ghost_y[i]+1);
			
			if (pacman_x > (ghost_x[i] - 12) && pacman_x < (ghost_x[i]+12)
				&& pacman_y > (ghost_y[i] - 12) && pacman_y < (ghost_y[i]+12)
				&& inGame) {
				dying=true;
			}
		}
	}
	
	private void drawGhost(Graphics2D g2d, int x, int y) {
		g2d.drawImage(ghost, x, y, this);
	}
	
	private void checkMaze() {
		int i=0;
		boolean finished =true;
		
		while(i<nBlocks * nBlocks && finished) {
			if((screenData[i] & 48) != 0) {
				finished = false;
			}i++;
		}
		if (finished) {
			
			if(nGhosts < nGhosts) {
				nGhosts++;
			}
			inGame=false;
			initLevel();
		} 
	}
	
	private void death() {
		inGame=false;
	}

    private void drawMaze(Graphics2D g2d) {

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
		initLevel();
	}
	
	private void initLevel() {
		for(int i=0;i<nBlocks*nBlocks;i++) {
			screenData[i]=levelData[i];
		}
		continueLevel();
	}
	
	private void continueLevel() {
		int dx=1;
		
		for(int i = 0; i<nGhosts;i++) {
			ghost_y[i]=4*blockSize; //start position
			ghost_x[i]=4*blockSize;
			ghost_dy[i]=0;
			ghost_dx[i]=dx;
			dx=-dx;
			ghostSpeed[i]=3;
		}
		
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
