import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends Entity {
	
	private Location pos; //position
	private boolean up, down, left, right;
	private int speed;
	private int dx, dy;
	private BufferedImage image;
	private int mana;
	private int mouseX, mouseY;
	private double mouseAngle;
	
	
	public Player(int h, int s, int x, int y)
	{
		super(h,s,x,y);
		pos = new Location(x, y);
		dx = 0;
		dy = 0;
		speed = s;
	}
	
	public void update(){
		//move player
		if(up)
			dy = -speed;
		if(down)
			dy = speed;
		if(left)
			dx = -speed;
		if(right)
			dx = speed;
		pos.incX(dx);
		pos.incY(dy);
		
		dx = dy = 0;
	}
	
	public void draw(Graphics2D g){
		try {
			image = ImageIO.read(new File("res/Cat.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		AffineTransform saveT = g.getTransform();
		AffineTransform rotate = new AffineTransform();
		g.setTransform(rotate);
//		//calculate angle to rotate towards mouse
		double rotateY = ((double)mouseY) - (pos.getY() + image.getHeight() / 2);
		double rotateX = ((double)mouseX) - (pos.getX() + image.getWidth() / 2); //difference mouseX and middle of image x
		mouseAngle = Math.atan(rotateY/rotateX);
//		//rotate player to face mouse position
//		
//		
//		rotate.translate((image.getWidth() + pos.getX()) / 2, (image.getHeight() + pos.getY()) / 2);
		rotate.rotate(mouseAngle, pos.getX() + image.getWidth() / 2, pos.getY() + image.getHeight() / 2);
//		rotate.translate(-image.getWidth()/2, -image.getHeight()/2);
		

		
//		g.drawImage(image, rotate, null);
		g.drawImage(image, pos.getX(), pos.getY(), null);
		g.setTransform(saveT);
	}

	public void setUp(boolean b) {up = b;}
	public void setDown(boolean b) {down = b;}
	public void setLeft(boolean b) {left = b;}
	public void setRight(boolean b) {right = b;}
	public void setMouseX(int x) {mouseX = x;}
	public void setMouseY(int y) {mouseY = y;}
	public double getAngle() {return Math.toDegrees(mouseAngle);}
	public int getX() {return pos.getX();}
	public int getY() {return pos.getY();}
	public int getDamage() {return 0;}
	public int getHealth() {return 0;}
	public int getSpeed() {return speed;}
	public Rectangle getBounds(){return new Rectangle(pos.getX(), pos.getY(), 
			pos.getX() + image.getWidth(), pos.getY() + image.getHeight());}

}
