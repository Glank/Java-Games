package gameutil;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
/*
 * Tested 8/9 methods
 */
class ImageLoaderTest {

	//Test Creates object
	@Test
	void testImageLoader() {
		ImageLoader il = new ImageLoader();
		assertNotNull(il);
	}

	//Test unsucessfully loading a picture through url
	@Test
	void testLoad() {
		ImageLoader il = new ImageLoader();
		
		boolean errorDidOccur = false;
		try {
			URL url = new URL("cards.jpg");
			il.load("cards.jpg");

		} catch (Exception e) {
			errorDidOccur = true;
		}
		
		assertEquals("Error should be thrown", true, errorDidOccur);
	}
	
	//Test sucessfully loading a picture through string
	@Test
	void testLoadString1() {
		ImageLoader il = new ImageLoader();
		il.load("cards.jpg");
		
		boolean containsFile = il.names.contains("cards.jpg");
		assertEquals("File was added to arrayList", true, containsFile);
	}
	
	//Test unsucessfully loading a picture through string
	@Test
	void testLoadString2() {
		ImageLoader il = new ImageLoader();
		il.load("blahblahblah.jpg");
		
		boolean containsFile = il.names.contains("blahblahblah.jpg");
		assertEquals("File was added to arrayList", true, containsFile);
	}

	//Test sucessful alreadyLoaded() method
	@Test
	void testAlreadyLoaded1() {
		ImageLoader il = new ImageLoader();
		il.load("cards.jpg");
		
		boolean isLoaded = il.alreadyLoaded("cards.jpg");
		
		assertEquals("File was added to arrayList", true, isLoaded);
	}


	//Test unsucessful alreadyLoaded() method
	@Test
	void testAlreadyLoaded2() {
		ImageLoader il = new ImageLoader();
		il.load("cards.jpg");
		
		boolean isLoaded = il.alreadyLoaded("cards2.jpg");
		
		assertEquals("File was not added to arrayList", false, isLoaded);
	}

	//Test getImageString() method
	@Test
	void testGetImageString() {
		ImageLoader il = new ImageLoader();
		il.load("cards.jpg");
		
		
		Image image = il.getImage("cards.jpg");
		
		assertNotNull(image);
	}

	//Tests getImages
	@Test
	void testGetImages() {
		ImageLoader il = new ImageLoader();
		il.load("cards.jpg");
		
		ArrayList<Image> imageCollection1 = il.images;
		
		ArrayList<Image> imageCollection2 = il.getImages();
		
		assertEquals("image collection 1 and two should be equal", imageCollection1, imageCollection2);
	}

	//Test getImages
	@Test
	void testGetImageInt() {		
		ImageLoader il = new ImageLoader();
		il.load("cards.jpg");
	
		Image image1 = il.images.get(0);
		Image image2 = il.getImage(0);

	
		assertEquals("image collection 1 and two should be equal", image1, image2);
	}

	//Tests getImages
	@Test
	void testGetImagesIntInt() {
		ImageLoader il = new ImageLoader();
		il.load("cards.jpg");
		
		ArrayList<Image> imageCollection1 = il.images;
		
		ArrayList<Image> imageCollection2 = il.getImages(0,0);
		
		assertEquals("image collection 1 and two should be equal", imageCollection1, imageCollection2);
	}



}
