public class NormalWeapon
{
	private String name;
	private int damage;
	private int useTime;
	private int range;
	
	public NormalWeapon(String name, int damage, int useTime, int range)
	{
		this.name = name;
		this.damage = damage;
		this.useTime = useTime;
		this.range = range;
	}
	
	public void attack()
	{
		double angle = Math.atan2((double)(Driver.player.getY() - Driver.player.getMouseY()), (double)(Driver.player.getX() - Driver.player.getMouseX()));
		if(name.equals("Sword"))
		{
			
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
}