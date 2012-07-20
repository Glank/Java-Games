import java.util.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.net.*;
import java.applet.*;

public class ImageLoader
{
	public ArrayList<Image> images;
	public ArrayList<String> names;
	public Applet applet = null;

	public ImageLoader()
	{
		images = new ArrayList();
		names = new ArrayList();
	}

	public void load(URL url)
	{
		try
		{
			if(applet == null)
				images.add(ImageIO.read(url));
			else
			{
				System.out.println("Reached");
				images.add(applet.getImage(url));
			}
			names.add(url.toString());
		}
		catch (Exception ex)
		{
			System.err.println("Error loading image: " + url + "\n" +ex);
			System.exit(1);
		}
	}

	public void load(String fileName)
	{
		fileName = fileName.replaceAll("&#32;", " ");
		images.add(Toolkit.getDefaultToolkit().getImage(fileName));
		fileName = fileName.replaceAll(" ", "&#32;");
		names.add(fileName);
	}

	public boolean alreadyLoaded(String fileName)
	{
		for(int i = 0; i < names.size(); i++)
		{
			if(names.get(i).equalsIgnoreCase(fileName))
				return true;
		}
		return false;
	}

	public Image getImage(String fileName)
	{
		if(alreadyLoaded(fileName))
		{
			for(int i = 0; i < names.size(); i++)
			{
				if(names.get(i).equalsIgnoreCase(fileName))
					return images.get(i);
			}
		}
		load(fileName);
		return images.get(images.size()-1);
	}

	public ArrayList<Image> getImages()
	{
		return images;
	}

	public Image getImage(int i)
	{
		return images.get(i);
	}

	public ArrayList<Image> getImages(int start, int finish)
	{
		ArrayList<Image> imgs = new ArrayList();
		for(int i = start; i <= finish; i++)
		{
			imgs.add(images.get(i));
		}
		return imgs;
	}

	public ArrayList<Image> getImages(ArrayList<String> fileNames)
	{
		ArrayList<Image> imgs = new ArrayList();
		for(int i = 0; i < fileNames.size(); i++)
		{
			imgs.add(getImage(fileNames.get(i)));
		}
		return imgs;
	}

}