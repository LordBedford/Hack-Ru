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