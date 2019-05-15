import java.awt.*;

public class Ball
{
	public static final int RADIUS = 10;

	private int x, y;
	private int dx, dy;
	private Color color;
	private int speed;
	private int speedUpDelay = 25;
	private int delay = 0;

	public Ball(Color c, int x, int y)
	{
		color = c;
		this.x = x;
		this.y = y;
		dx = (Math.random() < .5)?1:-1;
		dy = (Math.random() < .5)?1:-1;
		speed = 4;
	}

	public int getBallRadius() {
		return this.RADIUS;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public int getDx() {
		return this.dx;
	}

	public int getDy() {
		return this.dy;
	}

	public Color getColor() {
		return this.color;
	}

	public int getSpeed() {
		return this.speed;
	}

	public int getSpeedUpDelay() {
		return this.speedUpDelay;
	}

	public int getDelay() {
		return this.delay;
	}

	public void move()
	{
		delay = (delay+1)%speedUpDelay;
		if(delay == 0)
			speed++;
		x+=(int)(getUnitDX()*speed);
		y+=(int)(getUnitDY()*speed);
	}

	public Point getLocation()
	{
		return new Point(x,y);
	}

	private double getUnitDX()
	{
		return ((double)dx/(double)(Math.sqrt(dx*dx + dy*dy)));
	}

	private double getUnitDY()
	{
		return ((double)dy/(double)(Math.sqrt(dx*dx + dy*dy)));
	}

	public void bounceSide()
	{
		dx = -dx;
		dy = (int)(Math.random()*8)-4;
		move();
		move();
	}

	public void bounceTop()
	{
		dy = -dy;
		move();
	}

	public void draw(Graphics g)
	{
		g.setColor(color);
		g.fillOval(x-RADIUS, y-RADIUS, RADIUS*2, RADIUS*2);
	}
}
