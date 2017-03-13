import java.util.*;
import java.awt.event.*;

public class State
{
	public static Ship ship = new Ship();
	static ArrayList<Lazer> lazerList = new ArrayList<Lazer>();
	static ArrayList<Asteroid> asteroidList = new ArrayList<Asteroid>();
	static int asteroidSpeed = 2;
	static int lazerSpeed = 10;

	public static boolean up;
	public static boolean right;
	public static boolean left;
	public static boolean down;
	public static boolean lazerShoot;

	public void update()
	{
		if(up)
		{
			ship.y = ship.y - 5;
		}
		if(right)
		{
			ship.x = ship.x + 5;
		}
		if(left)
		{
			ship.x = ship.x - 5;
		}
		if(down)
		{
			ship.y = ship.y + 5;
		}
		if(lazerShoot)
		{
			lazerList.add(new Lazer(ship.x+175, ship.y+30));
		}
	}
}