
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
		if(Player.getY() > posy)
			posy = posy + speed;
		else if (player.getY < monster.getY)
			posy -= speed;
		 
		
	}
	
}