import java.awt.*;
import java.io.Serializable;

public class Goal implements Serializable
{
	double radius;
	double x, y;
	boolean reached = false;

	public Goal(double radius, double x, double y)
	{
		this.x = x;
		this.y = y;
		this.radius = radius;
	}

	public void testReached(Comet c)
	{
		if(Math.pow(Math.pow(c.x-x,2) + Math.pow(c.y-y,2), .5) <= radius)
			reached = true;
	}

	public void reset()
	{
		reached = false;
	}

	public void draw(Graphics g)
	{
		if(reached)
			g.setColor(Color.GREEN);
		else
			g.setColor(Color.RED);
		g.drawOval((int)(x-radius+.5), (int)(y-radius+.5), (int)(radius*2+.5), (int)(radius*2+.5));
	}
}