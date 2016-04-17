import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Projectile {
	
	private Location pos;
	private int speed;
	private int dx, dy;
	private BufferedImage image;
	private int width, height;
	private int damage;
	public Projectile(int direction, int x, int y, int damagen)
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
	
	public void draw(Graphics2D g)
	{
		try {
			image = ImageIO.read(new File("res/flyingspider.png"));
			width = image.getHeight();
			height = image.getHeight();
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(image, pos.getX(), pos.getY(), null);
	}
	
	public int getX() {return pos.getX();}
	public int getY() {return pos.getY();}
	public int getWidth() {return this.width;}
	public int getHeight() {return this.height;}

	public int getDamage(){return this.damage;}

	public Rectangle getBounds() {return new Rectangle(pos.getX(), pos.getY(), width, height);}

}

