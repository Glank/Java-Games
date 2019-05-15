import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;
import java.awt.Rectangle;

import org.junit.jupiter.api.Test;

class BrickTest {

	//Test creation of object
	@Test
	void testBrick1() {
		Brick brick = new Brick(1,0);
		assertNotNull(brick);
	}

	//Test correct class properties after creation of object
	@Test
	void testBrick2() {
		Brick brick = new Brick(1,0);
		
		int x = brick.getX();
		int y = brick.getY();
		
		assertEquals("x = 1  ",1,x);
		assertEquals("y = 0  ",0,y);		
	}
	
	//Test Move method
	@Test
	void testMove() {
		Brick brick = new Brick(1,0);
		
		brick = brick.move(1, 3);
		
		int x = brick.getX();
		int y = brick.getY();
		
		assertEquals("x = 2  ",2,x);
		assertEquals("y = 3  ",3,y);
	}

	//Test GetBounds method
	@Test
	void testGetBounds() {
		Brick brick = new Brick(1,0);
		Rectangle r = brick.getBounds();
		
		int x = r.x;
		int y = r.y;
		
		assertEquals("x = -24  ",-24,x);
		assertEquals("y = -12  ",-12,y);
	}

	//Test passing case of intersect()
	@Test
	void testIntersects1() {
		Brick brick = new Brick(1,0);
		
		boolean isIntersect = brick.intersects( new Ball(0,0));
		assertEquals("intersects, true",true,isIntersect);
	}
	
	//Test failing case of intersect()
	@Test
	void testIntersects2() {
		Brick brick = new Brick(50,20);
		
		boolean isIntersect = brick.intersects( new Ball(0,0));
		assertEquals("!intersects, !true",false,isIntersect);
	}

	//Test getLocation method
	@Test
	void testGetLocation() {
		Brick brick = new Brick(1,0);

		Point p = brick.getLocation();
		
		int x = p.x;
		int y = p.y;
		
		assertEquals("x = 1  ",1,x);
		assertEquals("y = 0  ",0,y);
	}

}
