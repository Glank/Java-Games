import java.awt.*;

public class Brick
{
	public static final int WIDTH = 50;
	public static final int HEIGHT = 25;

	private int x, y;
	
	//Getter method for Y
	protected int getY() {
		return y;
	}

	//Setter method for Y
	protected void setY(int y) {
		this.y = y;
	}

	//Getter method for X
	protected int getX() {
		return x;
	}

	//Setter method for X
	protected void setX(int x) {
		this.x = x;
	}

	public Brick(int x, int y)
	{
		setX(x);
		setY(y);
	}

	public Brick move(int dx, int dy)
	{
		return new Brick(getX()+dx, getY()+dy);
	}

	public Rectangle getBounds()
	{
		return new Rectangle(getX()-WIDTH/2, getY()-HEIGHT/2, WIDTH, HEIGHT);
	}

	public boolean intersects(Ball b)
	{
		return b.getBounds().intersects(getBounds());
	}

	public Ball affect(Ball b)
	{
		Ball ret = b;
		while(intersects(ret))
			ret = ret.move(0, -1);

		return ret;
	}

	public Point getLocation()
	{
		return new Point(getX(),getY());
	}

	public void draw(Graphics g)
	{
		g.setColor(Color.RED);
		g.fillRect(getX()-WIDTH/2, getY()-HEIGHT/2, WIDTH-1, HEIGHT-1);
	}
}