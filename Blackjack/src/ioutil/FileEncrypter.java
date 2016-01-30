
package ioutil;

import java.io.*;
import java.util.*;

/*
*	The file encrypter works on a rotating exclusive-or cypher that can be either one-byte or multi-byte.
*
*	Encrypt and decrypt are exact inverse functions.
*	So, you can do someting like this:
*
*	encrypt("TestFolder\\", "cypher");
*	encrypt("TestFolder\\", "cypher");
*	decrypt("TestFolder\\", "cypher");
*	encrypt("TestFolder\\", "cypher");
*	decrypt("TestFolder\\", "cypher");
*	decrypt("TestFolder\\", "cypher");
*	decrypt("TestFolder\\", "cypher");
*	encrypt("TestFolder\\", "cypher");
*
*	and TestFolder will be back to normal becase the number of encryptions and decryptions are equal.
*/

public class FileEncrypter
{
	//encrypts a folder with a one byte code
	public static void encryptFolder(String folderName, byte code) throws IOException
	{
		if(folderName.equals(""))
			System.exit(0);
		File dir = new File(folderName);
		File[] files = dir.listFiles();
		for(int i = 0; i < files.length; i++)
		{
			if(files[i].isDirectory())
				encryptFolder(files[i].getPath(), code);
			else
				encrypt(files[i].getPath(), code);
		}
	}

	//decrypts a folder with a one byte code
	public static void decryptFolder(String folderName, byte code) throws IOException
	{
		if(folderName.equals(""))
			System.exit(0);
		File dir = new File(folderName);
		File[] files = dir.listFiles();
		for(int i = 0; i < files.length; i++)
		{
			if(files[i].isDirectory())
				decryptFolder(files[i].getPath(), code);
			else
				decrypt(files[i].getPath(), code);
		}
	}

	//encrypts a folder with a multi-byte code
	public static void encryptFolder(String folderName, byte[] code) throws IOException
	{
		if(folderName.equals(""))
			System.exit(0);
		File dir = new File(folderName);
		File[] files = dir.listFiles();
		for(int i = 0; i < files.length; i++)
		{
			if(files[i].isDirectory())
				encryptFolder(files[i].getPath(), code);
			else
				encrypt(files[i].getPath(), code);
		}
	}

	//decrypts a folder with a multi-byte code
	public static void decryptFolder(String folderName, byte[] code) throws IOException
	{
		if(folderName.equals(""))
			System.exit(0);
		File dir = new File(folderName);
		File[] files = dir.listFiles();
		for(int i = 0; i < files.length; i++)
		{
			if(files[i].isDirectory())
				decryptFolder(files[i].getPath(), code);
			else
				decrypt(files[i].getPath(), code);
		}
	}

	//encrypts a file or folder with a one byte code
	public static void encrypt(String fileName, byte code) throws IOException
	{
		if(fileName.equals("FileEncrypter.class"))
			System.exit(0);
		if(new File(fileName).isDirectory())
		{
			encryptFolder(fileName, code);
			return;
		}
		DataInputStream dis = new DataInputStream(new FileInputStream(new File(fileName)));
		File temp = File.createTempFile(""+(int)(Math.random()*10000000),".tmp");
		DataOutputStream dos = new DataOutputStream(new FileOutputStream(temp));
		byte[] buffer = new byte[65536];
		int read = dis.read(buffer);
		while(read > 0)
		{
			for(int i = 0;i < read; i++)
			{
				if(i == 0)
					buffer[i] = (byte)(buffer[i]^code);
				else
					buffer[i] = (byte)(buffer[i]^buffer[i-1]);
			}
			dos.write(buffer,0,read);
			read = dis.read(buffer);
		}
		dis.close();
		dos.close();
		(new File(fileName)).delete();
		temp.renameTo(new File(fileName));
	}

	//decrypts a file or folder with a one byte code
	public static void decrypt(String fileName, byte code) throws IOException
	{
		if(fileName.equals("FileEncrypter.class"))
			System.exit(0);
		if(new File(fileName).isDirectory())
		{
			decryptFolder(fileName, code);
			return;
		}
		DataInputStream dis = new DataInputStream(new FileInputStream(new File(fileName)));
		File temp = File.createTempFile(""+(int)(Math.random()*10000000),".tmp");
		DataOutputStream dos = new DataOutputStream(new FileOutputStream(temp));
		byte[] buffer = new byte[65536];
		int read = dis.read(buffer);
		while(read > 0)
		{
			for(int i = read-1;i >=0; i--)
			{
				if(i == 0)
					buffer[i] = (byte)(buffer[i]^code);
				else
					buffer[i] = (byte)(buffer[i]^buffer[i-1]);
			}
			dos.write(buffer,0,read);
			read = dis.read(buffer);
		}
		dis.close();
		dos.close();
		(new File(fileName)).delete();
		temp.renameTo(new File(fileName));
	}

	//encrypts a file or folder with a multi-byte code
	public static void encrypt(String fileName, byte[] code) throws IOException
	{
		if(fileName.equals("FileEncrypter.class"))
			System.exit(0);
		if(new File(fileName).isDirectory())
		{
			encryptFolder(fileName, code);
			return;
		}
		DataInputStream dis = new DataInputStream(new FileInputStream(new File(fileName)));
		File temp = File.createTempFile(""+(int)(Math.random()*10000000),".tmp");
		DataOutputStream dos = new DataOutputStream(new FileOutputStream(temp));
		byte[] buffer = new byte[65536];
		int read = dis.read(buffer);
		while(read > 0)
		{
			for(int i = 0;i < read; i++)
			{
				if(i < code.length)
					buffer[i] = (byte)(buffer[i]^code[i%code.length]);
				else
					buffer[i] = (byte)(buffer[i]^buffer[i-code.length+i%code.length]);
			}
			dos.write(buffer,0,read);
			read = dis.read(buffer);
		}
		dis.close();
		dos.close();
		(new File(fileName)).delete();
		temp.renameTo(new File(fileName));
	}

	//decrypts a file or folder with a multi-byte code
	public static void decrypt(String fileName, byte[] code) throws IOException
	{
		if(fileName.equals("FileEncrypter.class"))
			System.exit(0);
		if(new File(fileName).isDirectory())
		{
			decryptFolder(fileName, code);
			return;
		}
		DataInputStream dis = new DataInputStream(new FileInputStream(new File(fileName)));
		File temp = File.createTempFile(""+(int)(Math.random()*10000000),".tmp");
		DataOutputStream dos = new DataOutputStream(new FileOutputStream(temp));
		byte[] buffer = new byte[65536];
		int read = dis.read(buffer);
		while(read > 0)
		{
			for(int i = read-1;i >= 0; i--)
			{
				if(i < code.length)
					buffer[i] = (byte)(buffer[i]^code[i%code.length]);
				else
					buffer[i] = (byte)(buffer[i]^buffer[i-code.length+i%code.length]);
			}
			dos.write(buffer,0,read);
			read = dis.read(buffer);
		}
		dis.close();
		dos.close();
		(new File(fileName)).delete();
		temp.renameTo(new File(fileName));
	}

	//encrypts a file or folder with a String code
	public static void encrypt(String fileName, String code) throws IOException
	{
		encrypt(fileName, getByteEncryptionCode(code));
	}

	//decrypts a file or folder with a String code
	public static void decrypt(String fileName, String code) throws IOException
	{
		decrypt(fileName, getByteEncryptionCode(code));
	}

	//converts a String into a multi-byte code
	public static byte[] getByteEncryptionCode(String s)
	{
		byte[] b = new byte[s.length()];
		for(int i = 0; i < s.length(); i++)
		{
			b[i] = (byte)(int)(s.charAt(i));
		}
		return b;
	}

}