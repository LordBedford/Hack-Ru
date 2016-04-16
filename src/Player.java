import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player implements Entity {
	
	private int x, y;
	
	public Player(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void update(){
		
	}
	
	public void draw(Graphics2D g){
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("res/blood.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(image, x, y, null);
	}

	public int getX() {return x;}
	public int getY() {return y;}
	public int getDamage() {}
	public int getHealth() {}
	public int getSpeed() {}
	public Rectangle getBounds(){}

}
