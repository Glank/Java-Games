package gameutil;

import java.util.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.net.*;

/**
 * The purpose of the ImageLoader is to be able to obtain multiple instances of an object without using extra RAM space.
 */

public class ImageLoader
{
	public ArrayList<Image> images;
	public ArrayList<String> names;

	/**
	 * Constructs an ImageLoader.
	 */
	public ImageLoader()
	{
		images = new ArrayList();
		names = new ArrayList();
	}

	/**
	 * Loads an image from a given {@link URL}.
	 * @param	url	The {@link URL} of an image.
	 */
	public void load(URL url)
	{
		try
		{
			images.add(ImageIO.read(url));
			names.add(url.getFile());
		}
		catch (Exception ex)
		{
			System.err.println("Error loading image: " + url + "\n" +ex);
			System.exit(1);
		}
	}


	/**
	 * Loads an image from a given file name.
	 * @param	fileName	The file name of an image.
	 */
	public void load(String fileName)
	{
		fileName = fileName.replaceAll("&#32;", " ");
		images.add(Toolkit.getDefaultToolkit().getImage(fileName));
		fileName = fileName.replaceAll(" ", "&#32;");
		names.add(fileName);
		System.out.println(fileName + " has been loaded.");
	}


	/**
	 * Tests to see if an image from a defined file name is already loaded.
	 * @param	fileName	The fileName of an image.
	 * @return	true if the image is already loaded.
	 */
	public boolean alreadyLoaded(String fileName)
	{
		for(int i = 0; i < names.size(); i++)
		{
			if(names.get(i).equalsIgnoreCase(fileName))
				return true;
		}
		return false;
	}

	/**
	 * Loads an image from a given file name if it is not already loaded, and returns the image.
	 * @param	fileName	The file name of an image.
	 * @return	returns the image of file name "fileName"
	 */
	public Image getImage(String fileName)
	{
		fileName = fileName.replaceAll(" ","&#32;");
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

	/**
	 * Retrieves all of the loaded images.
	 * @return	All of the previously loaded {@link Image}.
	 */
	public ArrayList<Image> getImages()
	{
		return images;
	}

	/**
	 * Retrieves a loaded image at index i.
	 * @param	i	The index of an {@link Image}.
	 * @return	A the previously loaded {@link Image}.
	 */
	public Image getImage(int i)
	{
		return images.get(i);
	}

	/**
	 * Retrieves a subset of all the previously loaded images.
	 * @param	start	The starting index of all images.
	 * @param	finish	The ending index.
	 * @return	A subset of all the previously loaded images.
	 */
	public ArrayList<Image> getImages(int start, int finish)
	{
		ArrayList<Image> imgs = new ArrayList();
		for(int i = start; i <= finish; i++)
		{
			imgs.add(images.get(i));
		}
		return imgs;
	}

	/**
	 * Retrieves a group of images. (Loads the images if they are not preloaded)
	 * @param	fileNames	An ArrayList of image file names.
	 * @return	A group of images.
	 */
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
