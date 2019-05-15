import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import java.awt.Rectangle;

import org.junit.jupiter.api.Test;

/*
 * Added 3 getter Setter methods 
 * 
 * 8/9 methods under test
 * 
 * Methods not under test
 *   - Draw()
 */
class BallTest {

	//Test creation of object
	@Test
	void testBallIntInt1() {
		Ball ball = new Ball(0,0);
		assertNotNull(ball);
	}

	//Test properties of object after instantiation
	@Test
	void testBallIntInt2() {
		Ball ball = new Ball(0,1);
		
		int x = ball.getX();
		int y = ball.getY();
		boolean isGood = ball.isGood();
		
		assertEquals("x =0  ",0,x);	
		assertEquals("y =1  ",1,y);
		assertEquals("isGood = true", true, isGood);
	}
	
	//Test creation of object
	@Test
	void testBallIntIntBoolean1() {
		Ball ball = new Ball(0,0,false);
		assertNotNull(ball);
	}

	//Test properties of object after instantiation
	@Test
	void testBallIntIntBoolean2() {
		Ball ball = new Ball(0,1,false);

		int x = ball.getX();
		int y = ball.getY();
		boolean isGood = ball.isGood();
		
		assertEquals("x =0  ",0,x);	
		assertEquals("y =1  ",1,y);
		assertEquals("isGood = false", false, isGood);	
	}
	
	// Test MoveTo() function
	@Test
	void testMoveTo() {
		Ball ball = new Ball(0,1);
		
		// Action
		ball = ball.moveTo(3, 0);
		
		int x = ball.getX();
		int y = ball.getY();
		boolean isGood = ball.isGood();
		
		
		assertEquals("x =3  ",3,x);	
		assertEquals("y =0  ",0,y);
		assertEquals("isGood = true", true, isGood);
	}

	// Test Move() function
	@Test
	void testMove() {
		Ball ball = new Ball(5,2,false);

		// Action
		ball = ball.move(1, 2);
		
		int x = ball.getX();
		int y = ball.getY();
		boolean isGood = ball.isGood();
		
		assertEquals("x =6  ",6,x);	
		assertEquals("y =4  ",4,y);
		assertEquals("isGood = false", false, isGood);
	}

	// Test MoveDown() function
	@Test
	void testMoveDown() {
		Ball ball = new Ball(5,2,true);

		// Action
		ball = ball.moveDown();
		
		int x = ball.getX();
		int y = ball.getY();
		boolean isGood = ball.isGood();
		
		assertEquals("x =5  ",5,x);	
		assertEquals("y =4  ",4,y);
		assertEquals("isGood = true", true, isGood);	
	}

	// Test GetLocation Function
	@Test
	void testGetLocation() {
		Ball ball = new Ball(5,2,true);

		// Action
		Point p = ball.getLocation();
		
		int x = p.x;
		int y = p.y;
		
		assertEquals("x =5  ",5,x);	
		assertEquals("y =2  ",2,y);
	}

	// Test getBounds() function
	@Test
	void testGetBounds() {
		Ball ball = new Ball(5,2,true);

		Rectangle r = ball.getBounds();
		
		int x = r.x;
		int y = r.y;
		
		assertEquals("x =0  ",0,x);	
		assertEquals("y =-3  ",-3,y);	
	}
}
