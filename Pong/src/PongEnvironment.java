import java.awt.*;

public class PongEnvironment
{
	public static final int WIDTH = 400;
	public static final int HEIGHT = 300;

	private Ball ball;
	private Paddle left, right;
	private int leftScore = 0, rightScore = 0;
	private boolean leftComputer, rightComputer;

	public PongEnvironment()
	{
		ball = new Ball(Color.BLACK, WIDTH/2, HEIGHT/2);
		left = new Paddle(10, HEIGHT/2);
		right = new Paddle(WIDTH-10, HEIGHT/2);
	}

	public void setComputer(boolean l, boolean r)
	{
		leftComputer = l;
		rightComputer = r;
	}

	public Paddle getLeft()
	{
		return left;
	}

	public Paddle getRight()
	{
		return right;
	}

	private void leftScore()
	{
		leftScore++;

		try
		{
			Thread.sleep(1000);
		}
		catch(Exception ex)
		{
		}

		ball = new Ball(Color.BLACK, WIDTH/2, HEIGHT/2);
		left = new Paddle(10, HEIGHT/2);
		right = new Paddle(WIDTH-10, HEIGHT/2);
	}

	private void rightScore()
	{
		rightScore++;

		try
		{
			Thread.sleep(1000);
		}
		catch(Exception ex)
		{
		}

		ball = new Ball(Color.BLACK, WIDTH/2, HEIGHT/2);
		left = new Paddle(10, HEIGHT/2);
		right = new Paddle(WIDTH-10, HEIGHT/2);
	}

	public void update()
	{
		ball.move();
		if(ball.getLocation().getY() - Ball.RADIUS <= 0)
			ball.bounceTop();
		else if(ball.getLocation().getY() + Ball.RADIUS >= HEIGHT)
			ball.bounceTop();
		else if(left.contains(ball))
			ball.bounceSide();
		else if(right.contains(ball))
			ball.bounceSide();
		else if(ball.getLocation().getX() < 0)
			rightScore();
		else if(ball.getLocation().getX() > WIDTH)
			leftScore();

		if(leftComputer)
			left.moveTo((int)ball.getLocation().getY());
		if(rightComputer)
			right.moveTo((int)ball.getLocation().getY());

	}

	public void draw(Graphics g)
	{
		ball.draw(g);
		left.draw(g);
		right.draw(g);

		g.drawString("Score: "+ leftScore, 25, 12);
		g.drawString("Score: "+ rightScore, WIDTH-125, 12);
	}
}