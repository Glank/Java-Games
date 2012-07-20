
package ioutil;

import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;

public class ImageSaver
{
	//Saves an array of BufferedImages to fileName in a format such as "gif" "bmp" "jpg" etc.
	public static void save(BufferedImage[] imgs, String fileName, String format) throws IOException
	{
		FileOutputStream fos = new FileOutputStream(new File(fileName));
		fos.getChannel().position(8*imgs.length+4);
		long[] positions = new long[imgs.length];
		for(int i = 0; i < imgs.length; i++)
		{
			positions[i] = (fos.getChannel().position());
			ImageIO.write(imgs[i], format, fos);
		}
		fos.getChannel().position(0);
		DataOutputStream dos = new DataOutputStream(fos);
		dos.writeInt(positions.length);
		for(int i = 0; i < positions.length; i++)
		{
			dos.writeLong(positions[i]);
		}
		dos.close();
		fos.close();
	}

	//Loads a file saved by ImageSaver and returns the BufferedImage array.
	public static BufferedImage[] load(String fileName) throws IOException
	{
		FileInputStream fis = new FileInputStream(new File(fileName));
		DataInputStream dis = new DataInputStream(fis);
		BufferedImage[] imgs = new BufferedImage[dis.readInt()];
		long[] positions = new long[imgs.length];
		for(int i = 0; i < positions.length; i++)
		{
			positions[i] = dis.readLong();
		}
		for(int i = 0; i < positions.length; i++)
		{
			fis.getChannel().position(positions[i]);
			imgs[i] = ImageIO.read(fis);
		}
		dis.close();
		fis.close();
		return imgs;
	}

	//Loads a BufferedImage from a given file name.
	public static BufferedImage loadBufferedImage(String fileName) throws IOException
	{
		return ImageIO.read(new File(fileName));
	}

	//Creates a JFrame to display a buffered image for a given amount of miliseconds.
	public static void displayImage(BufferedImage i, int time)
	{
		JFrame frame = new JFrame();
		frame.setLocation((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2-i.getWidth()/2),(int)( Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2-i.getHeight()/2));
		frame.setSize(i.getWidth(null), i.getHeight(null));
		frame.setResizable(true);
		frame.setUndecorated(true);
		frame.setVisible(true);
		Graphics2D g = (Graphics2D)frame.getGraphics();
		while (time >= 0)
		{
			try
			{
				g.drawImage(i, null, 0, 0);
				Thread.sleep(100);
			}
			catch(Exception ex)
			{
			}
			time-=100;
		}
		frame.dispose();
	}
}