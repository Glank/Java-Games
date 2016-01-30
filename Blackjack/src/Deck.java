import javax.imageio.*;
import java.awt.image.*;
import java.util.*;
import java.io.*;

public class Deck
{
	public static final String DEFAULT_FILE = "cards.jpg";

	Vector<Card> cards = new Vector();
	BufferedImage back;
	BufferedImage all;
	boolean autoShuffle = false;
	int added = 0;
	int maxCards = 52;

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
		added++;
		cards.add(c);
		if(cards.size() > maxCards)
			maxCards = cards.size();

		if(added >= maxCards)
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
		added = 0;
		times*=cards.size();
		for(int i = 0; i < times; i++)
		{
			int num = (int)(Math.random()*cards.size());
			Card c = cards.get(num);
			cards.remove(num);
			cards.add(c);
		}
	}

	public void setAutoShuffle(boolean s)
	{
		autoShuffle = s;
	}
}
