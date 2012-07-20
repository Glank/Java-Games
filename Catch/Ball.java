import java.awt.*;

public class Ball
{
	public static final Color GOOD_COLOR = Color.GREEN;
	public static final Color BAD_COLOR = Color.RED;
	public static final int RADIUS = 5;
	public static final int SPEED = 2;

	private boolean good;
	private int x,y;

	public Ball(int x, int y)
	{
		this.x = x;
		this.y = y;
		good = true;
	}

	public Ball(int x, int y, boolean good)
	{
		this.x = x;
		this.y = y;
		this.good = good;
	}

	public Ball moveTo(int x, int y)
	{
		return new Ball(x,y,good);
	}

	public Ball move(int dx, int dy)
	{
		return new Ball(x+dx, y+dy, good);
	}

	public Ball moveDown()
	{
		return move(0, SPEED);
	}

	public Point getLocation()
	{
		return new Point(x,y);
	}

	public Rectangle getBounds()
	{
		return new Rectangle(x-RADIUS, y-RADIUS, RADIUS*2, RADIUS*2);
	}

	public boolean isGood()
	{
		return good;
	}

	public void draw(Graphics g)
	{
		g.setColor(good?GOOD_COLOR:BAD_COLOR);
		g.fillOval(x-RADIUS, y-RADIUS, RADIUS*2, RADIUS*2);
	}
}