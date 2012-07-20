import java.awt.*;

public class Ball
{
	public static final int RADIUS = 10;
	public static final int SPEED = 10;
	private int x, y;
	private double dx, dy;

	public Ball(int x, int y)
	{
		this(x,y,0,0);
	}

	public Ball(int x, int y, double dx, double dy)
	{
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
	}

	public Ball moveLeft()
	{
		return new Ball(x-SPEED, y, dx, dy);
	}

	public Ball moveRight()
	{
		return new Ball(x+SPEED, y, dx, dy);
	}

	public Ball accelerate(double ax, double ay)
	{
		return new Ball(x, y, dx+ax, dy+ay);
	}

	public Ball setVelocity(double dx, double dy)
	{
		return new Ball(x, y, dx, dy);
	}

	public Ball setPosition(int x, int y)
	{
		return new Ball(x, y, 0, 0);
	}

	public Ball move(int dx, int dy)
	{
		return new Ball(x+dx, y+dy, 0, 0);
	}

	public Ball move()
	{
		return new Ball(x+(int)dx, y+(int)dy, dx, dy);
	}

	public Rectangle getBounds()
	{
		return new Rectangle(x-RADIUS, y-RADIUS, RADIUS*2, RADIUS*2);
	}

	public Point getLocation()
	{
		return new Point(x,y);
	}

	public void draw(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillOval(x-RADIUS, y-RADIUS, RADIUS*2, RADIUS*2);
	}
}