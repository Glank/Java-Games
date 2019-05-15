import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.KeyEvent;

import org.junit.jupiter.api.Test;

/*
 * Added 6 getter setter methods for class properties
 * 
 * 5/7 original methods under test
 * 
 * Methods not under test
 *   - run
 *   - paint
 */
class CatchComponentTest {

	//Test creation of object
	@Test
	void testCatchComponent1() {
		CatchComponent cc = new CatchComponent();
		assertNotNull(cc);
	}

	//Test properties of object after creation
	@Test
	void testCatchComponent2() {
		CatchComponent cc = new CatchComponent();
		CatchEngine ce = cc.getEngine();
		assertNotNull(ce);
	}

	//Test updateState() method. Will move left
	@Test
	void testUpdateState1() {
		CatchComponent cc = new CatchComponent();
		
		cc.setLeftPressed(true);
		CatchEngine ce = cc.getEngine();
		int beforeCoord = ce.getBucket().getBounds().x;
		
		//Will move left
		cc.updateState();
		
		ce = cc.getEngine();
		int afterCoord = ce.getBucket().getBounds().x;
			
		Boolean isSmallerAfterMove = (beforeCoord > afterCoord);
		
		assertEquals("Coordinates will be smaller after moving left",true,isSmallerAfterMove);			
	}
	
	//Test updateState() method. Will move right
	@Test
	void testUpdateState2() {
		CatchComponent cc = new CatchComponent();
		
		cc.setLeftPressed(false);
		cc.setRightPressed(true);
		CatchEngine ce = cc.getEngine();
		int beforeCoord = ce.getBucket().getBounds().x;
		
		//Will move right
		cc.updateState();
		
		ce = cc.getEngine();
		int afterCoord = ce.getBucket().getBounds().x;
			
		Boolean isSmallerAfterMove = (beforeCoord > afterCoord);
		
		assertEquals("Coordinates will be larger after moving right",false,isSmallerAfterMove);			
	}
	
	//Test updateState() method. Will move left then right
	@Test
	void testUpdateState3() {
		CatchComponent cc = new CatchComponent();
		
		cc.setLeftPressed(true);
		cc.setRightPressed(true);
		CatchEngine ce = cc.getEngine();
		int beforeCoord = ce.getBucket().getBounds().x;
		
		//Will move left then right
		cc.updateState();
		
		ce = cc.getEngine();
		int afterCoord = ce.getBucket().getBounds().x;
			
		Boolean isSameAfterMove = (beforeCoord == afterCoord);
		
		assertEquals("Coordinates will be the same after moving left then right",true,isSameAfterMove);			
	}
	
	// Test KeyPress passing through a key Event with left key being pressed
	@Test
	void testKeyPressed1() {
		CatchComponent cc = new CatchComponent();

		KeyEvent keyEvent = new KeyEvent(cc, 0, 0, 0, 0);
		keyEvent.setKeyCode(KeyEvent.VK_LEFT);
		cc.keyPressed(keyEvent);
		
		boolean isLeftTrue = cc.isLeftPressed();
		assertEquals("True, left key is pressed", true, isLeftTrue);	
	}
	
	// Test KeyPress passing through a key Event with right key being pressed
	@Test
	void testKeyPressed2() {
		CatchComponent cc = new CatchComponent();

		KeyEvent keyEvent = new KeyEvent(cc, 0, 0, 0, 0);
		keyEvent.setKeyCode(KeyEvent.VK_RIGHT);
		cc.keyPressed(keyEvent);
		
		boolean isRightTrue = cc.isRightPressed();
		assertEquals("True, right key is pressed", true, isRightTrue);	
	}

	// Test keyReleased() passing through key event with left key being pressed
	@Test
	void testKeyReleased1() {
		CatchComponent cc = new CatchComponent();

		KeyEvent keyEvent = new KeyEvent(cc, 0, 0, 0, 0);
		keyEvent.setKeyCode(KeyEvent.VK_LEFT);
		cc.keyPressed(keyEvent);
		
		// Action
		cc.keyReleased(keyEvent);
		
		boolean isLeftTrue = cc.isLeftPressed();
		assertEquals("false, left key is released", false, isLeftTrue);
	}

	// Test keyReleased() passing through key event with left key being pressed
	@Test
	void testKeyReleased2() {
		CatchComponent cc = new CatchComponent();

		KeyEvent keyEvent = new KeyEvent(cc, 0, 0, 0, 0);
		keyEvent.setKeyCode(KeyEvent.VK_RIGHT);
		cc.keyPressed(keyEvent);
		
		// Action
		cc.keyReleased(keyEvent);
		
		boolean isRightTrue = cc.isLeftPressed();
		assertEquals("false, left right is released", false, isRightTrue);
	}
	
}
