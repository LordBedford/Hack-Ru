import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Projectile {
	
	private Location pos;
	private int speed;
	private int dx, dy;
	private BufferedImage image;
	
	public Projectile(int direction, int x, int y)
	{
		speed = 6;
		switch (direction){
		case 0: //up
			dx = 0; dy = -1; break;
		case 1: //down
			dx = 0; dy = 1; break;
		case 2: //left
			dx = -1; dy = 0; break;
		case 3: //right
			dx = 1; dy = 0; break;
		case 4: //up left
			dx = -1; dy = -1; break;
		case 5: //up right
			dx = 1; dy = -1; break;
		case 6: //down left
			dx = -1; dy = 1; break;
		case 7: //down right
			dx = 1; dy = 1; break;
		default:
			System.out.println("No projectile direction"); break;
		}
		
		pos = new Location(x, y);	
	}
	
	public void update()
	{
		pos.incX(dx);
		pos.incY(dy);
	}
	
	public void draw(Graphics2D g)
	{
		try {
			image = ImageIO.read(new File("res/cat.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(image, pos.getX(), pos.getY(), null);
	}
}
