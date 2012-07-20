import java.util.*;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class Pong extends ListeningGameComponent
{
	public ServerSocket server = null;
	public Socket user;
	public ObjectInputStream in;
	public ObjectOutputStream out;
	public Paddle p1 = new Paddle(10,125);
	public Paddle p2 = new Paddle(240,125);
	public Ball ball = new Ball(125, 125);
	public int p1points = 0, p2points = 0;
	public Thread inUpdate;
	public Thread outUpdate;
	public boolean start = false;

	public Pong(boolean s)
	{
		super(250,250);

		if(s)
		{
			try
			{
				server = new ServerSocket(4400);
				JOptionPane.showMessageDialog(null, InetAddress.getLocalHost());
				System.out.println("Waiting...");
				user = server.accept();
				System.out.println("Reached!");
				in = new ObjectInputStream(user.getInputStream());
				out = new ObjectOutputStream(user.getOutputStream());
			}
			catch (Exception ex)
			{
				System.out.println(ex);
			}
		}
		else
		{
			try
			{
				System.out.println("Connecting...");
				user = new Socket(JOptionPane.showInputDialog(null, "IP:"), 4400);
				System.out.println("Connected!");
				out = new ObjectOutputStream(user.getOutputStream());
				in = new ObjectInputStream(user.getInputStream());
			}
			catch (Exception ex)
			{
				System.out.println(ex);
			}

		}

		inUpdate = new Thread()
		{
			public void run()
			{
				while(true)
				{
					try
					{
						if(server != null)
						{
							PongUpdate update = (PongUpdate)in.readObject();
							p2.moveTo(update.playerY);
						}
						else
						{
							PongUpdate update = (PongUpdate)in.readObject();
							p1.moveTo(update.playerY);
							ball.x = update.ballX;
							ball.y = update.ballY;
							p1points = update.player1p;
							p2points = update.player2p;
						}
						sleep(25);
					}
					catch(Exception ex)
					{
						System.out.println(ex);
					}
				}
			}
		};

		outUpdate = new Thread()
		{
			public void run()
			{
				while(true)
				{
					try
					{
						if(server != null)
						{
							out.writeObject(new PongUpdate(ball.x, ball.y, p1.y, p1points, p2points));
							out.flush();
						}
						else
						{
							out.writeObject(new PongUpdate(mouseY));
							out.flush();
						}
						sleep(50);
					}
					catch(Exception ex)
					{
						System.out.println(ex);
					}
				}
			}
		};
		inUpdate.start();
		outUpdate.start();
		start = true;
	}

	public void update()
	{
		try
		{
			if(start)
			{
				if(server != null)
				{
					p1.moveTo(mouseY);
					if(ball.getBounds().intersects(p1.getBounds()) || ball.getBounds().intersects(p2.getBounds()))
					{
						ball.bounceX();
					}
					if((ball.y-5 <= 0) || (ball.y+5 >= 250))
						ball.bounceY();
					if(ball.outOfBounds(new Rectangle(0,0,250,250)))
					{
						if(ball.x < 125)
							p2points++;
						else
							p1points++;
						ball = new Ball(125, 125);
					}

					ball.update();
				}
				else
				{
					p2.moveTo(mouseY);
				}
			}
		}
		catch (Exception ex)
		{
			System.out.println(ex);
		}
	}

	public void draw(Graphics g)
	{
		if(start)
		{
			if(g!=null)
			{
				if(ball!=null && p1!=null && p2!=null)
				{
					ball.draw(g);
					p1.draw(g);
					p2.draw(g);
					g.setColor(Color.BLUE);
					((Graphics2D)g).drawString("P1: " + p1points + "   P2: " + p2points,100,20);
				}
			}
		}
	}

	public static void main(String[] args)
	{
		(new Pong(true)).makeTestWindow();
	}
}