import java.awt.*;
import java.util.*;

public class CatchEngine
{
	public static final int WIDTH = 250;
	public static final int HEIGHT = 250;

	public static final int MIN_BALL_DELAY = 30;
	public static final int MAX_BALL_DELAY = 100;
	public static final int SPEED_UP_DELAY = 20;

	private Vector<Ball> balls;
	private Bucket bucket;
	private int points = 0;
	private int lives = 3;
	private int speedDelay = 0;
	private int addBallDelay = MAX_BALL_DELAY;
	private int ballDelay = 0;
	
	
	//Getter method for Balls
	public Vector<Ball> getBalls() {
		return balls;
	}

	//Setter method for Balls
	public void setBalls(Vector<Ball> balls) {
		this.balls = balls;
	}
	
	//Getter method for bucket
	public Bucket getBucket() {
		return bucket;
	}

	//Setter method for bucket
	public void setBucket(Bucket bucket) {
		this.bucket = bucket;
	}
	
	//Getter method for lives 
	public int getLives() {
		return lives;
	}

	//Setter method for lives
	public void setLives(int lives) {
		this.lives = lives;
	}
	
	//Getter method for points
	public int getPoints() {
		return points;
	}

	//Setter method for points
	public void setPoints(int points) {
		this.points = points;
	}
	
	public CatchEngine()
	{
		setBalls(new Vector<Ball>());
		setBucket(new Bucket(WIDTH/2, HEIGHT-Bucket.HEIGHT/2));
	}

	public void moveLeft()
	{
		if(getLives() > 0)
		{
			setBucket(bucket.moveLeft());					
			if(getBucket().getLocation().getX() < -Bucket.WIDTH/2)
				setBucket(bucket.moveTo(WIDTH+Bucket.WIDTH/2, (int)getBucket().getLocation().getY()));
		}
	}

	public void moveRight()
	{
		if(getLives() > 0)
		{
			setBucket(bucket.moveRight());
			if(getBucket().getLocation().getX() > WIDTH+Bucket.WIDTH/2)
				setBucket(getBucket().moveTo(-Bucket.WIDTH/2, (int)getBucket().getLocation().getY()));
		}
	}

	public void addBall()
	{
		getBalls().add(new Ball((int)(Math.random()*WIDTH), 0, Math.random()>.5));
	}

	public void moveBalls()
	{
		for(int i = 0; i < getBalls().size(); i++)
		{
			balls.add(i, balls.get(i).moveDown());
			balls.remove(i+1);
		}
	}

	public void testBallCatch()
	{
		for(int i = 0; i < balls.size(); i++)
		{
			if(getBucket().contains(balls.get(i)))
			{
				if(getBalls().get(i).isGood())
					setPoints(this.points+1);
				else
					setLives(this.lives-1);
				balls.remove(i);
				i--;
			}
			else if(balls.get(i).getLocation().getY() >= HEIGHT+Ball.RADIUS)
			{
				if(getBalls().get(i).isGood())
					setLives(this.lives-1);
				balls.remove(i);
				i--;
			}
		}
	}

	public void update()
	{
		if(getLives() > 0)
		{
			ballDelay++;
			speedDelay++;
			if(ballDelay >= addBallDelay)
			{
				ballDelay = 0;
				addBall();
			}
			if(speedDelay >= SPEED_UP_DELAY)
			{
				speedDelay = 0;
				addBallDelay--;
				if(addBallDelay < MIN_BALL_DELAY)
					addBallDelay = MIN_BALL_DELAY;
			}
			moveBalls();
			testBallCatch();
		}
	}

	public void draw(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.drawString("Points: " + getPoints(), 10, 20);
		g.drawString("Lives: " + getLives(), 10, 30);
		if(getLives() <= 0)
		{
			g.setColor(Color.RED);
			g.drawString("You Lose", WIDTH/2-20, HEIGHT/2);
		}
		for(int i = 0; i < balls.size(); i++)
			balls.get(i).draw(g);

		getBucket().draw(g);
	}
}
