import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends Entity {
	
	private Location pos; //position
	private boolean up, down, left, right;
	private int speed;
	private int dx, dy;
	
	public Player(int h, int s, int x, int y)
	{
		super(h,s,x,y);
		pos = new Location(x, y);
		dx = 0;
		dy = 0;
		speed = s;
	}
	
	public void update(){
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
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("res/Cat.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(image, pos.getX(), pos.getY(), null);
	}

	public void setUp(boolean b) {up = b;}
	public void setDown(boolean b) {down = b;}
	public void setLeft(boolean b) {left = b;}
	public void setRight(boolean b) {right = b;}
	public int getX() {return pos.getX();}
	public int getY() {return pos.getY();}
	public int getDamage() {return 0;}
	public int getHealth() {return 0;}
	public int getSpeed() {return speed;}
	public Rectangle getBounds(){return null;}

}
