import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Projectile {
	
	protected Location pos;
	protected double speed;
	protected double dx, dy;
	protected BufferedImage image;
	protected int width, height;
	protected int damage;
	public Projectile(int direction, double x, double y, int damagen)
	{
		speed = 10;
		damage = damagen;
		switch (direction){
		case 0: //up
			dx = 0; dy = -1 * speed; break;
		case 1: //down
			dx = 0; dy = 1 * speed; break;
		case 2: //left
			dx = -1 * speed; dy = 0; break;
		case 3: //right
			dx = 1 * speed; dy = 0; break;
		case 4: //up left
			dx = -1 * speed; dy = -1 * speed; break;
		case 5: //up right
			dx = 1 * speed; dy = -1 * speed; break;
		case 6: //down left
			dx = -1 * speed; dy = 1* speed; break;
		case 7: //down right
			dx = 1 * speed; dy = 1 * speed; break;
		default:
			System.out.println("No projectile direction"); break;
		
		}
		width = height = 0;
		pos = new Location(x, y);
		 //fix with dimensions
	}
	
	public void update()
	{
		pos.incX(dx);
		pos.incY(dy);
	}
	
	public abstract void draw(Graphics2D g);
	
	public double getX() {return pos.getX();}
	public double getY() {return pos.getY();}
	public int getWidth() {return this.width;}
	public int getHeight() {return this.height;}
	public int getDamage(){return this.damage;}
	public Rectangle getBounds() {return new Rectangle((int)pos.getX(), (int)pos.getY(), width, height);}

}

