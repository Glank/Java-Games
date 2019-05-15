import java.awt.*;

public class Ball
{
	public static final int RADIUS = 10;
	public static final int SPEED = 10;
	private int x, y;
	private double dx, dy;

	//Getter method for x
	protected int getX() {
		return x;
	}

	//Setter method for x
	protected void setX(int x) {
		this.x = x;
	}

	//Getter method for y
	protected int getY() {
		return y;
	}

	//Setter method for x
	protected void setY(int y) {
		this.y = y;
	}

	//Getter method for dx
	protected double getDx() {
		return dx;
	}

	//Setter method for dx
	protected void setDx(double dx) {
		this.dx = dx;
	}

	//Getter method for dy
	protected double getDy() {
		return dy;
	}

	//Setter method for dy
	protected void setDy(double dy) {
		this.dy = dy;
	}
	
	public Ball(int x, int y)
	{
		this(x,y,0,0);
	}

	public Ball(int x, int y, double dx, double dy)
	{
		setX(x);
		setY(y);
		setDx(dx);
		setDy(dy);
	}

	public Ball moveLeft()
	{
		return new Ball(getX()-SPEED, getY(), getDx(), getDy());
	}

	public Ball moveRight()
	{
		return new Ball(getX()+SPEED, getY(), getDx(), getDy());
	}

	public Ball accelerate(double ax, double ay)
	{
		return new Ball(getX(), getY(), getDx()+ax, getDy()+ay);
	}

	public Ball setVelocity(double dx, double dy)
	{
		return new Ball(getX(), getY(), dx, dy);
	}

	public Ball setPosition(int x, int y)
	{
		return new Ball(x, y, 0, 0);
	}

	public Ball move(int dx, int dy)
	{
		return new Ball(getX()+dx, getY()+dy, 0, 0);
	}

	public Ball move()
	{
		return new Ball(getX()+(int)getDx(), getY()+(int)getDy(), getDx(), getDy());
	}

	public Rectangle getBounds()
	{
		return new Rectangle(getX()-RADIUS, getY()-RADIUS, RADIUS*2, RADIUS*2);
	}

	public Point getLocation()
	{
		return new Point(getX(),getY());
	}

	public void draw(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillOval(getX()-RADIUS, getY()-RADIUS, RADIUS*2, RADIUS*2);
	}
}