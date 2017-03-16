import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Controller implements KeyListener
{
	Random r = new Random();
	State state = new State();
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W)
		{			
			state.up = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D)
		{
			state.right = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A)
		{
            state.left = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S)
		{
			state.down = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_NUMPAD0)
		{ 
            state.lazerShoot = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_F)
		{
			state.asteroidList.add(new Asteroid(r.nextInt(state.width),r.nextInt(state.height), randomString(r.nextInt(4 + 1))));
		}
	}
	public void keyReleased(KeyEvent e)
	{
        if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W)
        {
            state.up = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D)
        {
            state.right = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A)
        {
            state.left = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S)
        {
            state.down = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_NUMPAD0)
        { 
            state.lazerShoot = false;
        }
	}
	public void keyTyped(KeyEvent e)
	{

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