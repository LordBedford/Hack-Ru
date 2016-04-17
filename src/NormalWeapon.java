import java.awt.Rectangle;
import java.awt.geom.Arc2D;

public class NormalWeapon
{
	private String name;
	private int damage;
	private int useTime;
	private int range;
	private double angle;
	
	public NormalWeapon(String name, int damage, int useTime, int range)
	{
		this.name = name;
		this.damage = damage;
		this.useTime = useTime;
		this.range = range;
	}
	
	public void attack()
	{
		angle = Math.atan2((double)Math.abs(Driver.player.getY() - Driver.player.getMouseY()), (double)Math.abs(Driver.player.getX() - Driver.player.getMouseX()));
		System.out.println(Math.toDegrees(angle));
		if(name.equals("Sword"))
		{
			Arc2D arc = new Arc2D.Double();
			Rectangle r = new Rectangle(Driver.player.getX(), Driver.player.getY(), range, range);
			arc.setArc(r, angle - 30, angle + 30, Arc2D.PIE);
			for(int x = 0; x < Driver.creatures.size(); x++)
			{
				if(arc.contains(new Rectangle(Driver.creatures.get(x).getX(), Driver.creatures.get(x).getY(), Driver.creatures.get(x).getWidth(), Driver.creatures.get(x).getHeight())));
				{
					Driver.creatures.get(x).takeDamage(damage);
				}
			}
		}
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getDamage()
	{
		return damage;
	}
	
	public int useTime()
	{
		return useTime;
	}
	
	public int getRange()
	{
		return range;
	}
	
	public double getAngle()
	{
		return angle;
	}
}