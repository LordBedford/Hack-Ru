import java.awt.Color;
import java.awt.Graphics2D;

public class ManaBar {
	
	private double health;
	private double blue;
	private double red;
	
	public ManaBar()
	{
		blue = 100 * 2;
		red = 100 * 2;
	}
	
	public void setMana(double m) {blue = m * 10 * 2;};
	
	public void draw(Graphics2D g){
		g.setColor(Color.RED);
		g.fillRect(800, 45, (int) red, 20);
		g.setColor(Color.BLUE);
		g.fillRect(800, 45, (int) blue, 20);
	}
}
