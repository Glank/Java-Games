import java.awt.*;

public class CrapsGame
{

	private CrapsState state = CrapsState.START;
	private Dice die1, die2;
	private int point;

	public CrapsGame()
	{
		die1 = new Dice();
		die2 = new Dice();
	}

	public void roll()
	{
		if((state != CrapsState.WON) && (state != CrapsState.LOST))
		{
			die1 = die1.roll();
			die2 = die2.roll();

			if(state == CrapsState.START)
			{
				if((getNumber() == 7) || (getNumber() == 11))
					state = CrapsState.WON;
				else
				{
					state = CrapsState.ROLLING;
					point = getNumber();
				}
			}
			else
			{
				if(getNumber() == 7)
					state = CrapsState.LOST;
				else if(getNumber() == point)
					state = CrapsState.WON;
			}
		}
	}

	public int getNumber()
	{
		return die1.getState()+die2.getState();
	}

	public CrapsState getState()
	{
		return state;
	}

	public int getWidth()
	{
		return Dice.WIDTH*2+75;
	}

	public int getHeight()
	{
		return Dice.HEIGHT+30;
	}

	public void draw(Graphics g)
	{
		die1.draw(g, 0,0);
		die2.draw(g, Dice.WIDTH, 0);
		g.setColor(Color.RED);
		g.drawString(""+state, Dice.WIDTH*2+10, Dice.HEIGHT/2+5);
		if(state == CrapsState.ROLLING)
			g.drawString("Point: " + point, 10, Dice.HEIGHT+12);
		if(state != CrapsState.START)
			g.drawString("Roll: "+getNumber(), 10, Dice.HEIGHT+24);
	}
}