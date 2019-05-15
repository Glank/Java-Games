import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FallDownEngineTest {

	//Test creation of object
	@Test
	void testFallDownEngine1() {
		FallDownEngine fde = new FallDownEngine();
		assertNotNull(fde);
	}

	//Test correct class properties after creation of object
	@Test
	void testFallDownEngine2() {
		FallDownEngine fde = new FallDownEngine();
		
		int points = fde.getPoints();
		assertEquals("points = 0  ",0,points);
	}
	
	//Test createBrickLayer method
	@Test
	void testCreateBrickLayer() {
		FallDownEngine fde = new FallDownEngine();
		
		int points = fde.getPoints();
		assertEquals("points = 0  ",0,points);
	}

	//Test moveBricks() method
	@Test
	void testMoveBricks() {
		FallDownEngine fde = new FallDownEngine();
		
		Brick brick1 = fde.getBricks().elementAt(0);
		
		fde.moveBricks();
		
		Brick brick2 = fde.getBricks().elementAt(0);
		
		boolean brickIsBigger = brick1.getY() > brick2.getY();
		assertEquals("brick 1 > brick2  ",true,brickIsBigger);
	}

	//Test moveLeft() method
	@Test
	void testMoveLeft() {
		FallDownEngine fde = new FallDownEngine();
		
		Ball b1 = fde.getBall();
		
		fde.moveLeft();
		
		Ball b2 = fde.getBall();
		
		boolean ballIsBigger = b1.getX() > b2.getX();

		assertEquals("b1 > b2  ",true,ballIsBigger);
	}

	//Test moveRigHt() method
	@Test
	void testMoveRight() {
		FallDownEngine fde = new FallDownEngine();
		
		Ball b1 = fde.getBall();
		
		fde.moveRight();
		
		Ball b2 = fde.getBall();
		
		boolean ballIsBigger = b1.getX() > b2.getX();

		assertEquals("b1 !> b2  ",false,ballIsBigger);
	}

}
