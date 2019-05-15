/* FILE.java
 *
 * @author Thomas Johnson
 *
 * Pong tests suite
 */

import java.awt.Color;
import java.awt.Rectangle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static java.util.concurrent.TimeUnit.SECONDS;
import org.junit.Test;

import org.junit.Rule;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class PongTests {

		@Rule
		public Timeout globalTimeout = new Timeout(10, SECONDS);

		Color black = Color.black; // black color
		Color white = Color.white; // white color
		Color blue = Color.blue; // blue color
		Color red = Color.red; // red color
		Color green = Color.green; // green color
		Color yellow = Color.yellow; // yellow color
		Color nil = null; // null color for testing

		boolean boolF = false; // boolean false variable
		boolean boolT = true; // boolean true variable

		/*
 *  PONG GAME TESTS SUITE
 */

// Ball Tests

@Test
public void testBall1() {

	// empty test
}

@Test
public void testBall2() {

	// test for creating one single Ball object
	Ball ball = new Ball(black, 1, 1);
}

@Test
public void testBallGetX() {

	// tests for getX() method
	Ball ball = new Ball(black, 1, 1);
	Ball ball1 = new Ball(black, 5, 1);
	Ball ball2 = new Ball(black, 10, 1);
	Ball ball3 = new Ball(black, 150, 1);
	Ball ball4 = new Ball(black, 25, 1);
	Ball ball5 = new Ball(black, 100, 1);

	assertEquals("The x-coordinate of Ball object is at 1", 1, ball.getX());
	assertEquals("The x-coordinate of Ball object is at 5", 5, ball1.getX());
	assertEquals("The x-coordinate of Ball object is at 10", 10, ball2.getX());
	assertEquals("The x-coordinate of Ball object is at 150", 150, ball3.getX());
	assertEquals("The x-coordinate of Ball object is at 25", 25, ball4.getX());
	assertEquals("The x-coordinate of Ball object is at 100", 100, ball5.getX());
}

@Test
public void testBallGetY() {

	// tests for getY() method
	Ball ball = new Ball(black, 1, 1);
	Ball ball1 = new Ball(black, 5, 5);
	Ball ball2 = new Ball(black, 10, 10);
	Ball ball3 = new Ball(black, 150, 150);
	Ball ball4 = new Ball(black, 25, 25);
	Ball ball5 = new Ball(black, 100, 100);

	assertEquals("The y-coordinate of Ball object is at 1", 1, ball.getY());
	assertEquals("The y-coordinate of Ball object is at 5", 5, ball1.getY());
	assertEquals("The y-coordinate of Ball object is at 10", 10, ball2.getY());
	assertEquals("The y-coordinate of Ball object is at 150", 150, ball3.getY());
	assertEquals("The y-coordinate of Ball object is at 25", 25, ball4.getY());
	assertEquals("The y-coordinate of Ball object is at 100", 100, ball5.getY());
}

@Test
public void testBallGetDx() {

	// tests for getDx() method
	int dx = (Math.random() < .5)?1:-1;
	Ball ball = new Ball(black, dx, 1);

	assertTrue("The result of dx and ball's dx is -1 or 1", ball.getDx() == -1 || ball.getDx() == 1);
	assertFalse("The result of ball's dx is either -1 or 1", ball.getDx() == 0);
}

@Test
public void testBallGetDy() {

	// tests for getDy() method
	int dy = (Math.random() < .5)?1:-1;

	Ball ball = new Ball(black, 1, dy);

	assertTrue("The result of dy and ball's dy is equal to -1 or 1", ball.getDy() == -1 || ball.getDy() == 1);
	assertFalse("The result of ball's dy is either -1 or 1", ball.getDy() == 0);
}

@Test
public void testBallGetColor() {

	// tests for getColor() method
	Ball ball = new Ball(black, 1, 1);
	Ball ball1 = new Ball(white, 1, 1);
	Ball ball2 = new Ball(red, 1, 1);
	Ball ball3 = new Ball(blue, 1, 1);
	Ball ball4 = new Ball(green, 1, 1);
	Ball ball5 = new Ball(yellow, 1, 1);

	assertEquals("The color of the ball object is black", black, ball.getColor());
	assertEquals("The color of the ball object is white", white, ball1.getColor());
	assertEquals("The color of the ball object is red", red, ball2.getColor());
	assertEquals("The color of the ball object is blue", blue, ball3.getColor());
	assertEquals("The color of the ball object is green", green, ball4.getColor());
	assertEquals("The color of the ball object is yellow", yellow, ball5.getColor());
}

@Test
public void testBallGetSpeed() {

	// test for getSpeed() method
	Ball ball = new Ball(black, 1, 1);

	assertEquals("The speed of the ball object is always 4", 4, ball.getSpeed());
}

@Test
public void testBallGetSpeedUpDelay() {

	// test for getSpeedUpDelay() method
	Ball ball = new Ball(black, 1, 1);

	assertEquals("The speed up delay of the ball object is always 25", 25, ball.getSpeedUpDelay());
}

@Test
public void testBallDelay() {

	// test for getDelay() method
	Ball ball = new Ball(black, 1, 1);

	assertEquals("The delay of the ball object is always 0", 0, ball.getDelay());
}

@Test
public void testBallRadius() {

	// test for getSpeed() method
	Ball ball = new Ball(black, 1, 1);

	assertEquals("The delay of the ball object is always 10", 10, ball.getBallRadius());
}

@Test
public void testGetLocation() {

	// test for getLocation() method
	Ball ball = new Ball(black, 1, 1);
	Point p = new Point(1, 1);

	assertEquals("The location of this ball is at point p(1, 1)", p, ball.getLocation());
}

// Paddle Tests

@Test
public void testPaddle1() {

	// empty test
}

@Test
public void testPaddle2() {

	// test for creating one single Paddle object
	Paddle paddle = new Paddle(1, 1);
}

@Test
public void testPaddleGetX() {

	// tests for getX() method
	Paddle paddle = new Paddle(1, 1);
	Paddle paddle1 = new Paddle(15, 1);
	Paddle paddle2 = new Paddle(100, 1);
	Paddle paddle3 = new Paddle(25, 1);
	Paddle paddle4 = new Paddle(200, 1);

	assertEquals("The x-coordinate for paddle object is 1", 1, paddle.getX());
	assertEquals("The x-coordinate for paddle1 object is 15", 15, paddle1.getX());
	assertEquals("The x-coordinate for paddle2 object is 100", 100, paddle2.getX());
	assertEquals("The x-coordinate for paddle3 object is 25", 25, paddle3.getX());
	assertEquals("The x-coordinate for paddle4 object is 200", 200, paddle4.getX());
}

@Test
public void testPaddleGetY() {

	// tests for getY() method
	Paddle paddle = new Paddle(1, 1);
	Paddle paddle1 = new Paddle(15, 15);
	Paddle paddle2 = new Paddle(100, 100);
	Paddle paddle3 = new Paddle(25, 25);
	Paddle paddle4 = new Paddle(200, 200);

	assertEquals("The y-coordinate for paddle object is 1", 1, paddle.getY());
	assertEquals("The y-coordinate for paddle1 object is 15", 15, paddle1.getY());
	assertEquals("The y-coordinate for paddle2 object is 100", 100, paddle2.getY());
	assertEquals("The y-coordinate for paddle3 object is 25", 25, paddle3.getY());
	assertEquals("The y-coordinate for paddle4 object is 200", 200, paddle4.getY());
}

@Test
public void testPaddleGetWidth() {

	// tests for getWidth() method
	Paddle paddle = new Paddle(1, 1);

	assertEquals("The width of the paddle is always 10", 10, paddle.getWidth());
}

@Test
public void testPaddleGetHeight() {

	// tests for getHeight() method
	Paddle paddle = new Paddle(1, 1);

	assertEquals("The height of the paddle is always 50", 50, paddle.getHeight());
}

@Test
public void testPaddleGetMaxSpeed() {

	// tests for getMaxSpeed() method
	Paddle paddle = new Paddle(1, 1);

	assertEquals("The max speed of the paddle is always 10", 10, paddle.getMaxSpeed());
}

@Test
public void testPaddleMove() {

	// tests for move() method
	Paddle paddle = new Paddle(5, 10);

	paddle.move(10);
	assertEquals("After moving 10 in the y-direction, the new y value is 20", 20, paddle.getY());

	paddle.move(10);
	assertEquals("After moving 10 in the y-direction, the new y value is 30", 30, paddle.getY());

	paddle.move(50);
	assertEquals("After moving 10 in the y-direction, the new y value is 80", 80, paddle.getY());

	paddle.move(100);
	assertEquals("After moving 10 in the y-direction, the new y value is 180", 180, paddle.getY());

	paddle.move(-50);
	assertEquals("After moving 10 in the y-direction, the new y value is 130", 130, paddle.getY());

	paddle.move(0);
	assertEquals("After moving 10 in the y-direction, the new y value is 130", 130, paddle.getY());
}

// PongComponent Tests

@Test
public void testPongComponent1() {

	// empty test
}

@Test
public void testPongComponent2() {

	// test for creating one single PongComponent object
	PongComponent pc = new PongComponent();
}

// PongEnvironment Tests

@Test
public void testPongEnvironment1() {

	// empty test
}

@Test
public void testPongEnvironment2() {

	// test for creating one single PongEnvironment object
	PongEnvironment pe = new PongEnvironment();
}

@Test
public void testSetGetComputer() {

	// tests for setComputer() and both getComputer() methods
	PongEnvironment pe = new PongEnvironment();

	pe.setComputer(boolT, boolF);
	assertEquals("The left computer of pe object is true", boolT, pe.getLeftComputer());
	assertEquals("The right computer of pe object is false", boolF, pe.getRightComputer());

	pe.setComputer(boolF, boolT);
	assertEquals("The left computer of pe object is false", boolF, pe.getLeftComputer());
	assertEquals("The right computer of pe object is true", boolT, pe.getRightComputer());

	pe.setComputer(boolF, boolF);
	assertEquals("The left computer of pe object is false", boolF, pe.getLeftComputer());
	assertEquals("The right computer of pe object is false", boolF, pe.getRightComputer());

	pe.setComputer(boolT, boolT);
	assertEquals("The left computer of pe object is true", boolT, pe.getLeftComputer());
	assertEquals("The right computer of pe object is true", boolT, pe.getRightComputer());
}

@Test
public void testPaddleWidth() {

	// test for getPaddleWidth() method
	PongEnvironment pe = new PongEnvironment();

	assertEquals("The width of the paddle is always 400", 400, pe.getPaddleWidth());
}

@Test
public void testPaddleHeight() {

	// test for getPaddleHeight() method
	PongEnvironment pe = new PongEnvironment();

	assertEquals("The height of the paddle is always 300", 300, pe.getPaddleHeight());
}

@Test
public void testgetBall() {

	// test for getBall() method
	PongEnvironment pe = new PongEnvironment();
	Ball ball = new Ball(green, 100, 100);

	assertTrue("The local ball object does not equal pe object's ball", ball != pe.getBall());
	assertFalse("The local ball object does not equal pe object's ball", ball == pe.getBall());
}

@Test
public void testGetLeftPaddle() {

	// test for getLeftPaddle() method
	PongEnvironment pe = new PongEnvironment();
	Paddle left = new Paddle(200, 100);

	assertTrue("The local paddle object does not equal the left paddle in PongEnvironment class", left != pe.getLeft());
	assertFalse("The local paddle object does not equal the left paddle in PongEnvironment class", left == pe.getLeft());
}

@Test
public void testGetRightPaddle() {

	// test for getRightPaddle() method
	PongEnvironment pe = new PongEnvironment();
	Paddle right = new Paddle(200, 100);

	assertTrue("The local paddle object does not equal the right paddle in PongEnvironment class", right != pe.getRight());
	assertFalse("The local paddle object does not equal the right paddle in PongEnvironment class", right == pe.getRight());
}

// PlayPong Tests

@Test
public void testPlayPong1() {

	// empty test
}

@Test
public void testPlayPong2() {

	// test for creating one single PlayPong object
	PlayPong pong = new PlayPong();
}
}
