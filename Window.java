import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.io.*;
import javax.imageio.ImageIO;

public class Window extends JFrame implements MouseMotionListener
{
    State state = new State();
    Controller control = new Controller();
    Image ship;
    Image asteroid;
    JPanel panel;
    Image lazer;
    Image bg;
    Graphics g;

    Timer timer = new Timer(10, new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
            //panel.setLocation(0, 0);
            panel.setSize(state.width, state.height);
            state.update();
            panel.repaint();
        }
    });

    public void init(int p_width, int p_height)
    {
        setTitle("Block Wars");
        setSize(p_width, p_height);
        state.width = p_width;
        state.height = p_height;
        state.map_width = p_width * 10;
        state.map_height = p_height * 10;
        state.ship.x = p_width/2;
        state.ship.y = p_height/2;
        state.camera.x = 0;
        state.camera.y = 0;
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

        initPanel();
        add(panel);
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
                g.drawImage(bg, 0, 0, null);
                g2 = (Graphics2D)g;
                AffineTransform oldAT = g2.getTransform();

                g2.translate(state.ship.x - state.camera.x, state.ship.y - state.camera.y);
                g2.rotate(state.imageAngleRad);
                g2.translate(-state.ship.x + state.camera.x, -state.ship.y + state.camera.y);
                g.drawImage(ship, state.ship.x - state.camera.x - 51, state.ship.y - state.camera.y - 24, null);
                g2.setTransform(oldAT);
                g.setColor(new Color(0f, 0f, 0f, .8f));
                g.fillRect(50, 700, state.width/6, state.height/6);

                g.setColor(Color.BLUE);
                g.fillRect(50 + state.ship.x/60, 700+state.ship.y/60, 4, 4);

                g.setColor(Color.WHITE);
                g2.setStroke(new BasicStroke(5));
                // top border
                g.drawLine(0-state.camera.x, 0-state.camera.y, state.map_width-state.camera.x, 0-state.camera.y);
                // left border
                g.drawLine(0-state.camera.x, 0-state.camera.y, 0-state.camera.x, state.map_height-state.camera.y);
                // bottom border
                g.drawLine(0-state.camera.x, 9000-state.camera.y, 12000-state.camera.x, 9000-state.camera.y);
                // top border
                g.drawLine(12000-state.camera.x, 0-state.camera.y, 12000-state.camera.x, 9000-state.camera.y);

                if(!state.lazerList.isEmpty())
                {
                    for(int i = 0; i < state.lazerList.size(); i++)
                    {
                        if(state.lazerList.get(i).x - state.camera.x > 0 && state.lazerList.get(i).x - state.camera.x < state.width &&
                        state.lazerList.get(i).y - state.camera.y > 0 && state.lazerList.get(i).y - state.camera.y < state.height){
                            if(i < state.lazerList.size()){
                                g2.translate(state.lazerList.get(i).x - state.camera.x, state.lazerList.get(i).y - state.camera.y);
                                g2.rotate(state.lazerList.get(i).rotate);
                                g2.translate(-state.lazerList.get(i).x + state.camera.x, -state.lazerList.get(i).y + state.camera.y);
                                g.drawImage(lazer, state.lazerList.get(i).x - state.camera.x, state.lazerList.get(i).y - state.camera.y, null);
                                g2.setTransform(oldAT);
                            }
                        }
                        g.setColor(Color.RED);
                        g.fillRect(50 + state.lazerList.get(i).x/60, 700+state.lazerList.get(i).y/60, 1, 1);
                    }
                }
                if(!state.asteroidList.isEmpty())
                {
                    for(int i = 0; i < state.asteroidList.size(); i++)
                    {
                        if(state.asteroidList.get(i).x - state.camera.x + 61 > 0 && state.asteroidList.get(i).x - state.camera.x < state.width &&
                        state.asteroidList.get(i).y - state.camera.y + 61 > 0 && state.asteroidList.get(i).y - state.camera.y < state.height){
                            g.drawImage(asteroid, state.asteroidList.get(i).x - state.camera.x, state.asteroidList.get(i).y - state.camera.y, null);
                        }
                        g.setColor(Color.LIGHT_GRAY);
                        g.fillRect(50 + state.asteroidList.get(i).x/60, 700+state.asteroidList.get(i).y/60, 2, 2);
                    }
                }
                
            }
        };
    }

    public void mouseMoved(MouseEvent e)
    {
        state.mouseX = e.getX();
        state.mouseY = e.getY();
    }

    public void mouseDragged(MouseEvent e)
    {

    }
}