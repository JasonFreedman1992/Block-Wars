import java.awt.geom.AffineTransform;

public class Lazer
{
	public int x;
	public int y;
	public double vx;
	public double vy;
	public double rotate;
	public int fpx;
	public int fpy;
	public Lazer(int p_x, int p_y, double p_rotate, double p_vx, double p_vy)
	{
		x = p_x;
		y = p_y;
		rotate = p_rotate;
		vx = p_vx;
		vy = p_vy;
	}
}