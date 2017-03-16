import javax.swing.*;
import javax.swing.Timer;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.Point;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.*;

public class Window extends JFrame implements MouseMotionListener
{
    State state = new State();
    Controller control = new Controller();
	Random r = new Random();;
    Image ship;
    Image asteroid;
    JPanel panel;
    Image lazer;
    Image bg;
    Graphics g;

    public double imageAngleRad = 0;

    Timer timer = new Timer(10, new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
            initPanel();
            add(panel);
            panel.setLocation(0, 0);
            panel.setSize(state.width, state.height);
            state.update();
        }
    });

    public void init(int p_width, int p_height)
    {
        setTitle("Block Wars");
        setSize(p_width, p_height);
        state.width = p_width;
        state.height = p_height;
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
        addKeyListener(control);
        addMouseMotionListener(this);
        loadImages();
        try
        {
            setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("mycursor1.png").getImage(),new Point(0,0),"custom cursor"));
        }
        catch(Exception e)
        {

        }
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
    Graphics2D g2;
    // asteroid = 121 121
    // laser = 87 28
    // ship = 188 x 91
    void initPanel()
    {
        panel = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g)
            {
                //if(state.ship.x < state.width && state.ship.y < state.height && state.ship.x >= 0 && state.ship.y >= 0)
                //{   
                    g.drawImage(bg, 0, 0, null);
                    g2 = (Graphics2D)g;
                    g2.translate(state.ship.x, state.ship.y);
                    g2.rotate(imageAngleRad);
                    g2.translate(-state.ship.x, -state.ship.y);
                    g2.drawImage(ship, state.ship.x - 95, state.ship.y - 45, null);

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
                //}
            }
        };
    }

    public void mouseMoved(MouseEvent e)
    {
        double dX = e.getX() - state.ship.x;
        double dY = e.getY() - state.ship.y;
        imageAngleRad = Math.atan2(dY, dX);
        double degree = Math.toDegrees(imageAngleRad);

        System.out.println(imageAngleRad);
    }

    public void mouseDragged(MouseEvent e)
    {

    }
}