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
	private double speed;
	private double dx, dy;
	private BufferedImage image;
	private double health;
	private int mana;
	private int manaMax;
	private int mouseX, mouseY;
	/**
	 * 0 = up<br>
	 * 1 = down<br>
	 * 2 = left<br>
	 * 3 = right<br>
	 */
	private boolean[] direction;
	private int dir; //direction in int numerals for getDirection method
//	private int tick;
//	private boolean stretch;
	
	
	public Player(int h, int s, int x, int y)
	{
		super(h,s,x,y);
		pos = new Location(x, y);
		dx = 0;
		dy = 0;
		speed = s;
		direction = new boolean[4];
		direction[1] = true;
		dir = 2;
		mana = 10000;
		manaMax = 10000;
		health = 100.0;
//		tick = 0;
//		stretch = false;

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
//		tick++;
		try {
			if(direction[0] && direction[2]) //up left
			{
				image = ImageIO.read(new File("res/wizkidupleft.png"));
				dir = 4;
			}
			else if(direction[0] && direction[3]) //up right
			{
				image = ImageIO.read(new File("res/wizkidupright.png"));
				dir = 5;
			}
			else if(direction[1] && direction[2]) //down left
			{
				image = ImageIO.read(new File("res/wizkiddownleft.png"));
				dir = 6;
			}
			else if(direction[1] && direction[3]) //down right
			{
				image = ImageIO.read(new File("res/wizkiddownright.png"));
				dir = 7;
			}
			else if(direction[0]) //up
			{
				image = ImageIO.read(new File("res/whizkidjback.png"));
				dir = 0;
			}
			else if(direction[1]) //down
			{
				image = ImageIO.read(new File("res/wizkidj.png"));
				dir = 1;
			}
			else if(direction[2]) //left
			{
				image = ImageIO.read(new File("res/whizkidsideleft.png"));
				dir = 2;
			}
			else if(direction[3]) //right
			{
				image = ImageIO.read(new File("res/whizkidsideright.png"));
				dir = 3;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
//		if (tick % 25 == 0)
//			stretch = !stretch;
//			
////		g.drawImage(image, pos.getX(), pos.getY(), null);
//		if(stretch)
//		{
//			AffineTransform save = g.getTransform();
//			AffineTransform scale = new AffineTransform();
//			g.setTransform(scale);
//			g.scale(1.05, 1.05);
//			g.translate(-(pos.getX() * .05), -(pos.getY() * .05));
//			g.drawImage(image, pos.getX(), pos.getY(), null);
//			g.setTransform(save);
//		}
//		else
//		{
			g.drawImage(image, (int)pos.getX(), (int)pos.getY(), null);
//		}
		
		for(int i = 0; i < direction.length; i++)
			direction[i] = false;
	}

	public boolean hasMana() {if(mana > 0) return true; else return false;}
	public void setUp(boolean b) {up = b;}
	public void setDown(boolean b) {down = b;}
	public void setLeft(boolean b) {left = b;}
	public void setRight(boolean b) {right = b;}
	public void setMouseX(int x) {mouseX = x;}
	public void setMouseY(int y) {mouseY = y;}
	public void setMana(int x) {mana = x;}
	public void decMana(int x) {mana-= x;}
	public void decHealth(double x) {health -= x;};
	public double getX() {return pos.getX();}
	public double getY() {return pos.getY();}
	public int getDamage() {return 0;}
	public double getHealth() {return health;}
	public double getSpeed() {return speed;}
	public int getMana() {return mana;}
	public int getDirection() {return dir;}
	public int getMaxMana() {return manaMax;}
	public Rectangle getBounds(){return new Rectangle((int)pos.getX(), (int)pos.getY(), 
			(int)pos.getX() + image.getWidth() - 10, (int)pos.getY() + image.getHeight() - 10);}
	public int getMouseX() {return mouseX;}
	public int getMouseY() {return mouseY;}

	@Override
	public void setSpeed(double x) {
		// TODO Auto-generated method stub
		
	}
	

}
