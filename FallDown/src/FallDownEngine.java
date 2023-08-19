import java.awt.*;
import java.util.*;

public class FallDownEngine
{
	public static final double GRAVITY = .5;
	public static final int WIDTH_SCREEN = 300;
	public static final int HEIGHT_SCREEN = 300;
	public static final int BRICK_LAYER_DELAY = 100;
	public static final int SPEED_UP_DELAY = 20;

	private Vector<Brick> bricks = new Vector();
	private Ball ball;
	private int brickSpeed = 2;
	private int brickDelay = 0;
	private int speedDelay = 0;
	private int points = -1;

	public FallDownEngine()
	{
		ball = new Ball(WIDTH_SCREEN/2, HEIGHT_SCREEN/2);
		createBrickLayer();
	}

	public void createBrickLayer()
	{
		int hole = (int)((WIDTH_SCREEN/Brick.WIDTH_SCREEN)*Math.random());
		for(int i = 0; i < (WIDTH_SCREEN/Brick.WIDTH_SCREEN); i++)
		{
			if(i != hole)
			{
				bricks.add(new Brick(i*Brick.WIDTH_SCREEN+Brick.WIDTH_SCREEN/2, HEIGHT_SCREEN+Brick.HEIGHT_SCREEN));
			}
		}
		points++;
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
			ball = bricks.get(i).affect(ball);
		}
		ball = ball.accelerate(0, GRAVITY);
		if(ball.getLocation().getY() > HEIGHT_SCREEN)
			ball = ball.setPosition((int)ball.getLocation().getX(), HEIGHT_SCREEN);
	}

	public void moveLeft()
	{
		ball = ball.moveLeft();
		while(ball.getLocation().getX() < 0)
			ball = ball.moveRight();
	}

	public void moveRight()
	{
		ball = ball.moveRight();
		while(ball.getLocation().getX() > WIDTH_SCREEN)
			ball = ball.moveLeft();
	}

	public void update()
	{
		if(ball.getLocation().getY() >= -Ball.RADIUS)
		{
			ball = ball.move();
			moveBricks();
			removeOldBricks();
			brickDelay = brickDelay+brickSpeed;
			if(brickDelay > BRICK_LAYER_DELAY)
			{
				brickDelay = 0;
				speedDelay++;
				if(speedDelay > SPEED_UP_DELAY)
				{
					speedDelay = 0;
					brickSpeed++;
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
			g.drawString("You Lose", WIDTH_SCREEN/2-27, HEIGHT_SCREEN/2);
		}
		else
			ball.draw(g);
		for(int i = 0; i < bricks.size(); i++)
			bricks.get(i).draw(g);
		g.setColor(Color.BLUE);
		g.drawString("Points: "+points, 10, 20);
	}
}