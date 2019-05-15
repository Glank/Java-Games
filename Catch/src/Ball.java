import java.awt.*;

public class Ball
{
	public static final Color GOOD_COLOR = Color.GREEN;
	public static final Color BAD_COLOR = Color.RED;
	public static final int RADIUS = 5;
	public static final int SPEED = 2;

	private boolean good;
	private int x,y;

	//Getter method for x
	public int getX() {
		return x;
	}

	//Setter method for x
	public void setX(int x) {
		this.x = x;
	}

	//Getter method for y
	public int getY() {
		return y;
	}

	//Setter method for y
	public void setY(int y) {
		this.y = y;
	}

	//Setter method for Good
	public void setGood(boolean good) {
		this.good = good;
	}

	//Getter method for Good
	public boolean isGood()
	{
		return good;
	}
	
	public Ball(int x, int y)
	{
		setX(x);
		setY(y);
		setGood(true);
	}

	public Ball(int x, int y, boolean good)
	{
		setX(x);
		setY(y);
		setGood(good);
	}

	public Ball moveTo(int x, int y)
	{
		return new Ball(x,y,isGood());
	}

	public Ball move(int dx, int dy)
	{
		return new Ball(getX()+dx, getY()+dy, isGood());
	}

	public Ball moveDown()
	{
		return move(0, SPEED);
	}

	public Point getLocation()
	{
		return new Point(getX(),getY());
	}

	public Rectangle getBounds()
	{
		return new Rectangle(getX()-RADIUS, getY()-RADIUS, RADIUS*2, RADIUS*2);
	}

	public void draw(Graphics g)
	{
		g.setColor(good?GOOD_COLOR:BAD_COLOR);
		g.fillOval(getX()-RADIUS, getY()-RADIUS, RADIUS*2, RADIUS*2);
	}
}