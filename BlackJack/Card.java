import ioutil.*;

import javax.imageio.*;
import java.awt.image.*;

public class Card
{
	public static final int WIDTH = 234;
	public static final int HEIGHT = 369;
	public enum Type
	{
		ACE		(14),
		DUECE	(2),
		THREE	(3),
		FOUR	(4),
		FIVE	(5),
		SIX		(6),
		SEVEN	(7),
		EIGHT	(8),
		NINE	(9),
		TEN		(10),
		JACK	(11),
		QUEEN	(12),
		KING	(13);

		private int value;

		Type(int v)
		{
			value = v;
		}

		public int getValue()
		{
			return value;
		}

		public int getOrder()
		{
			if(value == 14)
				return 0;
			else
				return value-1;
		}

		public int getBJValue()
		{
			if(value <= 10)
				return value;
			else if(value == 14)
				return 11;
			else return 10;
		}
	};

	public enum Suit
	{
		SPADES	(0),
		HEARTS	(1),
		CLUBS	(2),
		DIAMONDS(3);

		private int order;

		Suit(int o)
		{
			order = o;
		}

		public int getOrder(){return order;}
	};

	public Suit suit;
	public Type type;
	public BufferedImage image;

	public Card(Type t, Suit s, BufferedImage all)
	{
		type = t;
		suit = s;
		image = all.getSubimage((getCardNumber()%11)*WIDTH+10, (getCardNumber()/11)*HEIGHT+8, WIDTH, HEIGHT);
	}

	public static BufferedImage getCardBack(BufferedImage all)
	{
		return all.getSubimage(10*WIDTH+8, 4*HEIGHT+8, WIDTH, HEIGHT);
	}

	public int getCardNumber()
	{
		return type.getOrder()+13*suit.getOrder()+2;
	}

	public int getBJValue()
	{
		return type.getBJValue();
	}
}