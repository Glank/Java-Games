package ioutil;

import java.awt.image.*;
import java.awt.*;
import java.io.*;

public class SerializableImage implements IOable, Serializable
{
	private int[][] colors = null;

	//Creates a Serializable image from a buffered image.
	public SerializableImage(BufferedImage bi)
	{
		colors = new int[bi.getWidth(null)][bi.getHeight(null)];
		for(int x = 0; x < colors.length; x++)
		{
			for(int y = 0; y < colors[0].length; y++)
			{
				colors[x][y] = bi.getRGB(x,y);
			}
		}
	}

	public SerializableImage(ObjectInputStream ois) throws IOException, ClassNotFoundException
	{
		load(ois);
	}

	public int[][] getColors()
	{
		return colors;
	}


	//Re-creates a buffered image from this serializable image.
	public BufferedImage getImage()
	{
		BufferedImage image = new BufferedImage(colors.length, colors[0].length, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		for(int x = 0; x < colors.length; x++)
		{
			for(int y = 0; y < colors[0].length; y++)
			{
				g.setColor(new Color(colors[x][y]));
				g.drawLine(x,y,x,y);
			}
		}
		return image;
	}

	public void save(ObjectOutputStream oos) throws IOException
	{
		oos.writeObject(colors);
	}

	public void load(ObjectInputStream ois) throws IOException, ClassNotFoundException
	{
		colors = (int[][])ois.readObject();
	}
}
