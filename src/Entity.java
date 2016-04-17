import java.awt.Graphics2D;
import java.awt.Rectangle;

/*
 * All Entities have: 
 * Health, Damage, Speed, X and Y
 */
public abstract class Entity {
	private int health;
	private int speed;
	private int posx;
	private int posy;
	private int width, height;
	public Entity (int h,int s,int x,int y)
	{
		 health = h;
		 speed = s;
		 posx = x;
		 posy = y;
		 width = 100;
		 height = 100;
	}
	public	int getX()
	{
		return posx;
	}
	public int getY()
	{
		return posy;
	}
	public double getHealth()
	{
		return health;
	}
	public int getSpeed()
	{
		return speed;
	}
	public abstract Rectangle getBounds();
		
	public void update()
	{
		 
	}
	public void draw(Graphics2D g)
	{
		
	}
}
