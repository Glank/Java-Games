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

	public boolean getLeftComputer() {
		return this.leftComputer;
	}

	public boolean getRightComputer() {
		return this.rightComputer;
	}

	public Ball getBall() {
		return this.ball;
	}

	public int getPaddleWidth() {
		return WIDTH;
	}

	public int getPaddleHeight() {
		return HEIGHT;
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

	public void updateTop() {
		if(ball.getLocation().getY() + Ball.RADIUS >= HEIGHT)
			ball.bounceTop();
	}

	public void updateBottom() {

		if(ball.getLocation().getY() - Ball.RADIUS <= 0)
			ball.bounceTop();
	}

	public void updateLeftSide() {

		if(left.contains(ball))
			ball.bounceSide();
	}

	public void updateRightSide() {

		if(right.contains(ball))
			ball.bounceSide();
	}

	public void updateLeftScore() {

		if(ball.getLocation().getX() > WIDTH)
			leftScore();
	}

	public void updateRightScore() {

		if(ball.getLocation().getX() < 0)
			rightScore();
	}

	public void update()
	{
		ball.move();

		updateBottom();
		updateTop();
		updateLeftSide();
		updateRightSide();
		updateLeftScore();
		updateRightScore();

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
