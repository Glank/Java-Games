import java.awt.*;

public class Bucket
{
	public static final int WIDTH = 20;
	public static final int HEIGHT = 18;
	public static final int SPEED = 5;

	private int x,y;

	public Bucket(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public Bucket moveTo(int x, int y)
	{
		return new Bucket(x,y);
	}

	public Bucket move(int dx, int dy)
	{
		return new Bucket(x+dx, y+dy);
	}

	public Bucket moveLeft()
	{
		return move(-SPEED, 0);
	}

	public Bucket moveRight()
	{
		return move(SPEED, 0);
	}

	public Point getLocation()
	{
		return new Point(x,y);
	}

	public Rectangle getBounds()
	{
		return new Rectangle(x-WIDTH/2, y-HEIGHT/2, WIDTH, HEIGHT);
	}

	public boolean contains(Ball b)
	{
		return getBounds().contains(b.getBounds());
	}

	public void draw(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(x-WIDTH/2, y-HEIGHT/2, WIDTH, HEIGHT);
	}
}