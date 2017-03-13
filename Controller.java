import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Controller implements KeyListener
{
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
}