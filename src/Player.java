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
	private boolean[] direction;
	
	
	public Player(int h, int s, int x, int y)
	{
		super(h,s,x,y);
		pos = new Location(x, y);
		dx = 0;
		dy = 0;
		speed = s;
		direction = new boolean[4];
		direction[1] = true;
	}
	
	public void update(){
		//move player
		if(up)
		{
			dy = -speed;
			direction[0] = true;
		}
		if(down)
		{
			dy = speed;
			direction[1] = true;
		}
		if(left)
		{
			dx = -speed;
			direction[2] = true;
		}
		if(right)
		{
			dx = speed;
			direction[3] = true;
		}
		pos.incX(dx);
		pos.incY(dy);
		
		dx = dy = 0;
	}
	
	public void draw(Graphics2D g){
		try {
			if(direction[0] && direction[2])
				image = ImageIO.read(new File("res/wizkidj.jpg"));
			else if(direction[0] && direction[3])
				image = ImageIO.read(new File("res/whizkidjback.jpg"));
			else if(direction[1] && direction[2])
				image = ImageIO.read(new File("res/whizkidjback.jpg"));
			else if(direction[1] && direction[2])
				image = ImageIO.read(new File("res/whizkidjback.jpg"));
			else if(direction[0])
				image = ImageIO.read(new File("res/whizkidjback.jpg"));
			else if(direction[1])
				image = ImageIO.read(new File("res/wizkidj.jpg"));
			else if(direction[2])
				image = ImageIO.read(new File("res/whizkidjback.jpg"));
			else if(direction[3])
				image = ImageIO.read(new File("res/whizkidjback.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(image, pos.getX(), pos.getY(), null);
		
		for(int i = 0; i < direction.length; i++)
			direction[i] = false;
		

	}

	public void setUp(boolean b) {up = b;}
	public void setDown(boolean b) {down = b;}
	public void setLeft(boolean b) {left = b;}
	public void setRight(boolean b) {right = b;}
	public void setMouseX(int x) {mouseX = x;}
	public void setMouseY(int y) {mouseY = y;}
	public int getX() {return pos.getX();}
	public int getY() {return pos.getY();}
	public int getDamage() {return 0;}
	public int getHealth() {return 0;}
	public int getSpeed() {return speed;}
	public Rectangle getBounds(){return new Rectangle(pos.getX(), pos.getY(), 
			pos.getX() + image.getWidth(), pos.getY() + image.getHeight());}

}
