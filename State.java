import java.util.*;

public class State
{
	public static Ship ship = new Ship();
	public static Controller control = new Controller();
	static ArrayList<Lazer> lazerList = new ArrayList<Lazer>();
	static ArrayList<Asteroid> asteroidList = new ArrayList<Asteroid>();
	static int asteroidSpeed = 2;
	static int lazerSpeed = 10;
	public void update()
	{
		if(control.up)
		{
			if(control.right)
			{

			}
			else if(control.left)
			{

			}
		}
		if(control.down)
		{
			if(control.right)
			{

			}
			else if(control.left)
			{

			}
		}

	}
}