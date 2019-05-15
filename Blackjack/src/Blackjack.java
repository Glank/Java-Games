import gameutil.*;

import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

public class Blackjack extends ListeningGameComponent
{
	Deck deck;
	BlackjackHand player;
	BlackjackHand dealer;
	public enum State{SETUP, BET, PLAY, DEALER, RESET, LOSE, WIN, PUSH};
	State state = State.SETUP;
	int bet = 25;
	int money = 250;

	//Getter method for deck
	public Deck getDeck() {
		return deck;
	}

	//Setter method for deck
	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	//Getter method for player
	public BlackjackHand getPlayer() {
		return player;
	}

	//Setter method for player
	public void setPlayer(BlackjackHand player) {
		this.player = player;
	}

	//Getter method for state
	public State getState() {
		return state;
	}

	//Setter method for state
	public void setState(State state) {
		this.state = state;
	}
	
	//Getter method for dealer
	public BlackjackHand getDealer() {
		return dealer;
	}

	//Setter method for dealer
	public void setDealer(BlackjackHand dealer) {
		this.dealer = dealer;
	}

	//Getter method for bet
	public int getBet() {
		return bet;
	}

	//Setter method for bet
	public void setBet(int bet) {
		this.bet = bet;
	}

	//Getter method for money
	public int getMoney() {
		return money;
	}

	//Setter method for money
	public void setMoney(int money) {
		this.money = money;
	}

	public Blackjack() throws IOException
	{
		super(Card.WIDTH*3,Card.HEIGHT*3/2);
		
		setDeck(new Deck());
		
		getDeck().shuffle(5);
		getDeck().setAutoShuffle(true);
		
		setPlayer(new BlackjackHand(getDeck(), true));
		
		setDealer(new BlackjackHand(getDeck(), false));
	}

	// Update the State
	public void update()
	{
		if(isKeyPressed("Q"))
			System.out.println(getState());
		updateStateSeam( getState() );
		resetKeys();
	}

	/*
	 * Seam for state in updateMethod.
	 * 
	 * Enter in a State:
	 * 	Call to correlating State function
	 *  
	 */
	private void updateStateSeam(State s) {
		
		//SETUP, BET, PLAY, DEALER, RESET, LOSE, WIN, PUSH
		switch(s) {
			case SETUP :
				stateSetup();
				break;
			case BET :
				stateBet();
				break;
			case PLAY :
				statePlay();
				break;
			case DEALER :
				stateDealer();
				break;
			case RESET :
				stateReset();
				break;
			case LOSE :
				stateLose();
				break;
			case WIN :
				stateWin();
				break;
			case PUSH :
				statePush();
				break;
		}		
	}
	
	// Operations for Setup Stage.
	private void stateSetup() {
		if(getDealer() != null)
		{
			getPlayer().hit();
			getDealer().hit();
			getPlayer().hit();
			getDealer().hit();
			setState(State.BET);
		}
	}
	
	// Operationsfor Bet State
	private void stateBet() {
		if(isKeyPressed("Up"))
			setBet( (getBet() + 5) );
		else if(isKeyPressed("Down"))
			setBet( (getBet() - 5) );
		else if(isKeyPressed("Enter"))
			setState( State.PLAY );

		if(getBet() < 0)
			setBet( 0 );
		else if( getBet() > getMoney() )
			setBet( getMoney() );
	}
	
	// Operations for Play State
	private void statePlay() {
		if(getPlayer().busted())
			setState( State.LOSE );
		else if(isKeyPressed("Space"))
			getPlayer().hit();
		else if(isKeyPressed("Enter"))
		{
			getDealer().setShow(true);
			setState( State.DEALER );
		}
	}

	// Operations for Dealer State
	private void stateDealer() {
		if( getDealer().getValue() < 17 ) {
			dealer.hit();
		}
		else if( getDealer().busted() || (getDealer().getValue() < getPlayer().getValue()) )
		{
			System.out.println("WIN");
			setState( State.WIN );
		}
		else if(dealer.getValue() == player.getValue())
		{
			System.out.println("PUSH");
			setState( State.PUSH );
		}
		else
		{
			System.out.println("LOSE");
			setState( State.LOSE );
		}
	}
	
	// Operations for Reset State
	private void stateReset() {
		if( getMoney() == 0 )
			setMoney( getMoney() + 2 );
		getPlayer().clear();
		getDealer().clear();
		getDealer().setShow(false);
		setState( State.SETUP );
	}
	
	// Operations for Lose State
	private void stateLose() {
		if(isKeyPressed("Space") || isKeyPressed("Enter"))
		{
			setMoney( getMoney() - getBet() );
			setState( State.RESET );
		}	
	}
	
	// Operations for Win State
	private void stateWin() {
		if(isKeyPressed("Space") || isKeyPressed("Enter"))
		{
			setMoney( getMoney() + getBet() );
			setState( State.RESET );
		}
	}
	
	// Operations for Reset State
	private void statePush() {
		if(isKeyPressed("Space") || isKeyPressed("Enter"))
		{
			setState( State.RESET );
		}
	}
	
	public void draw(Graphics g1)
	{
		Graphics2D g = (Graphics2D)g1;

		g.setColor(Color.BLACK);
		g.drawString("Money: " + money + "     Bet: " + getBet(), 10, 20);

		if(getState() == State.BET)
		{
			g.drawString("SET YOUR BET", getWIDTH()-150, 20);
		}
		if(getState() == State.PLAY)
		{
			getDealer().draw(g1, 157, 12);
			getPlayer().draw(g1, 25, 158);
		}
		if(getState() == State.WIN)
		{
			getDealer().draw(g1, 157, 12);
			getPlayer().draw(g1, 25, 158);
			g.drawString("YOU WIN!", getWIDTH()-150, 20);
		}
		if(getState() == State.LOSE)
		{
			getDealer().draw(g1, 157, 12);
			getPlayer().draw(g1, 25, 158);
			g.drawString("YOU LOSE!", getWIDTH()-150, 20);
		}
		if(getState() == State.PUSH)
		{
			getDealer().draw(g1, 157, 12);
			getPlayer().draw(g1, 25, 158);
			g.drawString("PUSH", getWIDTH()-150, 20);
		}
	}

	public static void main(String[] args) throws Exception
	{
		new Blackjack().makeTestWindow();
	}
}