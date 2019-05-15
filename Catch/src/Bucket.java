import java.awt.*;

public class Bucket
{
	public static final int WIDTH = 20;
	public static final int HEIGHT = 18;
	public static final int SPEED = 5;

	private int x,y;

	//Getter method for X
	public int getX() {
		return x;
	}

	//Setter method for X
	public void setX(int x) {
		this.x = x;
	}

	//Getter method for Y
	public int getY() {
		return y;
	}

	//Setter method for Y
	public void setY(int y) {
		this.y = y;
	}

	public Bucket(int x, int y)
	{
		setX(x);
		setY(y);
	}

	public Bucket moveTo(int x, int y)
	{
		return new Bucket(x,y);
	}

	public Bucket move(int dx, int dy)
	{
		return new Bucket(getX()+dx, getY()+dy);
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
		return new Point(getX(),getY());
	}

	public Rectangle getBounds()
	{
		return new Rectangle(getX()-WIDTH/2, getY()-HEIGHT/2, WIDTH, HEIGHT);
	}

	public boolean contains(Ball b)
	{
		return getBounds().contains(b.getBounds());
	}

	public void draw(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(getX()-WIDTH/2, getY()-HEIGHT/2, WIDTH, HEIGHT);
	}
}