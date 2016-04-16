import java.awt.Graphics2D;
import java.awt.Rectangle;

/*
 * All Entities have: 
 * Health, Damage, Speed, X and Y
 */
public interface Entity {

	int getX();
	int getY();
	int getDamage();
	int getHealth();
	int getSpeed();
	Rectangle getBounds();
	
	void update();
	void draw(Graphics2D g);
}
