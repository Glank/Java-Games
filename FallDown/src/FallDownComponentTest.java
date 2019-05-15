import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.KeyEvent;

import org.junit.jupiter.api.Test;

class FallDownComponentTest {

	//Test creating object
	@Test
	void testFallDownComponent1() {
		FallDownComponent fdc = new FallDownComponent();
		assertNotNull(fdc);
	}

	//Test Update method
	@Test
	void testUpdate1() {
		FallDownComponent fdc = new FallDownComponent();
		fdc.setLeftPressed(true);
		
		fdc.update();
		
		FallDownEngine fde = new FallDownEngine();
		
		Ball b1 = fde.getBall();
		
		fde.moveLeft();
		
		Ball b2 = fde.getBall();
		
		boolean ballIsBigger = b1.getX() > b2.getX();

		assertEquals("b1 > b2  ",true,ballIsBigger);
	}

	//Test Update method
	@Test
	void testUpdate2() {
		FallDownComponent fdc = new FallDownComponent();
		fdc.setRightPressed(true);
		
		fdc.update();
		
		FallDownEngine fde = new FallDownEngine();
		
		Ball b1 = fde.getBall();
		
		fde.moveRight();
		
		Ball b2 = fde.getBall();
		
		boolean ballIsBigger = b1.getX() > b2.getX();

		assertEquals("b1 > b2  ",false,ballIsBigger);
	}
	
	//Test keyPressed method
	@Test
	void testKeyPressed1() {
		FallDownComponent fdc = new FallDownComponent();

		KeyEvent keyEvent = new KeyEvent(fdc, 0, 0, 0, 0);
		keyEvent.setKeyCode(KeyEvent.VK_LEFT);
		
		fdc.keyPressed(keyEvent);
		
		boolean isLeftPressed = fdc.isLeftPressed();
		
		assertEquals("left is pressed. true",true,isLeftPressed);
	}
	
	//Test keyPressed method
	@Test
	void testKeyPressed2() {
		FallDownComponent fdc = new FallDownComponent();

		KeyEvent keyEvent = new KeyEvent(fdc, 0, 0, 0, 0);
		keyEvent.setKeyCode(KeyEvent.VK_RIGHT);
		
		fdc.keyPressed(keyEvent);
		
		boolean isRightPressed = fdc.isRightPressed();
		
		assertEquals("right is pressed. true",true,isRightPressed);
	}
	
	//Test keyReleased method
	@Test
	void testKeyReleased1() {
		FallDownComponent fdc = new FallDownComponent();
		fdc.setLeftPressed(true);
		
		KeyEvent keyEvent = new KeyEvent(fdc, 0, 0, 0, 0);
		keyEvent.setKeyCode(KeyEvent.VK_LEFT);
		
		fdc.keyReleased(keyEvent);
		
		boolean isLeftPressed = fdc.isLeftPressed();
		
		assertEquals("left is no longer pressed. true",false,isLeftPressed);
	}
	
	//Test keyReleased method
	@Test
	void testKeyReleased2() {
		FallDownComponent fdc = new FallDownComponent();
		fdc.setRightPressed(true);
		
		KeyEvent keyEvent = new KeyEvent(fdc, 0, 0, 0, 0);
		keyEvent.setKeyCode(KeyEvent.VK_RIGHT);
		
		fdc.keyReleased(keyEvent);
		
		boolean isRightPressed = fdc.isRightPressed();
		
		assertEquals("right is no longer pressed. true",false,isRightPressed);
	}

}
