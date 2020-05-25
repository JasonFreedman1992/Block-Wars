package com.blockwars;

import java.util.*;

import com.blockwars.Objects.Asteroid;
import com.blockwars.Objects.Lazer;
import com.blockwars.Objects.Ship;
import com.blockwars.Objects.Oracle;

import java.awt.event.*;
import java.awt.geom.AffineTransform;

public class State
{
	public static Ship ship = new Ship();
	static ArrayList<Lazer> lazerList = new ArrayList<Lazer>();
    static ArrayList<Asteroid> asteroidList = new ArrayList<Asteroid>();
    static ArrayList<Oracle> oracleList = new ArrayList<Oracle>();
	static int asteroidSpeed = 2;
	public static int width;
    public static int height;
    
    public static boolean selectingUnits;
    public static Oracle selectedOracle;

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
    public static boolean spawnOracle;

    public static int shipSpeed = 25;
    public int shipRelease = 5;
    public double imageAngleRad = 0;
    public double dX;
    public double dY;
    public double mouseX;
    public double mouseY;
    public int releaser = 100;
    public Random r = new Random();
    public AffineTransform trans = new AffineTransform();

    public static Camera camera = new Camera();

    public static int map_height; // 9000
    public static int map_width; // 12000

	public void update()
	{
        // System.out.println(ship.x);
        // System.out.println(ship.y);

        // dX = mouseX - ship.x;
        // dY = mouseY - ship.y;

        dX = mouseX - width/2;
        dY = mouseY - height/2;

        imageAngleRad = Math.atan2(dY, dX);

        // current angle of ship to cursor in radians
        //System.out.println(imageAngleRad);
        
		if(up)
		{
            if(camera.y+height/2 >= 0){
                camera.y = camera.y - shipSpeed;
                ship.y = ship.y - shipSpeed;
            }
		}
		if(right)
		{
            if(camera.x+width/2 <= map_width){
                ship.x = ship.x + shipSpeed;
                camera.x = camera.x + shipSpeed;
            }
		}
		if(left)
		{
            if(camera.x+width/2 >= 0){
                ship.x = ship.x - shipSpeed;
                camera.x = camera.x - shipSpeed;
            }
		}
		if(down)
		{
            if(camera.y+height/2 <= map_height){
                ship.y = ship.y + shipSpeed;
                camera.y = camera.y + shipSpeed;
            }
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
			lazerList.add(new Lazer(ship.x, ship.y, imageAngleRad, vx, vy));
        }
        if(spawnOracle){
            oracleList.add(new Oracle(r.nextInt(map_width), r.nextInt(map_height)));
        }
        if(spawnAsteroid)
        {
            // spawn asteroid with random position on map and random direction
            asteroidList.add(new Asteroid(r.nextInt(map_width),r.nextInt(map_height), randomString(r.nextInt(4 + 1))));
        }
        if(!lazerList.isEmpty())
        {
            // if laser is intersecting with asteroid, remove both laser and asteroid
            for(int i = 0; i < lazerList.size(); i++)
            {
                lazerList.get(i).x += lazerList.get(i).vx;
                lazerList.get(i).y += lazerList.get(i).vy;
                
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
        if(!lazerList.isEmpty()){
            // if laser hits the boundaries of the map
            for(int i = 0; i < lazerList.size(); i++){
                if(lazerList.get(i).x < 0 || lazerList.get(i).y < 0 || lazerList.get(i).y > map_height || lazerList.get(i).x > map_width){
                    lazerList.remove(i);
                }
            }
        }
        if(!asteroidList.isEmpty())
        {
            for(int i = 0; i < asteroidList.size(); i++)
            {
                // asteroid movement
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
                // if asteroid hits boundary, change direction
                if(asteroidList.get(i).x > map_width - 61)
                {
                    asteroidList.get(i).direction.setCharAt(1, 'l');
                }
                if(asteroidList.get(i).x < 0)
                {
                    asteroidList.get(i).direction.setCharAt(1, 'r');
                }
                if(asteroidList.get(i).y > map_height - 61)
                {
                    asteroidList.get(i).direction.setCharAt(0, 'u');
                }
                if(asteroidList.get(i).y < 0)
                {
                    asteroidList.get(i).direction.setCharAt(0, 'd');
                }
            }
        }
        if(!oracleList.isEmpty())
        {   
            for(int i = 0; i < oracleList.size(); i++){

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