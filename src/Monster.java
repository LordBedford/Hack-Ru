import java.awt.Graphics2D;
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
	
	public Monster (int h,int s,int d, int x, int y)//Creates basic monster object
	{
		super(h, s, x, y);
		health = h;
		speed = s;
		damage = d;
		posx = x;
		posy = y;
		
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
		/*if(driver.player.getY() > posy)//Changes monsters y to be equal to player
			posy = posy + speed;
		else if (driver.player.getY() < posy)
			posy -= speed;
		if(driver.player.getX() > posx)
		{
			posx += speed;
			System.out.println("Right");
		}
		else if(driver.player.getX() < posx)
		{
			posx -= speed;
		}
		*/
		double tempx = posx;
		double tempy = posy;
		double theta = Math.cos(tempx);
		double radius = speed;
		
		if(driver.player.getY() > posy)
			posy = posy + (int)(radius * Math.sin(theta));
		else if (driver.player.getY() < posy)
			posy -= posy - (int)(radius * Math.sin(theta));;
		if(driver.player.getX() > posx)
		{
			posx += (int)(radius * Math.cos(theta));
		}
		else if(driver.player.getX() < posx)
		{
			posx -= (int)(radius * Math.cos(theta));
		}
		System.out.println(posy);
		System.out.println(posx);
	}
	public void draw(Graphics2D g){
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("res/blood.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(image, posx, posy, null);
	}
}
	
