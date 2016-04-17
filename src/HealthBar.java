import java.awt.Color;
import java.awt.Graphics2D;

public class HealthBar {
	
	private double health;
	private double green;
	private double red;
	
	public HealthBar()
	{
		green = 100 * 2;
		red = 100 * 2;
	}
	
	public void setHealth(double h) {green = h * 2;};
	
	public void draw(Graphics2D g){
		g.setColor(Color.RED);
		g.fillRect(800, 20, (int) red, 20);
		g.setColor(Color.GREEN);
		g.fillRect(800, 20, (int) green, 20);
	}
}
