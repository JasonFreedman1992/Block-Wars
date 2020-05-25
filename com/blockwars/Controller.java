package com.blockwars;

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
            state.spawnAsteroid = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_R)
		{
			state.spawnOracle = true;
			// System.out.println("R");
            // state.spawnAsteroid = true;
		}
	}
	public void keyReleased(KeyEvent e)
	{
        if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W)
        {
            state.up = false;
            state.upRelease = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D)
        {
            state.right = false;
            state.rightRelease = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A)
        {
            state.left = false;
            state.leftRelease = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S)
        {
            state.down = false;
            state.downRelease = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_NUMPAD0)
        { 
            state.lazerShoot = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_F)
        {
            state.spawnAsteroid = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_R)
        {
			state.spawnOracle = false;
            // state.spawnAsteroid = false;
        }
	}
	public void keyTyped(KeyEvent e)
	{

	}
}