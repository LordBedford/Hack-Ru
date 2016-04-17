import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FireBall extends Projectile {
	
	public FireBall(int direction, int x, int y, int damagen)
	{
		super(direction, x, y, damagen);
	}
	public void draw(Graphics2D g)
	{
		try {
			image = ImageIO.read(new File("res/fireball.png"));
			width = image.getHeight();
			height = image.getHeight();
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(image, pos.getX(), pos.getY(), null);
	}
}