import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

public class Driver extends JPanel implements KeyListener, MouseMotionListener, MouseListener
{

	private int mouseX, mouseY;
	private final int width = 1080, height = 810;
	public static Player player;
	private NormalWeapon normWeapon;
	private Monster monster;
	private ArrayList<Monster> creatures;
	private ArrayList<Projectile> magic;
	private int monsterSpawnRate = 300;//Spawns monsters every x ticks
	private int monsterSpawnCounter = 0;//Counts ticks till monster spawn
	private final int MONSTERSPAWNCAP = 10;
	private BufferedImage image = null;
	private boolean gameOver;
	private HealthBar hBar;
	private ManaBar mBar;
	
	public static void main(String[] args) 
	{
		JFrame frame = new JFrame ("Cavern Conjurer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Driver panel = new Driver();
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask(){public void run(){panel.tick();panel.repaint();
		}}, 0, 1000/60);//handles tick and repainting the jframe.
		 
		
	}
	public Driver ()
	{
		this.setPreferredSize(new Dimension(width, height));
		setFocusable(true);
		requestFocus();
		addKeyListener(this);
		addMouseMotionListener(this);
		addMouseListener(this);
		normWeapon = new NormalWeapon("Sword", 5, 10, 32);
		creatures = new ArrayList<Monster>();
		player = new Player(100,4,width/2,height/2);
		magic = new ArrayList<Projectile>();
		
		try {
			image = ImageIO.read(new File("res/GroundTile.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		hBar = new HealthBar();
		mBar = new ManaBar();
		gameOver = false;

	}

	//update
	public void tick ()
	{
//		if(monsterSpawnCounter == monsterSpawnRate)
//		{
//			int spawnpos = (int) (Math.random() * 1080);//Monster random spawning
//			creatures.add(new Monster(100,2,0,spawnpos,0));
//			int side = (int)(Math.random() * 4);
//			if(side == 0)
//				creatures.add(new Monster(100,2,0,spawnpos,0));
//			else if(side == 1)
//				creatures.add(new Monster(100,2,0,0,spawnpos));
//			else if(side == 2)
//				creatures.add(new Monster(100,2,0,1080,spawnpos));
//			else
//				creatures.add(new Monster(100,2,0,spawnpos,810));
//			monsterSpawnCounter = 0;
//		}
//		else
//			monsterSpawnCounter++;
		if(!gameOver)
		{
			if(creatures.size()<=MONSTERSPAWNCAP)
				if(monsterSpawnCounter == monsterSpawnRate)
				{
					int spawnpos = (int) (Math.random() * 1080);//Monster random spawning
					creatures.add(new Monster(100,2,0,spawnpos,0));
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
			//check collision between projectile and entity
			int count = 0;
			loop:
			for(int i = 0; i < magic.size(); i++)
			{
				for(int j = 0; j < creatures.size(); j++)
				{
					if(magic.size() == i)
						break loop;
					if(magic.get(i).getBounds().intersects(creatures.get(j).getBounds()))
					{
						magic.remove(i);
						creatures.remove(j);
						j--;
						count++;
					}
				}
				i -= count;
			}
			for(int i = 0; i < creatures.size();i++)//updates all entities in the array.
			{
				if(!creatures.get(i).getBounds().intersects(player.getBounds()))
					creatures.get(i).update();
				else
				{
					player.decHealth(.5);
					hBar.setHealth(player.getHealth());
				}
			}
			player.update();
			//update projectile
			if(!magic.isEmpty())
				for(int i = 0; i < magic.size(); i++)
					magic.get(i).update();
			//checks to see if projectiles are within monster hitbox
			
			//delete projectile out of frame
			for(int i = 0; i < magic.size(); i++)
			{
				if(magic.get(i).getX() > this.width || magic.get(i).getX() + magic.get(i).getWidth() < 0 
						|| magic.get(i).getY() > this.height || magic.get(i).getY() + magic.get(i).getHeight() < 0)
						magic.remove(i);
			}
			if(player.getHealth() <= 0)
				gameOver = true;
		}
		//delete projectile out of frame
		for(int i = 0; i < magic.size(); i++)
		{
			if(magic.get(i).getX() > this.width || magic.get(i).getX() + magic.get(i).getWidth() < 0 
					|| magic.get(i).getY() > this.height || magic.get(i).getY() + magic.get(i).getHeight() < 0)
					magic.remove(i);
		}
	}
	
	
	
	//render
	public void paintComponent (Graphics g)
	{
		super.paintComponent(g);
		for(int i = 0; i < 6;i++)
			for(int j = 0; j < 5 ;j++)
			{	
				g.drawImage(image, i*200, j*200,200,200, null);
			}
		try {
			image = ImageIO.read(new File("res/GroundTile.png"));
			g.drawString("Mouse Pos: " + mouseX + ", " + mouseY, 500, 30);
			g.drawString("Player Pos: " + player.getX() + ", " + player.getY(), 500, 40);
			g.drawString("Projectile Left: " + player.getMana(), 500, 50);
			g.drawString("Health: " + player.getHealth(), 500, 60);
			if(gameOver)
			{
				Font gameOverFont = new Font(Font.DIALOG, Font.BOLD, 100);
				g.setFont(gameOverFont);
				g.drawString("GG m8", this.width/2, this.height/2);
				return; //fix to end loop
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		g.drawImage(image, 0, 0,1080,810, null);
		player.draw((Graphics2D)g);
		for(int i = 0; i < creatures.size();i++)
		{
			creatures.get(i).draw((Graphics2D)g);
		}
		if(!magic.isEmpty())
		{
			for(int i = 0; i < magic.size(); i++)
				magic.get(i).draw((Graphics2D)g);
		}
		hBar.draw((Graphics2D)g);
		mBar.draw((Graphics2D)g);
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
			if(player.hasMana())
			{
				magic.add(new FireBall(player.getDirection(), player.getX(), player.getY(),0));
				player.decMana(1);
				mBar.setMana(player.getMana());
			}
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
	
	public void mouseClicked(MouseEvent e)
	{
	}
	
	public void mousePressed(MouseEvent e) 
	{
		int mouseCode = e.getButton();
		if(mouseCode == MouseEvent.BUTTON1)
		{
			normWeapon.attack();
		}
	}
	
	public void mouseReleased(MouseEvent e)
	{
	}
	
	public void mouseEntered(MouseEvent e)
	{
	}
	
	public void mouseExited(MouseEvent e)
	{
	}
	
}
