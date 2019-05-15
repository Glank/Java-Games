import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Vector;

import org.junit.jupiter.api.Test;

/*
 * 6 getter/setter methods
 * 
 * 6/6 original methods under test
 */
class DeckTest {

	//Test creating the object without an exception
	@Test
	void testDeckString1() {
		Deck deck = null;
		
		boolean isExceptionCaught = false;
		try {
			deck = new Deck("cards.jpg");
		} catch (IOException e) {
			isExceptionCaught = true;
			e.printStackTrace();
		}
		assertEquals("Should not have errored thus false", false ,isExceptionCaught);	
		assertNotNull(deck);	
	}
	
	//Test creating the object with an exception
	@Test
	void testDeckString2() {
		Deck deck = null;
		
		boolean isExceptionCaught = false;
		try {
			deck = new Deck("rando.jpg");
		} catch (IOException e) {
			isExceptionCaught = true;
			//e.printStackTrace();
		}
		assertEquals("Should have errored thus true", true ,isExceptionCaught);	
	}

	//Test creating the object without an exception
	@Test
	void testDeck() {
		Deck deck = null;
		
		boolean isExceptionCaught = false;
		try {
			deck = new Deck();
		} catch (IOException e) {
			isExceptionCaught = true;
		}
		assertEquals("Should not have errored thus false", false ,isExceptionCaught);	
		assertNotNull(deck);
	}

	//test deal() method.
	@Test
	void testDeal() {
		Deck deck = null;
		
		boolean isExceptionCaught = false;
		try {
			deck = new Deck();
			deck.cards = new Vector();
			
		} catch (IOException e) {
			isExceptionCaught = true;
		}
		
		assertEquals("Should not have errored thus false", false ,isExceptionCaught);	
		assertNull(deck.deal(), "Deal will return null because card count = 0");	
	}

	//Test Add() method
	@Test
	void testAdd() {
		Deck deck = null;
		
		boolean isExceptionCaught = false;
		
		int oldValue = 0;
		int newValue = 0;
		
		try {
			deck = new Deck();
			deck.cards = new Vector();
			
			oldValue = deck.getAdded();
			
			// Action
			deck.add(new Card(Card.Type.NINE, Card.Suit.CLUBS, deck.all ));
		
			
			newValue = deck.getAdded();
		} catch (IOException e) {
			isExceptionCaught = true;
		}
		
		boolean isAddedIsBigger = newValue > oldValue;
		
		assertEquals("Should not have errored thus false", false ,isExceptionCaught);	
		assertEquals("Value will be bigger after adding a card. true", true,isAddedIsBigger);		
	}
	
	// Test reload method()
	@Test
	void testReload() {
		Deck deck = null;
		
		boolean isExceptionCaught = false;
		
		int deckSize = 0;
		
		try {
			deck = new Deck();
			deck.cards = new Vector();
			
			// Action
			deck.reload();
		
			deckSize = deck.cards.size();
			
		} catch (IOException e) {
			isExceptionCaught = true;
		}
		
		boolean is52deck = 52 == deckSize;
		
		assertEquals("Should not have errored thus false", false ,isExceptionCaught);	
		assertEquals("Value will be bigger 52 after getting a new deck. true", true,is52deck);		
	}

	//Test shuffle
	@Test
	void testShuffle1() {
		Deck deck = null;
		
		boolean isExceptionCaught = false;
		
		int addedSize = 0;
		
		try {
			deck = new Deck();
			deck.cards = new Vector();
			
			// Action
			deck.shuffle(-1);
		
			addedSize = deck.getAdded();
			
		} catch (IOException e) {
			isExceptionCaught = true;
		}
		
		boolean is0added= 0 == addedSize;
		
		assertEquals("Should not have errored thus false", false ,isExceptionCaught);	
		assertEquals("Value will be 0 because no new cards are added. true", true,is0added);		
	}

	//Tests shuffle()
	@Test
	void testShuffle2() {
		Deck deck = null;
		
		boolean isExceptionCaught = false;
		
		int addedSize = 0;
		
		try {
			deck = new Deck();
			deck.cards = new Vector();
			
			// Action
			deck.shuffle(2);
		
			addedSize = deck.getAdded();
			
		} catch (IOException e) {
			isExceptionCaught = true;
		}
		
		boolean is0added= 0 == addedSize;
		
		assertEquals("Should not have errored thus false", false ,isExceptionCaught);	
		assertEquals("Value will be 0 because no new cards are added. true", true,is0added);		
	}
}
