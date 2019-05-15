import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Vector;

import org.junit.jupiter.api.Test;

/*
 * 5/7 original methods under test.
 * 
 * Methods not under test
 *   -CalculateValue
 *   -Draw
 *   
 * 6 getter/setter methods created.
 */
class BlackjackHandTest {

	//Test creating object without any errors.
	@Test
	void testBlackjackHand() {
		BlackjackHand bjh = null;
		boolean isExceptionCaught = false;
		
		try {
			bjh = new BlackjackHand(new Deck(),true);
		} catch (IOException e) {
			isExceptionCaught = true;
			e.printStackTrace();
		}
		
		assertEquals("Should not have errored thus false", false ,isExceptionCaught);	
		assertNotNull(bjh);
	}

	//Test setShow() method. As a Player
	@Test
	void testSetShow1() {
		BlackjackHand bjh = null;
		boolean isExceptionCaught = false;
		
		boolean isPlayer = false;
		
		try {
			bjh = new BlackjackHand(new Deck(),true);
			bjh.setShow(true);
			isPlayer = bjh.isPlayer();
			
		} catch (IOException e) {
			isExceptionCaught = true;
			e.printStackTrace();
		}
		
		assertEquals("Should not have errored thus false", false ,isExceptionCaught);	
		assertEquals("Is player, thus true", true ,isPlayer);	
	}
	
	//Test setShow() method. As a Dealer
	@Test
	void testSetShow2() {
		BlackjackHand bjh = null;
		boolean isExceptionCaught = false;
		
		boolean isPlayer = false;
		
		try {
			bjh = new BlackjackHand(new Deck(),false);
			bjh.setShow(false);
			isPlayer = bjh.isPlayer();
			
		} catch (IOException e) {
			isExceptionCaught = true;
			e.printStackTrace();
		}
		
		assertEquals("Should not have errored thus false", false ,isExceptionCaught);	
		assertEquals("Is Dealer, thus false", false,isPlayer);	
	}

	//Test the hit() method.
	@Test
	void testHit() {
		BlackjackHand bjh = null;
		boolean isExceptionCaught = false;
		
		int oldValue = 0;
		int newValue = 0;
		
		try {
			bjh = new BlackjackHand(new Deck(),false);
			oldValue = bjh.getValue();
			
			// Action
			bjh.hit();
			
			newValue = bjh.getValue();
			
		} catch (IOException e) {
			isExceptionCaught = true;
			e.printStackTrace();
		}
		
		boolean isGreaterThan = newValue > oldValue;
		assertEquals("Should not have errored thus false", false ,isExceptionCaught);	
		assertEquals("Value is greater after another hit", true,isGreaterThan);		
	}

	//Test the Clear() method
	@Test
	void testClear() {
		BlackjackHand bjh = null;
		boolean isExceptionCaught = false;
			
		boolean isSameObject = false;

		try {
			bjh = new BlackjackHand(new Deck(),false);
			
			// Action
			bjh.clear();
			 

			Vector testToObject = new Vector();

			isSameObject = bjh.cards.equals(testToObject);
			
		} catch (IOException e) {
			isExceptionCaught = true;
			e.printStackTrace();
		}		
		
		assertEquals("Should not have errored thus false", false ,isExceptionCaught);	
		assertEquals("Vector will be a new initialzed vector after clear method", true,isSameObject);		
	}

	//Test busted() method. Busts in this case
	@Test
	void testBusted1() {
		BlackjackHand bjh = null;
		boolean isExceptionCaught = false;
			
		boolean isBusted = false;

		try {
			bjh = new BlackjackHand(new Deck(),false);
			bjh.setValue(22);
			
			// Action
			isBusted = bjh.busted();
			 			
		} catch (IOException e) {
			isExceptionCaught = true;
			e.printStackTrace();
		}		
		
		assertEquals("Should not have errored thus false", false ,isExceptionCaught);	
		assertEquals("22 is busted. True", true,isBusted);		
	}
	
	//Test busted() method. Does not bust in this case
	@Test
	void testBusted2() {
		BlackjackHand bjh = null;
		boolean isExceptionCaught = false;
			
		boolean isBusted = false;

		try {
			bjh = new BlackjackHand(new Deck(),false);
			bjh.setValue(20);
			
			// Action
			isBusted = bjh.busted();
			 			
		} catch (IOException e) {
			isExceptionCaught = true;
			e.printStackTrace();
		}		
		
		assertEquals("Should not have errored thus false", false ,isExceptionCaught);	
		assertEquals("20 is not busted. False", false,isBusted);
	}

}
