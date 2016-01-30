import java.awt.*;

public class Paddle
{
	public static final int WIDTH = 8;
	public static final int HEIGHT = 20;
	public int x, y;
	public Paddle(int xl, int yl)
	{
		x = xl;
		y = yl;
	}

	public void moveTo(int ny)
	{
		y = ny;
	}

	public void draw(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(x-WIDTH/2, y-HEIGHT/2, WIDTH, HEIGHT);
	}

	public Rectangle getBounds()
	{
		return new Rectangle(x-WIDTH/2, y-HEIGHT/2, WIDTH, HEIGHT);
	}

}