import java.awt.*;
import java.util.*;

/*
 * 6 Getter Setter methods added
 */
public class BlackjackHand
{
	Vector<Card> cards;
	Deck deck;
	boolean player;
	public int value;

	//Gettermethod for deck
	public Deck getDeck() {
		return deck;
	}

	//Setter method for deck
	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	//Getter method for player
	public boolean isPlayer() {
		return player;
	}

	//Setter method for player
	public void setPlayer(boolean player) {
		this.player = player;
	}
	
	//Getter for Value property
	public int getValue() {
		return this.value;
	}
	
	//Setter for Value property
	public void setValue(int value) {
		this.value = value;
	}
	
	public BlackjackHand(Deck d, boolean p)
	{
		setDeck(d);
		cards = new Vector();
		setPlayer(p);
	}

	public void setShow(boolean p)
	{
		setPlayer(p);
	}

	public void hit()
	{
		cards.add(getDeck().deal());
		calculateValue();
	}

	public void clear()
	{
		for(int i = 0; i < cards.size(); i++)
			getDeck().add(cards.get(i));
		cards = new Vector();
	}

	//Calculates the Value of the hand
	public int calculateValue(){		
		int aces = 0;
		int value = 0;
		for(int i = 0; i < cards.size(); i++)
		{
			if(cards.get(i).getBJValue() == 11)
				aces++;
			value+=cards.get(i).getBJValue();
		}
		while((value > 21) && (aces > 0))
		{
			value-=10;
			aces--;
		}
		setValue(value);
		return value;
	}
	
	public boolean busted()
	{
		return getValue() > 21;
	}

	public void draw(Graphics g, int x, int y)
	{
		if(isPlayer())
		{
			for(int i = cards.size()-1; i >= 0; i--)
			{
				g.drawImage(cards.get(i).image, x+(cards.size()-i-1)*50, y, null);
			}
		}
		else
		{
			for(int i = cards.size()-1; i >= 0; i--)
			{
				if(i == 0)
					g.drawImage(cards.get(i).image, x+(cards.size()-i-1)*50, y, null);
				else
					g.drawImage(deck.back, x+(cards.size()-i-1)*50, y, null);
			}
		}
	}
}