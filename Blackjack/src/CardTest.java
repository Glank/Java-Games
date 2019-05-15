import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;

import org.junit.jupiter.api.Test;
/*
 * 3/4 original methods under test
 * 
 * Method not under test
 *    getCardBack
 */
class CardTest {

	//Test creating object without error
	@Test
	void testCard1() {
		Card card = null;
		
		boolean isExceptionCaught = false;
		try {
			Deck deck = new Deck();
			card = new Card(Card.Type.NINE, Card.Suit.CLUBS, deck.all );
		} catch (IOException e) {
			isExceptionCaught = true;
		}
		assertEquals("Should not have errored thus false", false ,isExceptionCaught);	
		assertNotNull(card);
	}
	
	//Test creating object with error
	@Test
	void testCard2() {
		Card card = null;
		
		boolean isExceptionCaught = false;
		try {
			card = new Card(Card.Type.NINE, Card.Suit.CLUBS, new BufferedImage(0, 0, 0));
		} catch (Exception e) {
			isExceptionCaught = true;
		}
		assertEquals("Should have errored thus true", true  ,isExceptionCaught);	
	}

	//Test getCardNumber()
	@Test
	void testGetCardNumber() {
		Card card = null;
		
		boolean isExceptionCaught = false;
		int x = 0;
		try {
			Deck deck = new Deck();
			card = new Card(Card.Type.NINE, Card.Suit.CLUBS, deck.all );
			
			x = card.getCardNumber();
			
		} catch (IOException e) {
			isExceptionCaught = true;
		}
		assertEquals("Should not have errored thus false", false ,isExceptionCaught);	
		assertEquals("card number is 36. true",36,x);
	}

	//Test getBJValue()
	@Test
	void testGetBJValue1() {
		Card card = null;
		
		boolean isExceptionCaught = false;
		int x = 0;
		try {
			Deck deck = new Deck();
			card = new Card(Card.Type.NINE, Card.Suit.CLUBS, deck.all );
			
			x = card.getBJValue();
			
		} catch (IOException e) {
			isExceptionCaught = true;
		}
		assertEquals("Should not have errored thus false", false ,isExceptionCaught);	
		assertEquals("card number is 9. true",9,x);
	}
	
	//Test getBJValue()
	@Test
	void testGetBJValue2() {
		Card card = null;
		
		boolean isExceptionCaught = false;
		int x = 0;
		try {
			Deck deck = new Deck();
			card = new Card(Card.Type.ACE, Card.Suit.CLUBS, deck.all );
			
			x = card.getBJValue();
			
		} catch (IOException e) {
			isExceptionCaught = true;
		}
		assertEquals("Should not have errored thus false", false ,isExceptionCaught);	
		assertEquals("card number is 11. true",11,x);
	}
	
	//Test getBJValue()
	@Test
	void testGetBJValue3() {
		Card card = null;
		
		boolean isExceptionCaught = false;
		int x = 0;
		try {
			Deck deck = new Deck();
			card = new Card(Card.Type.QUEEN, Card.Suit.CLUBS, deck.all );
			
			x = card.getBJValue();
			
		} catch (IOException e) {
			isExceptionCaught = true;
		}
		assertEquals("Should not have errored thus false", false ,isExceptionCaught);	
		assertEquals("card number is 10. true",10,x);
	}

}
