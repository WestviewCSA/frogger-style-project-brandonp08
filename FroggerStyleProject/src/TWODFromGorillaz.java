import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class TWODFromGorillaz{
	private Image forward; 	
	private AffineTransform tx;
	
	int dir = 0; 					//0-forward, 1-backward, 2-left, 3-right
	int width, height;
	int x, y;						//position of the object
	int vx, vy;						//movement variables
	double scaleWidth = 1;		//change to scale image
	double scaleHeight = 1; 		//change to scale image

	public TWODFromGorillaz() {
		forward 	= getImage("/imgs/"+"2d-pixilart.png"); //load the image for Tree
		
		//alter these
		width = 70;
		height = 70;
		x = 200;
		y = 200;
		vx = 0;
		vy = 0;
		
		tx = AffineTransform.getTranslateInstance(0, 0);
		
		init(x, y); 				//initialize the location of the image
									//use your variables
		
	}
	
	
	/*
	 * Collision detection with character class
	 */
	
	
	//2nd construct allow setting x and y during construction
	public TWODFromGorillaz(int x, int y) {
		
		this();
	
		this.x = x;
		this.y = y;
		
	}
	
	
	
	public void move(int dir) {
		switch (dir) {
		case 0:
			y -= 60;
		
			break;
			
		case 1: 
			y+= 60;
			break;
		case 2:
			x-= 60;
			break;
		
		case 3:
			x+= 60;
			break;
		}
	}
	public void setVx(int newVelocity) {
		vx = newVelocity;
	}
	public int getVx() {
		return vx;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}

	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		//update x and y if using vx vy variables
		x+=vx;
		y+=vy;	
		
		init(x,y);
		
		g2.drawImage(forward, tx, null);
		
		if(Frame.debugging) {
			g.setColor(Color.green);
			g.drawRect(x+35, y+20, width-47, height-25);
		}
		
		
		
	}
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(scaleWidth, scaleHeight);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = TWODFromGorillaz.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
