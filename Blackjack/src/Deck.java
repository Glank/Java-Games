import javax.imageio.*;
import java.awt.image.*;
import java.util.*;
import java.io.*;

/*
 * Added 6 getter/setter methods
 */
public class Deck
{
	public static final String DEFAULT_FILE = "cards.jpg";

	Vector<Card> cards = new Vector();
	BufferedImage back;
	BufferedImage all;
	private boolean autoShuffle = false;
	private int added = 0;
	private int maxCards = 52;
	
	//Setter method for autoshuffle
	public void setAutoShuffle(boolean s)
	{
		autoShuffle = s;
	}
	
	//Getter method for autoshuffle
	public boolean getAutoShuffle() {
		return autoShuffle;
	}
	
	//Setter method for added
	public void setAdded(int i) {
		added = i;
	}
	
	//Getter method for added
	public int getAdded() {
		return added;
	}
	
	//Setter method for maxCards
	public void setMaxCards(int i) {
		maxCards = i;
	}
	
	//Getter method for maxCards
	public int getMaxCards() {
		return maxCards;
	}
		
	public Deck(String fileName) throws IOException
	{
		all = ImageIO.read(new File(fileName));
		for(Card.Suit suit : Card.Suit.values())
		{
			for(Card.Type type : Card.Type.values())
			{
				cards.add(new Card(type, suit, all));
			}
		}
		back = Card.getCardBack(all);
	}

	public Deck() throws IOException
	{
		all = ImageIO.read(new File(DEFAULT_FILE));
		for(Card.Suit suit : Card.Suit.values())
		{
			for(Card.Type type : Card.Type.values())
			{
				cards.add(new Card(type, suit, all));
			}
		}
		back = Card.getCardBack(all);
	}

	public Card deal()
	{
		if(cards.size() == 0)
			return null;
		Card d = cards.get(0);
		cards.remove(0);
		return d;
	}

	public void add(Card c)
	{
		setAdded(getAdded()+1);
		cards.add(c);
		if(cards.size() > getMaxCards())
			setMaxCards( cards.size() );

		if(getAdded() >= getMaxCards())
			shuffle(10);
	}

	public void reload()
	{
		cards = new Vector();
		for(Card.Suit suit : Card.Suit.values())
		{
			for(Card.Type type : Card.Type.values())
			{
				cards.add(new Card(type, suit, all));
			}
		}
		back = Card.getCardBack(all);
	}

	public void shuffle(int times)
	{
		setAdded(0);
		times*=cards.size();
		for(int i = 0; i < times; i++)
		{
			int num = (int)(Math.random()*cards.size());
			Card c = cards.get(num);
			cards.remove(num);
			cards.add(c);
		}
	}
}
