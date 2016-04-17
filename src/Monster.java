import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Monster extends Entity
{
	//Monster instance variables
	private int health;
	private int speed;
	private int damage;
	private int posx;
	private int posy;
	private Rectangle hitBox;
	
	public Monster (int h,int s,int d, int x, int y)//Creates basic monster object
	{
		super(h, s, x, y);
		health = h;
		speed = s;
		damage = d;
		posx = x;
		posy = y;
		hitBox = new Rectangle(x,y,100,100);
	}
	public int getHealth()//returns monster's current health
	{
		return health;
	}
	public int getSpeed()//returns monster's current speed
	{
		return speed;
	}
	public int getDamage()//returns monster's current damage
	{
		return damage;
	}
	public void update()
	{
		int playerposy = Driver.player.getY() - 50;
		int playerposx = Driver.player.getX() - 10;
		if(Driver.player.getY() != posy && Driver.player.getX() != posx)
		{
			double tempspeed = speed/2;
			if(playerposy > posy)//Changes monsters y to be equal to player
				posy = (int)(posy + tempspeed);
			else if (playerposy < posy)
				posy -= (int)tempspeed;
			if(playerposx > posx)
			{
				posx += (int)tempspeed;
				
			}
			else if(playerposx < posx)
			{
				posx -= (int)tempspeed;
//			}
//		double distancex = Math.abs(playerposx - posx);
//		double distancey = Math.abs(playerposy - posy);
//		double distancetot = Math.sqrt(Math.pow(distancex, 2) + Math.pow(distancey, 2));
//		posx =(int) (distancex / distancetot);
//		posy = (int)(distancey / distancetot);
		
			hitBox.setFrame(posx,posy,100,100);

		}
		else
		{
			if(Driver.player.getY() > posy)//Changes monsters y to be equal to player
				posy = posy + speed;
			else if (Driver.player.getY() < posy)
				posy -= speed;
			if(Driver.player.getX() > posx)
			{
				posx += speed;
			}
			else if(Driver.player.getX() < posx)
			{
				posx -= speed;
			}
		}
		}
	}

//	}
	public void draw(Graphics2D g){
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("res/ghost2.0.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(image, posx, posy, null);
	}
	public Rectangle getHitBox()
	{
		return hitBox;
	}
	public boolean takeDamage(int damage)
	{
		if(health - damage > 0)
		{
			health -= damage;
			return false;
		}
		else
		{
			return true;
		}
	}
}
	
