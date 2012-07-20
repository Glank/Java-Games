import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class FroggerComponent extends JComponent implements KeyListener, Runnable
{
	private FroggerLevelEngine engine;
	private boolean upPressed, downPressed, leftPressed, rightPressed;
	private Vector<FroggerLevel> levels = new Vector();
	private int level = 0;

	public FroggerComponent()
	{
		super();
		levelInit();
		engine = new FroggerLevelEngine(levels.get(level));
 		setPreferredSize(new Dimension(engine.WIDTH, engine.HEIGHT));

 		addKeyListener(this);
 		Thread run = new Thread(this);
 		run.start();
	}

	public void levelInit()
	{
		levels.add(new FroggerLevel(
			new int[]{1,1,1},
			new String[]{"LLL       LLL       ", "RRRR     RRRR     RRRR     ",
				"RR  BB     LL  MM     "}));

		levels.add(new FroggerLevel(
			new int[]{2,1,2,1,2},
			new String[]{"RR         L  ", "BB  LL  RR     ", "    RR    MM     ",
				"MMM     MMM     ", "RR      L     "}));

		levels.add(new FroggerLevel(
			new int[]{1,1,1,1,1,1,1},
			new String[]{"RR     RR     RR     ", "B   B   B   B   B   ",
				"MMM   MMM     MMM    ", "RR     RR     RR     ",
				"B   B   B   B   B   B   ", "MMM   MMM     MMM    L ",
				"   BBB    BBB L    BBB"}));

		levels.add(new FroggerLevel(
			new int[]{1,1,2,2,3,2,1},
			new String[]{"RR     LL     B   ", "LLL      BB   R    ",
				"RRR      LL        ", "MMM        MM    ", "L          L      ",
				"RR     L      M     ", "RRR    BL       "}));

		levels.add(new FroggerLevel(
			new int[]{1,2,3,4,5},
			new String[]{"BB  L    RRR    M", "RR B  MMM     L    ",
				"MM     LL     BB     ", "M      L      BB      ",
				"LL            "}));

		levels.add(new FroggerLevel(
			new int[]{1,3,4,3,1,2,3,4,5},
			new String[]{"MMM   LL     RR      ", "BBB       LL    RR    ",
			"BB        LL         ", "BBB       MM    MM   ", "MMM  LL  B    R  L B ",
			"MM  RR  LL  BB  LL  ", "BBB     LL  MMMM    ", "L    L     LLL     "}));
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
		if(upPressed)
			engine.moveUp();
		if(downPressed)
			engine.moveDown();
		if(leftPressed)
			engine.moveLeft();
		if(rightPressed)
			engine.moveRight();

		engine.update();

		if(engine.getState() == FroggerState.WON)
		{
			level++;
			if(level >= levels.size())
			{
				JOptionPane.showMessageDialog(this, "You have beaten this game.");
				System.exit(0);
			}
			engine = new FroggerLevelEngine(levels.get(level));
		}
	}

	public void keyPressed(KeyEvent ke)
	{
		if(ke.getKeyCode() == KeyEvent.VK_DOWN)
			downPressed = true;
		else if(ke.getKeyCode() == KeyEvent.VK_UP)
			upPressed = true;
		else if(ke.getKeyCode() == KeyEvent.VK_LEFT)
			leftPressed = true;
		else if(ke.getKeyCode() == KeyEvent.VK_RIGHT)
			rightPressed = true;
		else if(ke.getKeyCode() == KeyEvent.VK_SPACE)
		{
			if(engine.getState() == FroggerState.HIT)
				engine = new FroggerLevelEngine(levels.get(level));
		}
	}

	public void keyReleased(KeyEvent ke)
	{
		if(ke.getKeyCode() == KeyEvent.VK_DOWN)
			downPressed = false;
		else if(ke.getKeyCode() == KeyEvent.VK_UP)
			upPressed = false;
		else if(ke.getKeyCode() == KeyEvent.VK_LEFT)
			leftPressed = false;
		else if(ke.getKeyCode() == KeyEvent.VK_RIGHT)
			rightPressed = false;
	}

	public void keyTyped(KeyEvent ke)
	{
	}
}