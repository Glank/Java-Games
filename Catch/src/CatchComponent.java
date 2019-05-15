import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class CatchComponent extends JComponent implements KeyListener, Runnable
{
	private CatchEngine engine;
	private boolean leftPressed = false;
	private boolean rightPressed = false;

	//Getter method for leftPressed
	public boolean isLeftPressed() {
		return leftPressed;
	}

	//Setter method for leftPressed
	public void setLeftPressed(boolean leftPressed) {
		this.leftPressed = leftPressed;
	}

	//Getter method for rightPressed
	public boolean isRightPressed() {
		return rightPressed;
	}

	//Setter method for rightPressed
	public void setRightPressed(boolean rightPressed) {
		this.rightPressed = rightPressed;
	}

	//Getter method for engine
	public CatchEngine getEngine() {
		return engine;
	}

	//Setter method for engine
	public void setEngine(CatchEngine engine) {
		this.engine = engine;
	}
	
	public CatchComponent()
	{
		super();
		setEngine(new CatchEngine());
 		setPreferredSize(new Dimension(getEngine().WIDTH, getEngine().HEIGHT));

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
				Thread.sleep(50);
			}
			catch(Exception ex)
			{
			}
			requestFocus();
			updateState();
			repaint();
		}
	}

	public void paint(Graphics g)
	{
        engine.draw(g);
	}

	public void updateState()
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

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}
