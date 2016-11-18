import java.awt.*;

public class Frog
{
	public static final int RADIUS = 5;
	public static final Color COLOR = new Color(0,128,0);
	public static final int SPEED = 4;

	private int x,y;
	private Rectangle bounds;

	public Frog(int x,int y, Rectangle bounds)
	{
		this.x = x;
		this.y = y;
		this.bounds = bounds;
	}

	public Frog move(int dx, int dy)
	{
		Frog newFrog = new Frog(x+dx, y+dy, bounds);
		if(bounds.contains(newFrog.getBounds()))
			return newFrog;
		else
			return this;
	}

	public Frog moveTo(int x, int y)
	{
		return new Frog(x,y,bounds);
	}

	public Frog moveUp()
	{
		return move(0,-SPEED);
	}

	public Frog moveDown()
	{
		return move(0,SPEED);
	}

	public Frog moveLeft()
	{
		return move(-SPEED, 0);
	}

	public Frog moveRight()
	{
		return move(SPEED, 0);
	}

	public Rectangle getBounds()
	{
		return new Rectangle(x-RADIUS, y-RADIUS, RADIUS*2, RADIUS*2);
	}

	public void draw(Graphics g)
	{
		g.setColor(COLOR);
		g.fillOval(x-RADIUS, y-RADIUS, RADIUS*2, RADIUS*2);
	}
        public void drawLevel(Graphics g,int levelNumber)
        {
            String level="LEVEL "+levelNumber;
            g.setColor(COLOR);
            g.drawString(level,300,400);
        }
}