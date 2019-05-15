import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;

import org.junit.jupiter.api.Test;

/*
 * Created 4 getter/setter methods
 * 
 *  6/9 original methods under test
 *  
 *  Methods not under test
 *    -getBounds
 *    -contains
 *    -draw
 */
class BucketTest {

	// Creates the object and makes sure its not null
	@Test
	void testBucket1() {
		Bucket bucket = new Bucket(1,1);
		assertNotNull(bucket);
	}

	// Creates the object and checks class properties
	@Test
	void testBucket2() {
		Bucket bucket = new Bucket(1,1);
		int x = bucket.getX();
		
		assertEquals("x =1 ",1,x);	
	}
	
	// Creates the object and checks class properties
	@Test
	void testBucket3() {
		Bucket bucket = new Bucket(1,2);
		int y = bucket.getY();
		
		assertEquals("y =2 ",2,y);	
	}
	
	// test MoveTo() method
	@Test
	void testMoveTo() {
		Bucket bucket = new Bucket(2,2);
		bucket = bucket.moveTo(3, 4);
		
		int x = bucket.getX();
		int y = bucket.getY();
		assertEquals("x = 3 ",3,x);	
		assertEquals("y = 4", 4, y);
	}

	// test Move() method
	@Test
	void testMove() {
		Bucket bucket = new Bucket(2,2);
		bucket = bucket.move(1, 2);
		
		int x = bucket.getX();
		int y = bucket.getY();
		
		assertEquals("x = 3 ",3,x);	
		assertEquals("y = 4", 4, y);
	}

	// test MoveLeft() method
	@Test
	void testMoveLeft() {
		Bucket bucket = new Bucket(2,2);
		
		int beforeX = bucket.getX();

		bucket = bucket.moveLeft();
		
		int afterX = bucket.getX();
		
		boolean beforeXIsGreater = beforeX > afterX;
		assertEquals("Before move x coordinate is greater than after move left x coordinate",true,beforeXIsGreater);	
	}

	// Test moveRight() function
	@Test
	void testMoveRight() {
		Bucket bucket = new Bucket(2,2);
		
		int beforeX = bucket.getX();

		bucket = bucket.moveRight();
		
		int afterX = bucket.getX();
		
		boolean afterXIsGreater = beforeX < afterX;
		assertEquals("Before move x coordinate is less than after move right x coordinate",true,afterXIsGreater);		
	}

	// Test getLocation() method
	@Test
	void testGetLocation() {
		Bucket bucket = new Bucket(2,3);

		Point p = bucket.getLocation();
		int x = p.x;
		int y = p.y;
		
		assertEquals("x = 2 ",2,x);	
		assertEquals("y = 3 ",3,y);	

	}

}
