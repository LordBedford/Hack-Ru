import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FrostSpike extends Projectile {
	
	public FrostSpike(int direction, int x, int y)
	{
		super(direction, x, y, 1);
	}
	public void draw(Graphics2D g)
	{
		try {
			image = ImageIO.read(new File("res/frost spike.png"));
			width = image.getHeight();
			height = image.getHeight();
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(image, (int)pos.getX(), (int)pos.getY(), null);
	}
}