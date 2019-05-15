import java.awt.*;
import java.util.*;

public class FallDownEngine
{
	public static final double GRAVITY = .5;
	public static final int WIDTH = 300;
	public static final int HEIGHT = 300;
	public static final int BRICK_LAYER_DELAY = 100;
	public static final int SPEED_UP_DELAY = 20;

	private Vector<Brick> bricks = new Vector();
	private Ball ball;
	private int brickSpeed = 2;
	private int brickDelay = 0;
	private int speedDelay = 0;
	private int points = -1;
	
	protected Vector<Brick> getBricks() {
		return bricks;
	}

	protected void setBricks(Vector<Brick> bricks) {
		this.bricks = bricks;
	}

	protected Ball getBall() {
		return ball;
	}

	protected void setBall(Ball ball) {
		this.ball = ball;
	}

	protected int getBrickSpeed() {
		return brickSpeed;
	}

	protected void setBrickSpeed(int brickSpeed) {
		this.brickSpeed = brickSpeed;
	}

	protected int getBrickDelay() {
		return brickDelay;
	}

	protected void setBrickDelay(int brickDelay) {
		this.brickDelay = brickDelay;
	}

	protected int getSpeedDelay() {
		return speedDelay;
	}

	protected void setSpeedDelay(int speedDelay) {
		this.speedDelay = speedDelay;
	}

	protected int getPoints() {
		return points;
	}

	protected void setPoints(int points) {
		this.points = points;
	}

	public FallDownEngine()
	{
		setBall(new Ball(WIDTH/2, HEIGHT/2));
		createBrickLayer();
	}

	public void createBrickLayer()
	{
		int hole = (int)((WIDTH/Brick.WIDTH)*Math.random());
		for(int i = 0; i < (WIDTH/Brick.WIDTH); i++)
		{
			if(i != hole)
			{
				bricks.add(new Brick(i*Brick.WIDTH+Brick.WIDTH/2, HEIGHT+Brick.HEIGHT));
			}
		}
		setPoints(getPoints()+1);
	}

	public void removeOldBricks()
	{
		for(int i = 0; i < bricks.size(); i++)
		{
			if(bricks.get(i).getLocation().getY() < 0)
			{
				bricks.remove(i);
				i--;
			}
		}
	}

	public void moveBricks()
	{
		for(int i = 0; i < bricks.size(); i++)
		{
			bricks.add(i, bricks.get(i).move(0,-brickSpeed));
			bricks.remove(i+1);
		}
	}

	public void affectBall()
	{
		for(int i = 0; i < bricks.size(); i++)
		{
			setBall( bricks.get(i).affect(ball) );
		}
		setBall(getBall().accelerate(0, GRAVITY));
		if(getBall().getLocation().getY() > HEIGHT)
			setBall( ball.setPosition((int)ball.getLocation().getX(), HEIGHT) );
	}

	public void moveLeft()
	{
		setBall(getBall().moveLeft());
		while(ball.getLocation().getX() < 0)
			setBall( ball.moveRight() );
	}

	public void moveRight()
	{
		setBall(ball.moveRight());
		while(ball.getLocation().getX() > WIDTH)
			setBall( ball.moveLeft() );
	}

	public void update()
	{
		if(getBall().getLocation().getY() >= -Ball.RADIUS)
		{
			setBall( ball.move() );
			moveBricks();
			removeOldBricks();
			setBrickDelay(getBrickDelay()+getBrickSpeed());
			if(getBrickDelay() > BRICK_LAYER_DELAY)
			{
				setBrickDelay(0);
				setSpeedDelay(getSpeedDelay() + 1);
				if(getSpeedDelay() > SPEED_UP_DELAY)
				{
					setSpeedDelay(0);
					setBrickSpeed(getBrickSpeed() + 1);
				}
				createBrickLayer();
			}
			affectBall();
		}
	}

	public void draw(Graphics g)
	{
		if(ball.getLocation().getY() < -Ball.RADIUS)
		{
			g.setColor(Color.BLUE);
			g.drawString("You Lose", WIDTH/2-27, HEIGHT/2);
		}
		else
			ball.draw(g);
		for(int i = 0; i < bricks.size(); i++)
			bricks.get(i).draw(g);
		g.setColor(Color.BLUE);
		g.drawString("Points: "+points, 10, 20);
	}
}