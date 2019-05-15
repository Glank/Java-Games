import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.Rectangle;
import java.util.Vector;
import org.junit.jupiter.api.Test;

/*
 * Added 8 getter/setter methods
 * 
 * 5/8 original public methods tested
 * 
 * Methods not under test
 *  -testBallCatch
 *  -update
 *  -draw
 */
class CatchEngineTest {

	//Test creation of object
	@Test
	void testCatchEngine1() {
		CatchEngine ce = new CatchEngine();
		assertNotNull(ce);
	}
	
	//Test properties of object after creation
	@Test
	void testCatchEngine2() {
		CatchEngine ce = new CatchEngine();
		Vector<Ball> balls = ce.getBalls();
		assertNotNull(balls);
	}
	
	//Test properties of object after creation
	@Test
	void testCatchEngine3() {
		CatchEngine ce = new CatchEngine();
		Bucket bucket = ce.getBucket();
		assertNotNull(bucket);
	}

	//Test properties of object after creation
	@Test
	void testCatchEngine4() {
		CatchEngine ce = new CatchEngine();
		int points = ce.getPoints();
		assertEquals("0 is default value of points ",0,points);
	}
	
	//Test properties of object after creation
	@Test
	void testCatchEngine5() {
		CatchEngine ce = new CatchEngine();
		int lives = ce.getLives();
		assertEquals("3 is default value of lives ",3,lives);
	}
	

	//Test adding a ball. addBall() method
	@Test
	void testAddBall() {
		// Prep
		CatchEngine ce = new CatchEngine();
		Vector<Ball> balls = ce.getBalls();
		
		// Grab before value
		int beforeAddSize = balls.size();
		
		// Add ball
		ce.addBall();
		
		// Grab after value 
		balls = ce.getBalls();
		int afterAddSize = balls.size();
		
		Boolean isLargerAfterAdd = (afterAddSize > beforeAddSize);
		
		assertEquals("Ball size after adding a ball should be larger",true,isLargerAfterAdd);
	}

	//Test moveBalls() method
	@Test
	void testMoveBalls() {
		// Prep
		CatchEngine ce = new CatchEngine();
		Vector<Ball> balls = ce.getBalls();
		
		// Grab before value
		int beforeAddSize = balls.size();
		
		ce.moveBalls();
		
		// Grab after value 
		balls = ce.getBalls();
		int afterAddSize = balls.size();
		
		Boolean isLargerAfterMove = (afterAddSize > beforeAddSize);
		
		assertEquals("Ball size should be the same after moving a ball",false,isLargerAfterMove);
		
	}

	//Test moveLeft() method
	@Test
	void testMoveLeft() {
		CatchEngine ce = new CatchEngine();
		int beforeCoord = ce.getBucket().getBounds().x;
		
		// Action
		ce.moveLeft();
		
		int afterCoord = ce.getBucket().getBounds().x;
		
		Boolean isSmallerAfterMove = (beforeCoord > afterCoord);
		
		assertEquals("Coordinates will be smaller after moving left",true,isSmallerAfterMove);			
	}
	
	//Test moveRight() method 
	@Test
	void testMoveRight() {
		CatchEngine ce = new CatchEngine();
		int beforeCoord = ce.getBucket().getBounds().x;
		
		// Action
		ce.moveRight();
		
		int afterCoord = ce.getBucket().getBounds().x;
		
		Boolean isLargerAfterMove = (afterCoord > beforeCoord);
		
		assertEquals("Coordinates will be larger after moving right",true,isLargerAfterMove);			
	}
}
