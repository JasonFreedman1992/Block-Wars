import javax.swing.*;
import javax.swing.Timer;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.MouseInfo;
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
			y = y - 35;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			x = x + 35;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			x = x - 35;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			y = y + 35;
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{ 
			System.out.println(lazerList.size());
			System.out.println(asteroidList.size());
			lazerList.add(new Lazer(x+175, y+30));
		}
		if(e.getKeyCode() == KeyEvent.VK_NUMPAD4)
		{
			System.out.println("ASTEROID");
			asteroidList.add(new Asteroid(r.nextInt((width/2))+(width/2),r.nextInt(height - 125), new StringBuilder(randomString(r.nextInt(4)+1))));
		}
	}
	//randomString(r.nextInt(4 + 1)
	StringBuilder randomString(int p_int)
	{
		if(p_int == 1)
		{
			return new StringBuilder("ur");
		}
		else if(p_int == 2)
		{
			return new StringBuilder("ul");
		}
		else if(p_int == 3)
		{
			return new StringBuilder("dr");
		}
		else if(p_int == 4)
		{
			return new StringBuilder("dl");
		}
		else
		{
			return new StringBuilder("ur");
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
	
	int asteroidSpeed = 2;
	int lazerSpeed = 10;
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
    // asteroid = 121 121
    // laser = 87 28
    // ship = 188 x 91
    public void initPanel()
    {
    	panel = new JPanel()
    	{
    		@Override
    		protected void paintComponent(Graphics g)
    		{
    			if(x < width && y < height && x >= 0 && y >= 0)
    			{	
    				g.drawImage(bg, 0, 0, null);
    				//x = MouseInfo.getPointerInfo().getLocation().x;
    				//y = MouseInfo.getPointerInfo().getLocation().y;
    				g.drawImage(ship, x, y, null);
    				if(lazerList.size() != 0)
    				{
    					for(int i = 0; i < lazerList.size(); i++)
    					{
    						lazerList.get(i).x = lazerList.get(i).x + lazerSpeed;
    						g.drawImage(lazer, lazerList.get(i).x, lazerList.get(i).y, null);
    						for(int j = 0; j < asteroidList.size(); j++)
    						{
    							if(lazerList.get(i).x + 87 > asteroidList.get(j).x && lazerList.get(i).x + 87 < asteroidList.get(j).x + 121 && lazerList.get(i).y + 28 > asteroidList.get(j).y && lazerList.get(i).y + 28 < asteroidList.get(j).y + 121)
    							{
    								lazerList.remove(i);
    								asteroidList.remove(j);
    								break;
    							}
    						}
    						if(i == lazerList.size())
    						{

    						}
    						else if(i < lazerList.size())
    						{
	    						if(lazerList.get(i).x > width - 50)
	    						{
	    							lazerList.remove(i);
	    						}
    						}

    					}
    				}
    				if(asteroidList.size() != 0)
    				{
    					for(int i = 0; i < asteroidList.size(); i++)
    					{
    						if(asteroidList.get(i).direction.toString().equals(new StringBuilder("ur").toString()))
    						{
    							asteroidList.get(i).x = asteroidList.get(i).x + asteroidSpeed;
    							asteroidList.get(i).y = asteroidList.get(i).y - asteroidSpeed;
    						}
    						else if(asteroidList.get(i).direction.toString().equals(new StringBuilder("ul").toString()))
    						{
    							asteroidList.get(i).x = asteroidList.get(i).x - asteroidSpeed;
    							asteroidList.get(i).y = asteroidList.get(i).y - asteroidSpeed;
    						}
    						else if(asteroidList.get(i).direction.toString().equals(new StringBuilder("dr").toString()))
    						{
    							asteroidList.get(i).x = asteroidList.get(i).x + asteroidSpeed;
    							asteroidList.get(i).y = asteroidList.get(i).y + asteroidSpeed;
    						}
    						else if(asteroidList.get(i).direction.toString().equals(new StringBuilder("dl").toString()))
    						{
    							asteroidList.get(i).x = asteroidList.get(i).x - asteroidSpeed;
    							asteroidList.get(i).y = asteroidList.get(i).y + asteroidSpeed;
    						}
    						g.drawImage(asteroid, asteroidList.get(i).x, asteroidList.get(i).y, null);
    						if(asteroidList.get(i).x > width - 122)
    						{
    							asteroidList.get(i).direction.setCharAt(1, 'l');
    						}
    						if(asteroidList.get(i).x < 0)
    						{
    							asteroidList.get(i).direction.setCharAt(1, 'r');
    						}
    						if(asteroidList.get(i).y > height - 122)
    						{
    							asteroidList.get(i).direction.setCharAt(0, 'u');
    						}
    						if(asteroidList.get(i).y < 0)
    						{
    							asteroidList.get(i).direction.setCharAt(0, 'd');
    						}
    					}
    				}
    				
    			}
    		}
    	};
    }
    class Lazer
    {
    	int x;
    	int y;
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
    	StringBuilder direction;
    	public Asteroid(int p_x, int p_y, StringBuilder p_direction)
    	{
    		x = p_x;
    		y = p_y;
    		direction = p_direction;
    	}
    }
}