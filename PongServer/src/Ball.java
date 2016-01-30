import java.awt.*;
import java.awt.geom.*;
import java.io.Serializable;
import java.util.Scanner;

public class Ball implements Serializable
{
	public double xVec = 0, yVec= 0;
	public double x,y;
	public Ball(int xl, int yl)
	{
		x = xl;
		y = yl;
		if(Math.random() > .5)
			xVec = 1;
		else
			xVec = -1;
	}

	public Ball(String s)
	{
		Scanner sc = new Scanner(s);
		x = sc.nextInt();
		y = sc.nextInt();
	}
	public void update()
	{
		if(xVec > 10)
			xVec = 10;
		if(xVec < -10)
			xVec = -10;
		if(yVec > 10)
			yVec = 10;
		if(yVec < -10)
			yVec = -10;
		x+=xVec;
		y+=yVec;
	}

	public boolean outOfBounds(Rectangle bounds)
	{
		return !getBounds().intersects(bounds);
	}

	public void bounceY()
	{
		yVec = -yVec;
	}

	public void bounceX()
	{
		xVec = -xVec;

		if(Math.random() > .5)
		{
			yVec += (Math.random() * 5)-2;
		}

		if(Math.random() > .2)
			speedUp();
	}

	public Point2D getLocation()
	{
		return new Point2D.Double(x,y);
	}

	public void setLocation(Point2D p)
	{
		x = p.getX();
		y = p.getY();
	}

	public Rectangle getBounds()
	{
		return new Rectangle((int)(x-5+.5), (int)(y-5+.5), 10, 10);
	}

	public void draw(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillOval((int)(x-5+.5),(int)(y-5+.5), 10, 10);
	}

	public String toString()
	{
		return "" + x + " " + y;
	}

	public void speedUp()
	{
		if(xVec < 0)
			xVec -= Math.random();
		else
			xVec += Math.random();
	}
}