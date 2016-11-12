import java.awt.*;

public class Car extends Rectangle
{
	public static final int WIDTH = 18;
	public static final int HEIGHT = 18;

	private int speed;
	private Color color;

	public Car(int x, int y, int speed, Color color)
	{
		super(x,y,WIDTH,HEIGHT);
		this.speed = speed;
		this.color = color;
	}

	public Car moveLeft()
	{
		return new Car(x-speed,y,speed,color);
	}

	public Car moveRight()
	{
		return new Car(x+speed,y,speed,color);
	}

	public boolean empty()
	{
		return color == null;
	}

	public void draw(Graphics g)
	{
		if(color != null)
		{
			g.setColor(color);
			g.fillRect(x,y,WIDTH,HEIGHT);
		}
	}
}