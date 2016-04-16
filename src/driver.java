import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class driver extends JPanel implements KeyListener, MouseMotionListener
{
<<<<<<< HEAD
	private Player player;
	private int mouseX, mouseY;
	private Monster monster;

=======
	private int mouseX, mouseY;
	public static Player player;
	private Monster monster;
<<<<<<< HEAD
	private ArrayList<Entity> creatures = new ArrayList();
	private int monsterSpawnRate = 500;//Spawns monsters every x ticks
	private int monsterSpawnCounter = 0;//Counts ticks till monster spawn
=======
>>>>>>> 1291da45758cabfcfdf93893b72fe983f8f94993
>>>>>>> 956a62b4bf946c1dc8d5ea90aa842e865b8376c5
	public static void main(String[] args) 
	{
		JFrame frame = new JFrame ("Game thing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		driver panel = new driver();
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask(){public void run(){panel.tick();panel.repaint();
		}}, 0, 1000/60);//handles tick and repainting the jframe.
		 
		
	}
	public driver ()
	{
		this.setPreferredSize(new Dimension(1080,810));
		setFocusable(true);
		requestFocus();
		addKeyListener(this);
		addMouseMotionListener(this);
		creatures.add(player = new Player(100,4,0,0));
		creatures.add(monster = new Monster (100,2,0,0,0));
	}
	//update
	public void tick ()
	{
		if(monsterSpawnCounter == monsterSpawnRate)
		{
			creatures.add(new Monster(100,2,0,0,0));
			monsterSpawnCounter = 0;
		}
		else
			monsterSpawnCounter++;
		for(int i = 0; i < creatures.size();i++)//updates all entities in the array.
		{
			creatures.get(i).update();
		}
	}
	
	//render
	public void paintComponent (Graphics g)
	{
		super.paintComponent(g);
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("res/GroundTile.png"));
			g.drawString("Mouse Pos: " + mouseX + ", " + mouseY, 500, 30);
			g.drawString("Player Pos: " + player.getX() + ", " + player.getY(), 500, 40);
			g.drawString("Angle: " + player.getAngle(), 500, 50);
			monster.draw((Graphics2D)g);
		} catch (IOException e) {
			e.printStackTrace();
		}
<<<<<<< HEAD
		g.drawImage(image, 0, 0, null);
=======
		g.drawImage(image, 0, 0,1080,810, null);
		player.draw((Graphics2D) g);
		monster.draw((Graphics2D)g);
>>>>>>> 1291da45758cabfcfdf93893b72fe983f8f94993
	}
	@Override
	public void keyTyped(KeyEvent key) {
		
	}
	@Override
	public void keyPressed(KeyEvent key) {
		int keyCode = key.getKeyCode();
		if(keyCode == KeyEvent.VK_W){
			player.setUp(true);
		}
		if(keyCode == KeyEvent.VK_S){
			player.setDown(true);
		}
		if(keyCode == KeyEvent.VK_A){
			player.setLeft(true);
		}
		if(keyCode == KeyEvent.VK_D){
			player.setRight(true);
		}
	}
	@Override
	public void keyReleased(KeyEvent key) {
		int keyCode = key.getKeyCode();
		if(keyCode == KeyEvent.VK_W){
			player.setUp(false);
		}
		if(keyCode == KeyEvent.VK_S){
			player.setDown(false);
		}
		if(keyCode == KeyEvent.VK_A){
			player.setLeft(false);
		}
		if(keyCode == KeyEvent.VK_D){
			player.setRight(false);
		}
	}
	@Override
	public void mouseDragged(MouseEvent e) {
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		player.setMouseX(e.getX());
		player.setMouseY(e.getY());
	}
}
