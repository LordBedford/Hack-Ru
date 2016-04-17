import java.awt.Graphics2D;
import java.awt.Rectangle;

/*
 * All Entities have: 
 * Health, Damage, Speed, X and Y
 */
public abstract class Entity {
	private int health;
	private double speed;
	private double posx;
	private double posy;
	private int width, height;
	public Entity (int h, double s,int x,int y)
	{
		 health = h;
		 speed = s;
		 posx = x;
		 posy = y;
		 width = 100;
		 height = 100;
	}
	public	double getX()
	{
		return posx;
	}
	public double getY()
	{
		return posy;
	}
	public double getHealth()
	{
		return health;
	}
	public double getSpeed()
	{
		return speed;
	}
	
	public abstract void setSpeed(double x);
	public abstract Rectangle getBounds();
		
	public void update()
	{
		 
	}
	public void draw(Graphics2D g)
	{
		
	}
}
