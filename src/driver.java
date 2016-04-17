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

	private int mouseX, mouseY;
	public static Player player;
	private Monster monster;
<<<<<<< HEAD
	private ArrayList<Entity> creatures = new ArrayList();
	private int monsterSpawnRate = 60;//Spawns monsters every x ticks
=======
	private ArrayList<Entity> creatures;
	private ArrayList<Projectile> magic;
	private int monsterSpawnRate = 600;//Spawns monsters every x ticks
>>>>>>> 6ca60ff0c3414af8c00a1248912cfc13832fc564
	private int monsterSpawnCounter = 0;//Counts ticks till monster spawn

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
		creatures = new ArrayList<Entity>();
		creatures.add(player = new Player(100,4,0,0));
		creatures.add(monster = new Monster (100,2,0,0,0));
		magic = new ArrayList<Projectile>();
	}
	//update
	public void tick ()
	{
		if(monsterSpawnCounter == monsterSpawnRate)
		{
			int spawnpos = (int) (Math.random() * 1080);//Monster random spawning
			int side = (int)(Math.random() * 4);
			if(side == 0)
				creatures.add(new Monster(100,2,0,spawnpos,0));
			else if(side == 1)
				creatures.add(new Monster(100,2,0,0,spawnpos));
			else if(side == 2)
				creatures.add(new Monster(100,2,0,1080,spawnpos));
			else
				creatures.add(new Monster(100,2,0,spawnpos,810));
			monsterSpawnCounter = 0;
		}
		else
			monsterSpawnCounter++;
		for(int i = 0; i < creatures.size();i++)//updates all entities in the array.
		{
			creatures.get(i).update();
		}
		if(player.hasMana() && !magic.isEmpty())
			for(int i = 0; i < magic.size(); i++)
				magic.get(i).update();
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
		} catch (IOException e) {
			e.printStackTrace();
		}

		g.drawImage(image, 0, 0,1080,810, null);
		for(int i = 0; i < creatures.size();i++)
		{
			creatures.get(i).draw((Graphics2D)g);
		}
		if(player.hasMana() && !magic.isEmpty())
		{
			for(int i = 0; i < magic.size(); i++)
				magic.get(i).draw((Graphics2D)g);
		}
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
		if(keyCode == KeyEvent.VK_J){
			magic.add(new Projectile(player.getDirection(), player.getX(), player.getY()));
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
