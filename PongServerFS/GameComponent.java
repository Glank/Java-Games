import java.util.*;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

public abstract class GameComponent extends JComponent
{
	public static int WIDTH, HEIGHT;
	protected BufferedImage background = null;
	public int delay = 25;
	public boolean fullScreen = false;
	public int fsswitch = 0;

	public GameComponent(int w, int h)
	{
		super();
		WIDTH = w;
		HEIGHT = h;
		setSize(WIDTH, HEIGHT);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground(Color.WHITE);
		setVisible(true);
		Thread t = new Thread()
		{
			public void run()
			{
				while(true)
				{
					long time = System.currentTimeMillis();
					if(background == null)
					{
						background = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
						background.getGraphics().setColor(Color.WHITE);
						background.getGraphics().fillRect(0,0,WIDTH,HEIGHT);
					}

					requestFocus();

					//update game state
					standardUpdates();
					update();

					//draw stuff
					standardDraw(getCanvas());
					draw(background.getGraphics());
					if(fullScreen && (fsswitch%4 == 0))
						refreshImage();
					else if(!fullScreen)
						refreshImage();

					fsswitch++;
					if(fsswitch >= 4)
						fsswitch = 0;

					time = System.currentTimeMillis()-time;
					try
					{
						if(delay-(int)time > 0)
							sleep(delay-(int)time);
					}
					catch(Exception ex)
					{
					}
				}
			}
		};
		t.start();
	}

	//get a blank image to draw onto
	private Graphics getCanvas()
	{
		if(background == null)
		{
			background = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		}
		background.getGraphics().setColor(Color.WHITE);
		background.getGraphics().fillRect(0,0,WIDTH,HEIGHT);
		return background.getGraphics();
	}

	public void setFullScreen()
	{
		setSize(640, 480);
		fullScreen = true;
	}

	//take the canvas that you have drawn on and draw it onto the component
	private void refreshImage()
	{
		if(background != null)
		{
			if(getGraphics() != null)
			{
				if(fullScreen)
				{
					getGraphics().drawImage(background.getScaledInstance(getWidth(),getHeight(),Image.SCALE_FAST),0,0,null);
				}
				else
				{
					getGraphics().drawImage(background,0,0,null);
				}
			}
		}
	}

	public void makeTestWindow()
	{
		JFrame frame = new JFrame();
		frame.getContentPane().setLayout(new FlowLayout());
		frame.getContentPane().add(this);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public void makeFullScreenWindow()
	{
		JFrame frame = new JFrame();
		GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		GraphicsConfiguration gc = device.getDefaultConfiguration();
  		DisplayMode oldDisplayMode = device.getDisplayMode();
  	    DisplayMode newDisplayMode = new DisplayMode(640, 480, (oldDisplayMode.getBitDepth()), (oldDisplayMode.getRefreshRate()));
		frame.getContentPane().setLayout(null);
		setFullScreen();
		frame.getContentPane().add(this, 0, 0);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setUndecorated(true);
		frame.setVisible(true);
		if(device.isFullScreenSupported())
		{
			device.setFullScreenWindow(frame);
			device.setDisplayMode(newDisplayMode);
		}
		else
		{
			System.out.println("ARGS! NO FULLSCRENE!");
		}
	}

	public void standardUpdates()
	{
	}

	public abstract void draw(Graphics g);

	public void standardDraw(Graphics g)
	{
	}

	public abstract void update();
}