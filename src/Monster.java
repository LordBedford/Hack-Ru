import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Monster extends Entity
{
	//Monster instance variables
	private double health;
	private double speed;
	private double damage;
	private double posx;
	private double posy;
	private int width, height;
	private boolean frozen;
	private int count; //time frozen
	
	public Monster (int h, double s,int d, int x, int y)//Creates basic monster object
	{
		super(h, s, x, y);
		health = h;
		speed = s;
		damage = d;
		posx = x;
		posy = y;
		frozen = false;
		count = 0;
	}
	public double getHealth()//returns monster's current health
	{
		return health;
	}
	public double getSpeed()//returns monster's current speed
	{
		return speed;
	}
	public void setSpeed(double s)
	{
		speed = s;
		frozen = true;
	}
	public double getDamage()//returns monster's current damage
	{
		return damage;
	}
	public void setX(int x){posx = x;}
	public void setY(int y){posy = y;}
	
	public void update()
	{
		if(frozen)
			count++;
		if(count % 120 == 0)
		{
			frozen = false;
			count = 0;
			speed = 2;
		}
		double playerposy = Driver.player.getY() - 50;
		double playerposx = Driver.player.getX() - 10;
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

	public void draw(Graphics2D g){
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("res/ghost2.0.png"));
			width = image.getWidth();
			height = image.getHeight();
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(image, (int)posx, (int)posy, null);
	}
	public Rectangle getBounds()
	{
		return new Rectangle((int)posx, (int)posy, 80, 80);
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
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
}
	
