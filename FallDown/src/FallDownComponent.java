import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class FallDownComponent extends JComponent implements KeyListener, Runnable
{
	private FallDownEngine engine;
	private boolean leftPressed, rightPressed;

	protected boolean isLeftPressed() {
		return leftPressed;
	}

	protected void setLeftPressed(boolean leftPressed) {
		this.leftPressed = leftPressed;
	}

	protected boolean isRightPressed() {
		return rightPressed;
	}

	protected void setRightPressed(boolean rightPressed) {
		this.rightPressed = rightPressed;
	}

	public FallDownComponent()
	{
		super();
		engine = new FallDownEngine();
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
		if(isLeftPressed())
			engine.moveLeft();
		if(isRightPressed())
			engine.moveRight();
		engine.update();
	}

	public void keyPressed(KeyEvent ke)
	{
		if(ke.getKeyCode() == KeyEvent.VK_LEFT)
			setLeftPressed(true);
		else if(ke.getKeyCode() == KeyEvent.VK_RIGHT)
			setRightPressed(true);
	}

	public void keyReleased(KeyEvent ke)
	{
		if(ke.getKeyCode() == KeyEvent.VK_LEFT)
			setLeftPressed(false);
		else if(ke.getKeyCode() == KeyEvent.VK_RIGHT)
			setRightPressed(false);
	}

	public void keyTyped(KeyEvent ke)
	{
	}
}