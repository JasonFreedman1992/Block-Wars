package com.blockwars.Objects;

public class Oracle {
    public int x;
	public int y;
	public double vx = 10; // default to 10
	public double vy = 10;
	public double rotate;
	public int fpx;
    public int fpy;
    
    public Oracle(int p_x, int p_y, double p_rotate, double p_vx, double p_vy){
        x = p_x;
		y = p_y;
		rotate = p_rotate;
		vx = p_vx;
		vy = p_vy;

    }

    public Oracle(int p_x, int p_y){
        x = p_x;
		y = p_y;
    }

    // public Oracle(int p_x, int p_y double p_vx, double p_vy){

    // }
}