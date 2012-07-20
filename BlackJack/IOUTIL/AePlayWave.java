package ioutil;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.util.Scanner;

//A utility method used by WavePlayer. Basicaly a single playable wave file.
public class AePlayWave extends Thread
{

	public String filename;

	private Position curPosition;

	public boolean begin = false;
	public boolean playBack = true;
	public boolean looping = false;
	public boolean suspended = false;

	public String state = null;

	private SourceDataLine auline = null;

	private final int EXTERNAL_BUFFER_SIZE = 524288; // 128Kb

	enum Position
	{
		LEFT, RIGHT, NORMAL
	};

	//Creates a wave player from a given file name.
	public AePlayWave(String wavfile)
	{
		filename = wavfile;
		curPosition = Position.NORMAL;
		state = "waiting";
		start();
	}

	//restarts the AePlayWave
	public void restart()
	{
		auline.close();
		if(state.equals("suspended"));
			unPause();
	}

	//Creates a wave player from a given file name, at a give speaker Position.
	public AePlayWave(String wavfile, Position p)
	{
		filename = wavfile;
		curPosition = p;
		start();
		state = "waiting";
	}

	//Starts the sound without looping.
	public void play()
	{
		begin = true;
		looping = false;
		playBack = true;
		state = "playing";
	}

	//Starts the sound and will loop at the end.
	public void loop()
	{
		begin = true;
		looping = true;
		playBack = true;
		state = "playing";
	}

	//stops the sound.
	public void stopPlayback()
	{
		playBack = false;
		looping = false;
	}

	public void run()
	{

		File soundFile = new File(filename);
		if (!soundFile.exists())
		{
			System.err.println("Wave file not found: " + filename);
			return;
		}

		AudioInputStream audioInputStream = null;
		try
		{
			audioInputStream = AudioSystem.getAudioInputStream(soundFile);
		}
		catch (UnsupportedAudioFileException e1)
		{
			e1.printStackTrace();
			return;
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
			return;
		}

		AudioFormat format = audioInputStream.getFormat();
		auline = null;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

		try
		{
			auline = (SourceDataLine) AudioSystem.getLine(info);
			auline.open(format);
		}
		catch (LineUnavailableException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		if (auline.isControlSupported(FloatControl.Type.PAN))
		{
			FloatControl pan = (FloatControl) auline.getControl(FloatControl.Type.PAN);
			if (curPosition == Position.RIGHT)
				pan.setValue(1.0f);
			else if (curPosition == Position.LEFT)
				pan.setValue(-1.0f);
		}

		do
		{

			auline.start();
			int nBytesRead = 0;
			byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];

			try
			{
				while (nBytesRead != -1)
				{
					nBytesRead = audioInputStream.read(abData, 0, abData.length);
					while(!begin)
					{
						sleep(20);
					}
					if (nBytesRead >= 0)
						auline.write(abData, 0, nBytesRead);
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				System.out.println("Reached");
				auline.drain();
				auline.close();
			}

			if(!looping)
			{
				begin = false;
				state = "waiting";
			}

			try
			{
				audioInputStream = AudioSystem.getAudioInputStream(soundFile);
			}
			catch (UnsupportedAudioFileException e1)
			{
				e1.printStackTrace();
				return;
			}
			catch (IOException e1)
			{
				e1.printStackTrace();
				return;
			}

			try
			{
				auline = (SourceDataLine) AudioSystem.getLine(info);
				auline.open(format);
			}
			catch (LineUnavailableException e)
			{
				e.printStackTrace();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

			if (auline.isControlSupported(FloatControl.Type.PAN))
			{
				FloatControl pan = (FloatControl) auline.getControl(FloatControl.Type.PAN);
				if (curPosition == Position.RIGHT)
					pan.setValue(1.0f);
				else if (curPosition == Position.LEFT)
					pan.setValue(-1.0f);
			}

		} while(playBack);

		state = "finished";
	}

	//pauses the sound.
	public void pause()
	{
		super.suspend();
		suspended = true;
		state = "suspended";
	}

	//unpauses the sound.
	public void unPause()
	{
		super.resume();
		suspended = false;
		if(begin)
			state = "playing";
		else
			state = "waiting";
	}

	//returns the current time in microseconds
	public long getTime()
	{
		return auline.getMicrosecondPosition();
	}

	//Tester method.
	public static void main(String[] args) throws Exception
	{
		AePlayWave pw = new AePlayWave("pirates.wav");
		Scanner kb = new Scanner(System.in);
		kb.nextLine();
		pw.play();

		String command = "";
		while(true)
		{
			command = kb.nextLine();
			if(command.equals("s"))
				pw.suspend();
			else if(command.equals("r"))
				pw.resume();
			else if(command.equals("p"))
				pw.play();
			else if(command.equals("stop"))
				pw.stop();
		}

	}
}