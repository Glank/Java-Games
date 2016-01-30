import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class CrapsComponent extends JComponent implements KeyListener, Runnable
{
	private CrapsEngine engine;

	public CrapsComponent()
	{
		super();
		engine = new CrapsEngine();
 		setPreferredSize(new Dimension(engine.getWidth(), engine.getHeight()));

 		addKeyListener(this);
 		Thread run = new Thread(this);
 		run.start();
	}

	public void run()
	{
		while(true)
		{
			try
			{
				Thread.sleep(20);
			}
			catch(Exception ex)
			{
			}
			requestFocus();
			repaint();
		}
	}

	public void paint(Graphics g)
	{
		synchronized(g)
		{
			engine.draw(g);
		}
	}

	public void keyPressed(KeyEvent ke)
	{
		if(ke.getKeyCode() == KeyEvent.VK_DOWN)
			engine.downBet();
		else if(ke.getKeyCode() == KeyEvent.VK_UP)
			engine.upBet();
		else if(ke.getKeyCode() == KeyEvent.VK_SPACE)
			engine.roll();
		else if(ke.getKeyCode() == KeyEvent.VK_ENTER)
			engine.roll();
	}

	public void keyReleased(KeyEvent ke)
	{
	}

	public void keyTyped(KeyEvent ke)
	{
	}
}