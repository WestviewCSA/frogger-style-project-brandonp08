import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Russell{
	private Image forward; 	
	private AffineTransform tx;
	
	int dir = 0; 					//0-forward, 1-backward, 2-left, 3-right
	int width, height;
	int x, y;						//position of the object
	int vx, vy;						//movement variables
	double scaleWidth = 1;		//change to scale image
	double scaleHeight = 1; 		//change to scale image

	public Russell() {
		forward 	= getImage("/imgs/"+"russellleft.png"); //load the image for Tree
		
		//alter these
		width = 90;
		height = 90;
		x = -width;
		y = 600;
		vx = -2;
		vy = 0;
		
		tx = AffineTransform.getTranslateInstance(0, 0);
		
		init(x, y); 				//initialize the location of the image
									//use your variables
		
	}
	
	public boolean collided (TWODFromGorillaz character) {
		//represents hitbox and checks if they are intersecting
		Rectangle main = new Rectangle(
				character.getX()+35,
				character.getY()+20,
				character.getWidth()-47,
				character.getHeight()-25
				);
		
		
		Rectangle thisObject = new Rectangle(x+5, y-20, width+2, height+40);
		
		return main.intersects(thisObject);
	}
	public int getVx() {
		return vx;
	}
	//2nd construct allow setting x and y during construction
	public Russell(int x, int y) {
		
		this();
	
		this.x = x;
		this.y = y;
		
	}

	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		//update x and y if using vx vy variables
		x+=vx;
		y+=vy;	
		
		if(x < -100) {
			x = 800;
		}
		
		init(x,y);
		
		g2.drawImage(forward, tx, null);
		
		if(Frame.debugging) {
			g.setColor(Color.green);
			g.drawRect(x+5, y-20, width+2, height+40);
		}
		
		
		
	}
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(scaleWidth, scaleHeight);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Russell.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
