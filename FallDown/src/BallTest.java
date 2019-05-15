import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import java.awt.Rectangle;

import org.junit.jupiter.api.Test;

class BallTest {

	//Test creation of object
	@Test
	void testBallIntInt1() {
		Ball ball = new Ball(1,0);
		assertNotNull(ball);
	}
	
	//Test correct class properties after creation of object
	@Test
	void testBallIntInt2() {
		Ball ball = new Ball(1,3);
		
		int x = ball.getX();
		int y = ball.getY();
		double dx = ball.getDx();
		double dy = ball.getDy();
		
		assertEquals("x = 1  ",1,x);
		assertEquals("y = 3  ",3,y);
		assertEquals("dx = 0  ",(int)0,(int)dx);
		assertEquals("dy = 0  ",(int)0,(int)dy);
	}
	
	//Test creation of object
	@Test
	void testBallIntIntDoubleDouble1() {
		Ball ball = new Ball(1,5,2,3);
		assertNotNull(ball);	
	}
	
	//Test correct class properties after creation of object
	@Test
	void testBallIntIntDoubleDouble2() {
		Ball ball = new Ball(1,5,2,3);

		int x = ball.getX();
		int y = ball.getY();
		double dx = ball.getDx();
		double dy = ball.getDy();
		
		assertEquals("x = 1  ",1,x);
		assertEquals("y = 5  ",5,y);
		assertEquals("dx = 2  ",(int)2,(int)dx);
		assertEquals("dy = 3  ",(int)3,(int)dy);
	}

	//Test moveLeft() method
	@Test
	void testMoveLeft() {
		Ball ball = new Ball(11,5,2,3);
		ball = ball.moveLeft();
		
		int x = ball.getX();
		int y = ball.getY();
		double dx = ball.getDx();
		double dy = ball.getDy();
		
		assertEquals("x = 1  ",1,x);
		assertEquals("y = 5  ",5,y);
		assertEquals("dx = 2  ",(int)2,(int)dx);
		assertEquals("dy = 3  ",(int)3,(int)dy);
	}

	//Test moveRight() method
	@Test
	void testMoveRight() {
		Ball ball = new Ball(11,5,2,3);
		ball = ball.moveRight();
		
		int x = ball.getX();
		int y = ball.getY();
		double dx = ball.getDx();
		double dy = ball.getDy();
		
		assertEquals("x = 21  ",21,x);
		assertEquals("y = 5  ",5,y);
		assertEquals("dx = 2  ",(int)2,(int)dx);
		assertEquals("dy = 3  ",(int)3,(int)dy);
	}

	//Test accelerate method
	@Test
	void testAccelerate() {
		Ball ball = new Ball(11,5,2,3);
		ball = ball.accelerate(1, 1);
		
		int x = ball.getX();
		int y = ball.getY();
		double dx = ball.getDx();
		double dy = ball.getDy();
		
		assertEquals("x = 11  ",11,x);
		assertEquals("y = 5  ",5,y);
		assertEquals("dx = 3  ",(int)3,(int)dx);
		assertEquals("dy = 4  ",(int)4,(int)dy);	
	}

	//Test setVelocity method
	@Test
	void testSetVelocity() {
		Ball ball = new Ball(11,5,2,3);
		ball = ball.setVelocity(1, 2);
		
		int x = ball.getX();
		int y = ball.getY();
		double dx = ball.getDx();
		double dy = ball.getDy();
		
		assertEquals("x = 11  ",11,x);
		assertEquals("y = 5  ",5,y);
		assertEquals("dx = 1  ",(int)1,(int)dx);
		assertEquals("dy = 2  ",(int)2,(int)dy);
	}

	//Test setPosition() method
	@Test
	void testSetPosition() {
		Ball ball = new Ball(11,5,2,3);
		ball = ball.setPosition(1, 2);
		
		int x = ball.getX();
		int y = ball.getY();
		double dx = ball.getDx();
		double dy = ball.getDy();
		
		assertEquals("x = 1  ",1,x);
		assertEquals("y = 2  ",2,y);
		assertEquals("dx = 0  ",(int)0,(int)dx);
		assertEquals("dy = 0  ",(int)0,(int)dy);
	}

	//Test move() method
	@Test
	void testMoveIntInt() {
		Ball ball = new Ball(11,5,2,3);
		ball = ball.move(1, 2);
		
		int x = ball.getX();
		int y = ball.getY();
		double dx = ball.getDx();
		double dy = ball.getDy();
		
		assertEquals("x = 12  ",12,x);
		assertEquals("y = 7  ",7,y);
		assertEquals("dx = 0  ",(int)0,(int)dx);
		assertEquals("dy = 0  ",(int)0,(int)dy);
	}

	//Test move() method
	@Test
	void testMove() {
		Ball ball = new Ball(11,5,2,3);
		ball = ball.move();
		
		int x = ball.getX();
		int y = ball.getY();
		double dx = ball.getDx();
		double dy = ball.getDy();
		
		assertEquals("x = 13  ",13,x);
		assertEquals("y = 8  ",8,y);
		assertEquals("dx = 2  ",(int)2,(int)dx);
		assertEquals("dy = 3  ",(int)3,(int)dy);
	}

	//Test getBounds() method
	@Test
	void testGetBounds() {
		Ball ball = new Ball(11,5,2,3);

		Rectangle r = ball.getBounds();
		
		int x = r.x;
		int y = r.y;

		assertEquals("x = 1  ",1,x);
		assertEquals("y = -5  ",-5,y);
	}
	
	//Test getLocation() method
	@Test
	void testGetLocation() {
		Ball ball = new Ball(11,5,2,3);

		Point p = ball.getLocation();
		
		int x = p.x;
		int y = p.y;

		assertEquals("x = 11  ",11,x);
		assertEquals("y = 5  ",5,y);
	}
}
