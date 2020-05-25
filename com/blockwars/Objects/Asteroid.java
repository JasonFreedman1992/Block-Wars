package com.blockwars.Objects;
import java.lang.StringBuilder;

public class Asteroid
{
	public int x;
	public int y;
	public StringBuilder direction;
	public Asteroid(int p_x, int p_y, StringBuilder p_direction)
	{
		x = p_x;
		y = p_y;
		direction = p_direction;
	}
}