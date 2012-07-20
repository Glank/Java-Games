import java.awt.*;
import java.awt.event.*;

public class MathHero extends ListeningGameComponent
{
	protected World world;
	protected String typed;

	public MathHero()
	{
		super(Util.MAX_R*2,Util.MAX_R*2);
		world = World.getWorld();
		typed = "";
		start();
	}

	public void update()
	{
		world.update();
	}

	public void draw(Graphics g)
	{
		world.draw(g);
		g.setColor(Color.BLACK);
		g.drawString(typed,5,30);
	}

	public void keyTyped(KeyEvent ke)
	{
		if(ke.getKeyChar()=='\n'&&typed.length()>0)
		{
			if(typed.length()==7)
				world.tryKey(Integer.parseInt(typed));
			else
				world.player.addExp(world.getLevel().process(Integer.parseInt(typed)));
			typed = "";
		}
		if(Character.isDigit(ke.getKeyChar()))
			typed=typed+ke.getKeyChar();
	}

	public static void main(String[] args)
	{
		new MathHero().makeTestWindow();
	}
}
