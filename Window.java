import javax.swing.*;
import javax.swing.Timer;
import javax.swing.JPanel;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.*;

public class Window extends JFrame implements KeyListener
{
	Random r = new Random();
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			System.out.println("up");
			y = y - 35;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			System.out.println("right");
			x = x + 35;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			x = x - 35;
			System.out.println("left");
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			y = y + 35;
			System.out.println("down");
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{ 
			System.out.println("LAZER");
			System.out.println(lazerList.size());
			lazerList.add(new Lazer(x+188, y+45));
		}
		if(e.getKeyCode() == KeyEvent.VK_NUMPAD4)
		{
			System.out.println("ASTEROID");
			asteroidList.add(new Asteroid(r.nextInt(750)+750,r.nextInt(1000)));
		}
	}


	public void keyReleased(KeyEvent e)
	{

	}
	public void keyTyped(KeyEvent e)
	{

	}

	ArrayList<Lazer> lazerList = new ArrayList<Lazer>();
	ArrayList<Asteroid> asteroidList = new ArrayList<Asteroid>();
	
	int speed = 10;
	Image ship;
	Image asteroid;
	JPanel panel;
	Image lazer;
	Image bg;
	Graphics g;
	int x;
	int y;
	int height;
	int width;
	Timer timer = new Timer(10, new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
        	initPanel();
        	add(panel);
        	panel.setLocation(0, 0);
        	panel.setSize(width, height);
		}
	});
	public void init(int p_width, int p_height)
    {
        setTitle("Freeza Chess");
        setSize(p_width, p_height);
        width = p_width;
        height = p_height;
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
        addKeyListener(this);
        loadImages();
        timer.start();
    }

    void loadImages()
    {
	    try
		{
			ship = ImageIO.read(new File("redship.png"));
			bg = ImageIO.read(new File("space.jpg"));
			lazer = ImageIO.read(new File("lazer.png"));
			asteroid = ImageIO.read(new File("asteroid.png"));
		}
		catch(IOException ex)
		{

		}
    }

    public void initPanel()
    {
    	panel = new JPanel()
    	{
    		@Override
    		protected void paintComponent(Graphics g)
    		{
    			if(x < 1500 && y < 1125 && x >= 0 && y >= 0)
    			{	
    				g.drawImage(bg, 0, 0, null);
    				g.drawImage(ship, x, y, null);
    				if(lazerList.size() != 0)
    				{
    					for(int i = 0; i < lazerList.size(); i++)
    					{
    						lazerList.get(i).x = lazerList.get(i).x + speed;
    						g.drawImage(lazerList.get(i).img, lazerList.get(i).x, lazerList.get(i).y, null);
    						if(lazerList.get(i).x > 1500)
    						{
    							lazerList.remove(i);
    						}

    					}
    				}
    				if(asteroidList.size() != 0)
    				{
    					for(int i = 0; i < asteroidList.size(); i++)
    					{
    						g.drawImage(asteroidList.get(i).img, asteroidList.get(i).x, asteroidList.get(i).y, null);
    						if(asteroidList.get(i).x > 1500)
    						{

    						}
    					}
    				}
    				
    			}
    		}

    	};
    }
    void initObjects()
    {

    }
    class Lazer
    {
    	int x;
    	int y;
    	Image img = lazer;
    	public Lazer(int p_x, int p_y)
    	{
    		x = p_x;
    		y = p_y;
    	}
    }
    class Asteroid
    {
    	int x;
    	int y;
    	Image img = asteroid;
    	String direction;
    	int speedUp;
    	int speedRight;
    	public Asteroid(int p_x, int p_y)
    	{
    		x = p_x;
    		y = p_y;
    	}
    }
}