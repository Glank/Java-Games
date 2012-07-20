import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class CatchComponent extends JComponent implements KeyListener, Runnable
{
	private CatchEngine engine;
	private boolean leftPressed = false;
	private boolean rightPressed = false;

	public CatchComponent()
	{
		super();
		engine = new CatchEngine();
 		setPreferredSize(new Dimension(engine.WIDTH, engine.HEIGHT));

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
			update();
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

	public void update()
	{
		if(leftPressed)
			engine.moveLeft();
		if(rightPressed)
			engine.moveRight();
		engine.update();
	}

	public void keyPressed(KeyEvent ke)
	{
		if(ke.getKeyCode() == KeyEvent.VK_LEFT)
			leftPressed = true;
		else if(ke.getKeyCode() == KeyEvent.VK_RIGHT)
			rightPressed = true;
	}

	public void keyReleased(KeyEvent ke)
	{
		if(ke.getKeyCode() == KeyEvent.VK_LEFT)
			leftPressed = false;
		else if(ke.getKeyCode() == KeyEvent.VK_RIGHT)
			rightPressed = false;
	}

	public void keyTyped(KeyEvent ke)
	{
	}
}