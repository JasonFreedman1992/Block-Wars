import java.util.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;

public class State
{
	public static Ship ship = new Ship();
	static ArrayList<Lazer> lazerList = new ArrayList<Lazer>();
	static ArrayList<Asteroid> asteroidList = new ArrayList<Asteroid>();
	static int asteroidSpeed = 2;
	static int lazerSpeed = 10;
	public static int width;
	public static int height;
	public static boolean up;
	public static boolean right;
	public static boolean left;
	public static boolean down;
    public static boolean upRelease;
    public static boolean rightRelease;
    public static boolean leftRelease;
    public static boolean downRelease;
	public static boolean lazerShoot;
    public static boolean spawnAsteroid;
    public static int shipSpeed = 5;
    public int shipRelease = 5;
    public double imageAngleRad = 0;
    public double dX;
    public double dY;
    public double mouseX;
    public double mouseY;
    public Random r = new Random();

    public AffineTransform trans = new AffineTransform();

	public void update()
	{
        //System.out.println(dX);
        //System.out.println(dY);
        dX = mouseX - ship.x;
        dY = mouseY - ship.y;
        imageAngleRad = Math.atan2(dY, dX);
        System.out.println(imageAngleRad);
		if(up)
		{
			ship.y = ship.y - shipSpeed;
		}
		if(right)
		{
			ship.x = ship.x + shipSpeed;
		}
		if(left)
		{
			ship.x = ship.x - shipSpeed;
		}
		if(down)
		{
			ship.y = ship.y + shipSpeed;
		}
        if(upRelease)
        {
            while(shipRelease > 0)
            {
                ship.y = ship.y - shipRelease;
                shipRelease = shipRelease - 1;
            }
        }
        if(rightRelease)
        {

        }
        if(leftRelease)
        {

        }
        if(downRelease)
        {

        }
		if(lazerShoot)
		{
            double angle = imageAngleRad/Math.PI;
            double vx = 0;
            double vy = 0;
            double spawnx = 0;
            double spawny = 0;
            if(imageAngleRad < 0)
            {
                vx = 20 - (-40 * angle);
                if(angle < 0 && angle >= -.5)
                {
                    vy = 0 + (40 * angle);
                }
                else if(angle < -.5 && angle >= - 1)
                {
                    vy = 40 * (-1 - angle);
                }
            }
            else if(imageAngleRad > 0)
            {
                vx = 20 + (-40 * angle);
                if(angle > 0 && angle <= .5)
                {
                    vy = 0 + (40 * angle);
                }
                else if(angle > .5 && angle < 1)
                {
                    vy = -40 * (-1 + angle);
                }
            }
            //
            // randomly shoot out back and front
            //
            else if(imageAngleRad == 0)
            {
                vy = 0;
                if(mouseX > ship.x)
                {
                   vx = 20;
                }
                else
                {
                    vx = -20;
                }
            }
			lazerList.add(new Lazer(ship.x + 50, ship.y + 50, imageAngleRad, vx, vy));
		}
        if(spawnAsteroid)
        {
            asteroidList.add(new Asteroid(r.nextInt(width),r.nextInt(height), randomString(r.nextInt(4 + 1))));
        }
        if(!lazerList.isEmpty())
        {
            for(int i = 0; i < lazerList.size(); i++)
            {
                lazerList.get(i).x += lazerList.get(i).vx;
                lazerList.get(i).y += lazerList.get(i).vy;
                //lazerList.get(i).y += (int) lazerList.get(i).vy;
                
                for(int j = 0; j < asteroidList.size(); j++)
                {
                    if(lazerList.get(i).x + 87 > asteroidList.get(j).x && lazerList.get(i).x + 87 < asteroidList.get(j).x + 121 && lazerList.get(i).y + 28 > asteroidList.get(j).y && lazerList.get(i).y + 28 < asteroidList.get(j).y + 121)
                    {
                        lazerList.remove(i);
                        asteroidList.remove(j);
                        break;
                    }
                }
            }
        }
        if(!asteroidList.isEmpty())
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