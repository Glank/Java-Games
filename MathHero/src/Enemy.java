import java.awt.*;

public abstract class Enemy
{
	protected double r,t,speed;
	protected Color color = Color.BLACK;
	protected int radius = 5;
	private boolean dying1 = false;
	private boolean dying2 = false;
	private boolean dead = false;
	private int alpha = 255;
	private int arrowR = Util.PLAYER_RADIUS+Util.ARROW_LENGTH;

	public Enemy(double speed)
	{
		this.speed = speed;
		r = Util.MAX_R;
		t = Math.random()*Math.PI*2;
	}

	public int x()
	{
		return Util.MAX_R+(int)(Math.cos(t)*r+.5);
	}

	public int y()
	{
		return Util.MAX_R+(int)(Math.sin(t)*r+.5);
	}

	private int arrowX1()
	{
		return Util.MAX_R+(int)(Math.cos(t)*arrowR+.5);
	}

	private int arrowY1()
	{
		return Util.MAX_R+(int)(Math.sin(t)*arrowR+.5);
	}

	private int arrowX2()
	{
		return Util.MAX_R+(int)(Math.cos(t)*(arrowR-Util.ARROW_LENGTH)+.5);
	}

	private int arrowY2()
	{
		return Util.MAX_R+(int)(Math.sin(t)*(arrowR-Util.ARROW_LENGTH)+.5);
	}

	public void die()
	{
		dying1 = true;
	}

	public boolean dead()
	{
		return dead;
	}

	public boolean hitting()
	{
		return r == Util.PLAYER_RADIUS+radius && !dying1;
	}

	public void update()
	{
		if(!dying2)
		{
			r-=speed;
			if(r<Util.PLAYER_RADIUS+radius)
				r = Util.PLAYER_RADIUS+radius;
		}

		if(dying2)
		{
			alpha/=1.1;
			if(alpha==0)
				dead = true;
		}
		else if(dying1)
		{
			arrowR+=Util.ARROW_SPEED;
			if(arrowR>=r)
				dying2 = true;
		}
	}

	public abstract String getProblem();
	public abstract int getSolution();

	public void draw(Graphics g)
	{
		g.setColor(color);
		g.drawString(getProblem(), x()-radius, y()-radius);
		g.fillOval(x()-radius, y()-radius, radius*2, radius*2);
		if(dying1)
		{
			g.setColor(Color.BLACK);
			g.drawLine(arrowX1(),arrowY1(),arrowX2(),arrowY2());
		}
		if(dying2)
		{
			color = new Color(color.getRed(),color.getGreen(),color.getBlue(),alpha);
		}
	}
}