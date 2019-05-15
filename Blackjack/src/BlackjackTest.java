import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

/*
 * Added 12 Getter/Setter methods for class properties 
 * 
 * 3/4 originally public funtions under test
 * 
 * functions not under test
 *    -draw()
 *    
 * seam created : updateStateSeam()
 */
class BlackjackTest {

	//Test updating from SETUP -> BET
	@Test
	void testUpdate1() {		
		boolean isSucessful = true;
		
		Blackjack.State state = null;
		try {
			Blackjack blackJack = new Blackjack();	
			
			blackJack.setState( Blackjack.State.SETUP );
			
			// Action 
			blackJack.update();
			
			//retrieved the data
			state = blackJack.getState();
			
		} catch (IOException e) {
			isSucessful = false;
			e.printStackTrace();
		}

		assertEquals("Should not have errored thus true", true ,isSucessful);	
		assertEquals("Update after SETUP causes state change to BET ",Blackjack.State.BET,state);
	}
	
	/*
	 * Test updating from BET State
	 * 
	 *   -Betvalue > 0. Should return betValue = 0
	 */	
	@Test
	void testUpdate2() {		
		boolean isSucessful = true;
				
		int betValue = -1;
		
		try {
			Blackjack blackJack = new Blackjack();
			
			blackJack.setBet(-2);
			blackJack.setState( Blackjack.State.BET );
			
			// Action 
			blackJack.update();
			
			betValue = blackJack.getBet();
			
		} catch (IOException e) {
			isSucessful = false;
			e.printStackTrace();
		}

		assertEquals("Should not have errored thus true", true ,isSucessful);	
		assertEquals("bound checking on betValue  ",0,betValue);
	}
	

	/*
	 * Test updating from BET State
	 * 
	 *   -Betvalue > Moneyvalue. Should return betValue = Moneyvalue.
	 */
	@Test
	void testUpdate3() {		
		boolean isSucessful = true;
				
		int betValue = -1;
		
		try {
			Blackjack blackJack = new Blackjack();
			
			blackJack.setBet(1000);
			blackJack.setMoney(10);
			blackJack.setState( Blackjack.State.BET );
			
			// Action 
			blackJack.update();
			
			betValue = blackJack.getBet();
			
		} catch (IOException e) {
			isSucessful = false;
			e.printStackTrace();
		}

		assertEquals("Should not have errored thus true", true ,isSucessful);	
		assertEquals("bound checking on betValue  ",10,betValue);
	}
	
	/*
	 * Test updating from BET State
	 * 
	 *   -Betvalue >= Moneyvalue. Should return betValue = Moneyvalue.
	 */	
	@Test
	void testUpdate4() {		
		boolean isSucessful = true;
				
		int betValue = -1;
		
		try {
			Blackjack blackJack = new Blackjack();
			
			blackJack.setBet(1000);
			blackJack.setMoney(1000);
			blackJack.setState( Blackjack.State.BET );
			
			// Action 
			blackJack.update();
			
			betValue = blackJack.getBet();
			
		} catch (IOException e) {
			isSucessful = false;
			e.printStackTrace();
		}

		assertEquals("Should not have errored thus true", true ,isSucessful);	
		assertEquals("bound checking on betValue ",1000,betValue);
	}

	/*
	 * Test updating from PLAY State
	 * 
	 * 	Updates to Dealer state if enter is pressed
	 */	
	@Test
	void testUpdate5() {		
		boolean isSucessful = true;
				
		int betValue = -1;
		
		Blackjack.State state = null;
		
		try {
			Blackjack blackJack = new Blackjack();

			blackJack.setState( Blackjack.State.PLAY );
			blackJack.keysPressed.add("Enter");			
			
			// Action 
			blackJack.update();
			
			
			state = blackJack.getState();
			
		} catch (IOException e) {
			isSucessful = false;
			e.printStackTrace();
		}

		assertEquals("Should not have errored thus true", true ,isSucessful);	
		assertEquals("Play to Dealer state ",Blackjack.State.DEALER,state);
	}
	
	/*
	 * Test updating from DEALER State
	 * 
	 * 	Updates to Dealer state if it is a PUSH
	 */	
	@Test
	void testUpdate6() {		
		boolean isSucessful = true;
				
		int betValue = -1;
		
		Blackjack.State state = null;
		
		try {
			Blackjack blackJack = new Blackjack();

			blackJack.setState( Blackjack.State.DEALER );
			
			blackJack.dealer.setValue(18);
			blackJack.player.setValue(18);
				
			// Action 
			blackJack.update();		
			
			state = blackJack.getState();			
		} catch (IOException e) {
			isSucessful = false;
			e.printStackTrace();
		}

		assertEquals("Should not have errored thus true", true ,isSucessful);	
		assertEquals("Update after SETUP causes state change to BET ",Blackjack.State.PUSH,state);
	}
	
	/*
	 * Test updating from DEALER State
	 * 
	 * 	Updates to Dealer state if it is a LOSE
	 */	
	@Test
	void testUpdate7() {
		boolean isSucessful = true;		
		Blackjack.State state = null;
		
		try {
			Blackjack blackJack = new Blackjack();

			blackJack.setState( Blackjack.State.DEALER );
			
			blackJack.dealer.setValue(18);
			blackJack.player.setValue(17);
				
			// Action 
			blackJack.update();
			
			state = blackJack.getState();			
		} catch (IOException e) {
			isSucessful = false;
			e.printStackTrace();
		}

		assertEquals("Should not have errored thus true", true ,isSucessful);	
		assertEquals("Player has less points than dealer. Lose ",Blackjack.State.LOSE,state);
	}
	
	/*
	 * Test updating from DEALER State
	 * 
	 * 	Updates to Dealer state if it is a WIN
	 */	
	@Test
	void testUpdate8() {
		boolean isSucessful = true;		
		Blackjack.State state = null;
		
		try {
			Blackjack blackJack = new Blackjack();

			blackJack.setState( Blackjack.State.DEALER );
			
			blackJack.dealer.setValue(17);
			blackJack.player.setValue(18);
				
			// Action 
			blackJack.update();
			
			state = blackJack.getState();			
		} catch (IOException e) {
			isSucessful = false;
			e.printStackTrace();
		}

		assertEquals("Should not have errored thus true", true ,isSucessful);	
		assertEquals("Player has more points than dealer. Win ",Blackjack.State.WIN,state);
	
	}

	/*
	 * Test updating from RESET State
	 * 
	 * Updates to SETUP State
	 */
	@Test
	void testUpdate9() {
		boolean isSucessful = true;		
		Blackjack.State state = null;
		
		try {
			Blackjack blackJack = new Blackjack();

			blackJack.setState( Blackjack.State.RESET );
			
				
			// Action 
			blackJack.update();
			
			state = blackJack.getState();			
		} catch (IOException e) {
			isSucessful = false;
			e.printStackTrace();
		}

		assertEquals("Should not have errored thus true", true ,isSucessful);	
		assertEquals("Reset to Setup state transition ",Blackjack.State.SETUP,state);
	
	}
	
	/*
	 * Test updating from LOSE State
	 * 
	 * Updates to RESET
	 */
	@Test
	void testUpdate10() {
		boolean isSucessful = true;		
		Blackjack.State state = null;
		
		try {
			Blackjack blackJack = new Blackjack();

			blackJack.setState( Blackjack.State.LOSE );
			blackJack.keysPressed.add("Enter");			

				
			// Action 
			blackJack.update();
			
			state = blackJack.getState();			
		} catch (IOException e) {
			isSucessful = false;
			e.printStackTrace();
		}

		assertEquals("Should not have errored thus true", true ,isSucessful);	
		assertEquals("Lose to Reset state transition ",Blackjack.State.RESET,state);
	}
	
	/*
	 * Test updating from WIN State
	 * 
	 * Updates to RESET state
	 */
	@Test
	void testUpdate11() {
		boolean isSucessful = true;		
		Blackjack.State state = null;
		
		try {
			Blackjack blackJack = new Blackjack();

			blackJack.setState( Blackjack.State.WIN );
			blackJack.keysPressed.add("Enter");			

				
			// Action 
			blackJack.update();
			
			state = blackJack.getState();			
		} catch (IOException e) {
			isSucessful = false;
			e.printStackTrace();
		}

		assertEquals("Should not have errored thus true", true ,isSucessful);	
		assertEquals("Win to Reset state transition",Blackjack.State.RESET,state);
	}
	
	/*
	 * Test updating from WIN State
	 * 
	 * Updates to RESET state
	 */
	@Test
	void testUpdate12() {
		boolean isSucessful = true;		
		Blackjack.State state = null;
		
		try {
			Blackjack blackJack = new Blackjack();

			blackJack.setState( Blackjack.State.PUSH );
			blackJack.keysPressed.add("Enter");			

				
			// Action 
			blackJack.update();
			
			state = blackJack.getState();			
		} catch (IOException e) {
			isSucessful = false;
			e.printStackTrace();
		}

		assertEquals("Should not have errored thus true", true ,isSucessful);	
		assertEquals("Push to Reset state transition",Blackjack.State.RESET,state);
	}
	//Test creating object 
	@Test
	void testBlackjack1() {
		Blackjack blackJack = null;
		
		try {
			blackJack = new Blackjack();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertNotNull(blackJack);
	}
	
	//Test creating object without exceptions
	@Test
	void testBlackjack2() {
		
		boolean isSucessful = true;
		Blackjack blackJack = null;
		
		try {
			blackJack = new Blackjack();
		} catch (IOException e) {
			isSucessful = false;
			e.printStackTrace();
		}

		assertEquals("Should not have errored thus true", true ,isSucessful);	
		assertNotNull(blackJack);
	}
	
	//Test object properties without exceptions
	@Test
	void testBlackjack3() {
		
		boolean isSucessful = true;
		Blackjack blackJack = null;
		
		try {
			blackJack = new Blackjack();
		} catch (IOException e) {
			isSucessful = false;
			e.printStackTrace();
		}

		assertEquals("Should not have errored thus true", true ,isSucessful);	
		assertNotNull(blackJack.getDeck());
	}
	
	//Test object properties without exceptions
	@Test
	void testBlackjack4() {
		
		boolean isSucessful = true;
		Blackjack blackJack = null;
		
		try {
			blackJack = new Blackjack();
		} catch (IOException e) {
			isSucessful = false;
			e.printStackTrace();
		}
				
		//times
		assertEquals("Should not have errored thus true", true ,isSucessful);	
		assertNotNull( blackJack.getPlayer() );
	}
	
	//Test object properties without exceptions
	@Test
	void testBlackjack5() {
		
		boolean isSucessful = true;
		Blackjack blackJack = null;
		
		try {
			blackJack = new Blackjack();
		} catch (IOException e) {
			isSucessful = false;
			e.printStackTrace();
		}
				
		//times
		assertEquals("Should not have errored thus true", true ,isSucessful);	
		assertNotNull( blackJack.getDealer() );
	}

	//Test running main() without exception
	@Test
	void testMain() {
		boolean isSuccessful = true;
		try {
			Blackjack blackJack = new Blackjack();
		} catch (IOException e) {
			isSuccessful = false;
			e.printStackTrace();
		}
		
		assertEquals("Should be true if sucessful ",true,isSuccessful);	
	}

}
