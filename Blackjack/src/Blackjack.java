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
	enum State{SETUP, BET, PLAY, DEALER, RESET, LOSE, WIN, PUSH};
	State state = State.SETUP;
	int bet = 25;
	int money = 250;

	public Blackjack() throws IOException
	{
		super(Card.WIDTH*3,Card.HEIGHT*3/2);
		deck = new Deck();
		deck.shuffle(5);
		deck.setAutoShuffle(true);
		player = new BlackjackHand(deck, true);
		dealer = new BlackjackHand(deck, false);
	}

	public void update()
	{
		if(isKeyPressed("Q"))
			System.out.println(state);
		if(state == State.SETUP)
		{
			if(dealer != null)
			{
				player.hit();
				dealer.hit();
				player.hit();
				dealer.hit();
				state = State.BET;
			}
		}
		else if(state == State.BET)
		{
			if(isKeyPressed("Up"))
				bet+=5;
			else if(isKeyPressed("Down"))
				bet-=5;
			else if(isKeyPressed("Enter"))
				state = State.PLAY;

			if(bet < 0)
				bet = 0;
			else if(bet > money)
				bet = money;
		}
		else if(state == State.PLAY)
		{
			if(player.busted())
				state = State.LOSE;
			else if(isKeyPressed("Space"))
				player.hit();
			else if(isKeyPressed("Enter"))
			{
				dealer.setShow(true);
				state = State.DEALER;
			}
		}
		else if(state == State.DEALER)
		{
			if(dealer.getValue() < 17)
				dealer.hit();
			else if(dealer.busted() || (dealer.getValue() < player.getValue()))
			{
				System.out.println("WIN");
				state = State.WIN;
			}
			else if(dealer.getValue() == player.getValue())
			{
				System.out.println("PUSH");
				state = State.PUSH;
			}
			else
			{
				System.out.println("LOSE");
				state = State.LOSE;
			}
		}
		else if(state == State.WIN)
		{
			if(isKeyPressed("Space") || isKeyPressed("Enter"))
			{
				money+=bet;
				state = State.RESET;
			}
		}
		else if(state == State.PUSH)
		{
			if(isKeyPressed("Space") || isKeyPressed("Enter"))
			{
				state = State.RESET;
			}
		}
		else if(state == State.LOSE)
		{
			if(isKeyPressed("Space") || isKeyPressed("Enter"))
			{
				money-=bet;
				state = State.RESET;
			}
		}
		else if(state == State.RESET)
		{
			if(money == 0)
				money++;
			player.clear();
			dealer.clear();
			dealer.setShow(false);
			state = State.SETUP;
		}
		resetKeys();
	}

	public void draw(Graphics g1)
	{
		Graphics2D g = (Graphics2D)g1;

		g.setColor(Color.BLACK);
		g.drawString("Money: " + money + "     Bet: " + bet, 10, 20);

		if(state == State.BET)
		{
			g.drawString("SET YOUR BET", WIDTH-150, 20);
		}
		if(state == State.PLAY)
		{
			dealer.draw(g1, 157, 12);
			player.draw(g1, 25, 158);
		}
		if(state == State.WIN)
		{
			dealer.draw(g1, 157, 12);
			player.draw(g1, 25, 158);
			g.drawString("YOU WIN!", WIDTH-150, 20);
		}
		if(state == State.LOSE)
		{
			dealer.draw(g1, 157, 12);
			player.draw(g1, 25, 158);
			g.drawString("YOU LOSE!", WIDTH-150, 20);
		}
		if(state == State.PUSH)
		{
			dealer.draw(g1, 157, 12);
			player.draw(g1, 25, 158);
			g.drawString("PUSH", WIDTH-150, 20);
		}
	}

	public static void main(String[] args) throws Exception
	{
		new Blackjack().makeTestWindow();
	}
}