import javax.swing.*;
import javax.swing.Timer;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.*;

public class Window extends JFrame
{
    State state = new State();
    Controller control = new Controller();
	Random r = new Random();
    int width;
    int height;
    Image ship;
    Image asteroid;
    JPanel panel;
    Image lazer;
    Image bg;
    Graphics g;

    public static Timer timer = new Timer(10, new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
            initPanel();
            add(panel);
            panel.setLocation(0, 0);
            panel.setSize(width, height);
            state.update();
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
        addKeyListener(control);
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
    public static void initPanel()
    {
        panel = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g)
            {
                if(state.ship.x < width && state.ship.y < height && state.ship.x >= 0 && state.ship.y >= 0)
                {   
                    g.drawImage(bg, 0, 0, null);
                    g.drawImage(ship, state.ship.x, state.ship.y, null);
                    if(!state.lazerList.isEmpty())
                    {
                        for(int i = 0; i < state.lazerList.size(); i++)
                        {
                            g.drawImage(lazer, state.lazerList.get(i).x, state.lazerList.get(i).y, null);
                        }
                    }
                    if(!state.asteroidList.isEmpty())
                    {
                        for(int i = 0; i < state.asteroidList.size(); i++)
                        {
                            g.drawImage(asteroid, state.asteroidList.get(i).x, state.asteroidList.get(i).y, null);
                        }
                    }
                    if(!state.lazerList.isEmpty())
                    {
                        for(int i = 0; i < state.lazerList.size(); i++)
                        {
                            state.lazerList.get(i).x = state.lazerList.get(i).x + state.lazerSpeed;
                            
                            for(int j = 0; j < state.asteroidList.size(); j++)
                            {
                                if(state.lazerList.get(i).x + 87 > state.asteroidList.get(j).x && state.lazerList.get(i).x + 87 < state.asteroidList.get(j).x + 121 && state.lazerList.get(i).y + 28 > state.asteroidList.get(j).y && state.lazerList.get(i).y + 28 < state.asteroidList.get(j).y + 121)
                                {
                                    state.lazerList.remove(i);
                                    state.asteroidList.remove(j);
                                    break;
                                }
                            }
                            if(i < state.lazerList.size())
                            {
                                if(state.lazerList.get(i).x > width - 50)
                                {
                                    state.lazerList.remove(i);
                                }
                            }
                        }
                    }
                    if(!state.asteroidList.isEmpty())
                    {
                        for(int i = 0; i < state.asteroidList.size(); i++)
                        {
                            if(state.asteroidList.get(i).direction.toString().equals(new StringBuilder("ur").toString()))
                            {
                                state.asteroidList.get(i).x = state.asteroidList.get(i).x + state.asteroidSpeed;
                                state.asteroidList.get(i).y = state.asteroidList.get(i).y - state.asteroidSpeed;
                            }
                            else if(state.asteroidList.get(i).direction.toString().equals(new StringBuilder("ul").toString()))
                            {
                                state.asteroidList.get(i).x = state.asteroidList.get(i).x - state.asteroidSpeed;
                                state.asteroidList.get(i).y = state.asteroidList.get(i).y - state.asteroidSpeed;
                            }
                            else if(state.asteroidList.get(i).direction.toString().equals(new StringBuilder("dr").toString()))
                            {
                                state.asteroidList.get(i).x = state.asteroidList.get(i).x + state.asteroidSpeed;
                                state.asteroidList.get(i).y = state.asteroidList.get(i).y + state.asteroidSpeed;
                            }
                            else if(state.asteroidList.get(i).direction.toString().equals(new StringBuilder("dl").toString()))
                            {
                                state.asteroidList.get(i).x = state.asteroidList.get(i).x - state.asteroidSpeed;
                                state.asteroidList.get(i).y = state.asteroidList.get(i).y + state.asteroidSpeed;
                            }
                            if(state.asteroidList.get(i).x > width - 122)
                            {
                                state.asteroidList.get(i).direction.setCharAt(1, 'l');
                            }
                            if(state.asteroidList.get(i).x < 0)
                            {
                                state.asteroidList.get(i).direction.setCharAt(1, 'r');
                            }
                            if(state.asteroidList.get(i).y > height - 122)
                            {
                                state.asteroidList.get(i).direction.setCharAt(0, 'u');
                            }
                            if(state.asteroidList.get(i).y < 0)
                            {
                                state.asteroidList.get(i).direction.setCharAt(0, 'd');
                            }
                        }
                    }
                }
            }
        };
    }

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

}