import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
	
	public static boolean debugging = true ;
	
	//Timer related variables
	int waveTimer = 5; //each wave of enemies is 20s
	long ellapseTime = 0;
	Font timeFont = new Font("Courier", Font.BOLD, 70);
	int level = 0;
	int lives = 7;
	int score = 0;
	Font myFont = new Font("Courier", Font.BOLD, 40);
	SimpleAudioPlayer backgroundMusic = new SimpleAudioPlayer("feelgoodinc.wav", true);
//	Music soundBang = new Music("bang.wav", false);+
	
//	Music soundHaha = new Music("haha.wav", false);
	
	TWODFromGorillaz twoD = new TWODFromGorillaz(200, 750);
	Sprite2 beach = new Sprite2();
	River river1 = new River();
	gameover gameOver = new gameover();
	youwin youWin = new youwin();
	TWODScrolling[] row1 = new TWODScrolling[6]; 
	Noodle[] row2 = new Noodle[6]; 
	Russell[] row3 = new Russell[6];
	Russell2[] row4 = new Russell2[6];
	//frame width/height
	int width = 800;
	int height = 800;	
	

	public void paint(Graphics g) {
		super.paintComponent(g);
		
		beach.paint(g);
		river1.paint(g);
		for(Russell2 obj : row4) {
			obj.paint(g);
		}
		for(Russell obj : row3) {
			obj.paint(g);
		}
		for(TWODScrolling obj : row1) {
			obj.paint(g);
		}
		
		for(Noodle obj : row2) {
			obj.paint(g);
		}
		
		twoD.paint(g);
		
		if(lives == 0) {
			gameOver.paint(g);
		}
		if(score == 3) {
			youWin.paint(g);
		}
		
		//have the row 1objects on the screen for each obj
		
	
		if(twoD.getX()< 0) {
			twoD.x = 0;
		}
		if(twoD.getX() > 700) {
			twoD.x = 700;
		}
		if(twoD.getY()> 700) {
			twoD.y = 700;
		}
		if(twoD.getY()< 0) {
			twoD.x = 200;
			twoD.y = 750;
			score += 1;
			System.out.println("Your score is " + score);
		}
		
		for(TWODScrolling obj : row1) {
			
			if(obj.collided(twoD)) {
				System.out.println("Ouch");
				twoD.x = 200;
				twoD.y = 750;
				lives-= 1;
			}
			
		for(Noodle obj1: row2) {
				
			if(obj1.collided(twoD)) {
				System.out.println("Ouch");
				twoD.x = 200;
				twoD.y = 750;
				lives-=1;
				}
			
		}
		boolean riding = false;
		
		for(Russell obj1 : row3) {
			if(obj1.collided(twoD)) {
				twoD.vx=-2;
				twoD.x = obj1.x;
				riding = true;
			}else if(!obj1.collided(twoD)) {
					twoD.vx = 0;
					twoD.x = twoD.x;
					riding = false;
				}
		
			}
		for(Russell2 obj1 : row4) {
			if(obj1.collided(twoD)) {
				twoD.vx=2;
				twoD.x = obj1.x;
				riding = true;
			}else if(!obj1.collided(twoD)) {
					twoD.vx = 0;
					twoD.x = twoD.x;
					riding = false;
				}
		
			}
			if(river1.collided(twoD) && riding == false) {
				System.out.println("Ouch");
				twoD.x = 200;
				twoD.y = 750;
				lives-=1;
			
			}else if(river1.collided(twoD) && riding == true) {
				twoD.x = twoD.x;
				twoD.y = twoD.y;
				
				
			}
			g.setColor(Color.white);
			g.drawString("Score: " + score, 8, 50);
			g.drawString("Lives: " + lives, 80, 50);
		}

			}
		
		
		// main charcater stops movigin if not on a rideable object but limit y
		
		
		
		
	
	
	
	
	public static void main(String[] arg) {
		Frame f = new Frame();

	}
	
	public Frame() {
		JFrame f = new JFrame("Duck Hunt");
		f.setSize(new Dimension(width, height));
		f.setBackground(Color.white);
		f.add(this);
		f.setResizable(false);
 		f.addMouseListener(this);
		f.addKeyListener(this);
	
		//backgroundMusic.play();

		for(int i = 0; i < row1.length; i++) {
			row1[i] = new TWODScrolling( i*150 , 300);
		}
		

		for(int i = 0; i < row2.length; i++) {
			row2[i] = new Noodle( i*150 , 100);
		}
		for(int i = 0; i < row3.length; i++) {
			row3[i] = new Russell( i*150 , 600);
		}
		for(int i = 0; i < row4.length; i++) {
			row4[i] = new Russell2( i*150 , 400);
		}
		//you will need to import a couple of classes to make it fully 
		//functional! use eclipse quick-fixes
		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon("torch.png").getImage(),
				new Point(0,0),"custom cursor"));	
		
		
		Timer t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent m) {
		
	
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getKeyCode());
		if(arg0.getKeyCode() == 87) {
			//move main character up
			twoD.move(0);
		}else if (arg0.getKeyCode() == 83) {
			//move main character down
			twoD.move(1);
		}else if (arg0.getKeyCode() == 68) {
			//move main character down
			twoD.move(3);
		}else if (arg0.getKeyCode() == 65) {
			//move main character down
			twoD.move(2);
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
