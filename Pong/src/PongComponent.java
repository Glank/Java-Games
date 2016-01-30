import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class PongComponent extends JComponent implements KeyListener, Runnable
{
	private PongEnvironment environment;
	private boolean pressingUp = false;
	private boolean pressingDown = false;

	public PongComponent()
	{
		super();
		environment = new PongEnvironment();
		environment.setComputer(false, true);
 		setPreferredSize(new Dimension(environment.WIDTH, environment.HEIGHT));

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
			environment.draw(g);
		}
	}

	public void update()
	{
		environment.update();
		if(pressingUp)
			environment.getLeft().moveUp();
		else if(pressingDown)
			environment.getLeft().moveDown();
	}

	public void keyPressed(KeyEvent ke)
	{
		if(ke.getKeyCode() == KeyEvent.VK_UP)
			pressingUp = true;
		else if(ke.getKeyCode() == KeyEvent.VK_DOWN)
			pressingDown = true;
	}

	public void keyReleased(KeyEvent ke)
	{
		if(ke.getKeyCode() == KeyEvent.VK_UP)
			pressingUp = false;
		else if(ke.getKeyCode() == KeyEvent.VK_DOWN)
			pressingDown = false;
	}

	public void keyTyped(KeyEvent ke)
	{
	}
}