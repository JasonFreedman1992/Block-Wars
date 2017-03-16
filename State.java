import java.util.*;
import java.awt.event.*;

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
    public static int shipSpeed = 5;
    public int shipRelease = 5;

	public void update()
	{
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
			lazerList.add(new Lazer(ship.x + 75, ship.y - 12));
		}
        if(!lazerList.isEmpty())
        {
            for(int i = 0; i < lazerList.size(); i++)
            {
                lazerList.get(i).x = lazerList.get(i).x + lazerSpeed;
                
                for(int j = 0; j < asteroidList.size(); j++)
                {
                    if(lazerList.get(i).x + 87 > asteroidList.get(j).x && lazerList.get(i).x + 87 < asteroidList.get(j).x + 121 && lazerList.get(i).y + 28 > asteroidList.get(j).y && lazerList.get(i).y + 28 < asteroidList.get(j).y + 121)
                    {
                        lazerList.remove(i);
                        asteroidList.remove(j);
                        break;
                    }
                }
                if(i < lazerList.size())
                {
                    if(lazerList.get(i).x > width - 50)
                    {
                        lazerList.remove(i);
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
}