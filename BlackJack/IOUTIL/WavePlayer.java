package ioutil;

import java.util.ArrayList;
import java.util.Scanner;

/*
The wave player works like this:

You instantiate a WavePlayer object, then create a list of music with the
add(String, String) method. After the reference is added to the player
you can play any song by using its reference string in the play(String)
method. You can probably figure out the  other methods on your own.
pause(String)
stop(String)
togglePlay(String)
loop(String)
boolean isPlaying(String)
boolean isPaused(String)
endLoop(String)
boolean isLooping(String);

I have also built in a playlist function for songs with the WavePlayer.Player
class. You add an arraylist of refferences to the playlist and then use
the inner methods
next()
back()
String getPlaying()
find(String)
to navagate the playlist. The playlist will start working right after you
create one using the setPlaylist(ArrayList<String>) method.
*/

public class WavePlayer
{
	private String dir = "";
	public ArrayList<String> playlistRefs = null;

	private ArrayList<AePlayWave> waves = new ArrayList();
	private ArrayList<String> references = new ArrayList();

	public Player playlist = null;
	private boolean playlistLoop = true;

	//creates a WavePlayer with the default directory "directory"
	public WavePlayer(String directory)
	{
		dir = directory;
	}

	//creates a WavePlayer
	public WavePlayer()
	{
		dir = "";
	}

	//Plays a wave file without pre-loading it.
	public static void quickPlay(String fileName)
	{
		AePlayWave pw = new AePlayWave(fileName);
		pw.play();
	}

	//Adds a wave file to the reference list under referenceName
	public void add(String fileName, String referenceName)
	{
		references.add(referenceName);
		waves.add(new AePlayWave(dir + fileName));
	}

	//returns an AePlayWave under reference.
	public AePlayWave getWave(String reference)
	{
		for(int i = 0; i < references.size(); i++)
		{
			if(references.get(i).equals(reference))
			{
				return waves.get(i);
			}
		}
		return null;
	}

	//Plays an added wave file.
	public void play(String reference)
	{
		AePlayWave pw = getWave(reference);
		if((pw.state).equals("waiting"))
			pw.play();
		else if((pw.state).equals("suspended"))
			pw.unPause();
	}

	//Pauses and added wave file.
	public void pause(String reference)
	{
		getWave(reference).pause();
	}

	//Stops an added wave file. (same as end(String reference)
	public void stop(String reference)
	{
		getWave(reference).restart();
	}

	//Stops an added wave file. (same as stop(String reference)
	public void end(String reference)
	{
		getWave(reference).restart();
	}

	//Either pauses or starts an added wave file.
	public void togglePlay(String reference)
	{
		AePlayWave pw = getWave(reference);
		if((pw.state).equals("waiting"))
			pw.play();
		else if((pw.state).equals("suspended"))
			pw.unPause();
		else
			pw.pause();
	}

	//Tests to see if a wave file is playing.
	public boolean isPlaying(String reference)
	{
		return (getWave(reference).state).equals("playing");
	}

	//Tests to see if a wave file is paused.
	public boolean isPaused(String reference)
	{
		return (getWave(reference).state).equals("suspended");
	}

	//Tells an added wave file to repeat when it reaches its end.
	public void loop(String reference)
	{
		getWave(reference).loop();
	}

	//Tells an added wave file not to loop when it reaches its end.
	public void endLoop(String reference)
	{
		getWave(reference).looping = false;
	}

	//Tests to see if a wave file is looping.
	public boolean isLooping(String reference)
	{
		return getWave(reference).looping;
	}

	//returns the time-state of a wave file in microseconds.
	public long getTime(String reference)
	{
		return getWave(reference).getTime();
	}

	//sets the current playlist as playReferences
	public void setPlaylist(ArrayList<String> playReferences)
	{
		if(playlist != null)
			playlist.stop();

		playlistRefs = playReferences;

		playlist = new Player();

		playlist.start();
	}

	//A supliment class for WavePlayer
	public class Player extends Thread
	{
		private int i = 0;

		private boolean next = false;
		private boolean back = false;
		private String find = "";

		public void run()
		{
			try
			{
				ArrayList<String> refs = playlistRefs;
				for(i = 0; i< refs.size();)
				{
					if(i < refs.size())
					{
						if(isPlaying(refs.get(i)) || isPaused(refs.get(i)))
						{
							if(isPaused(refs.get(i)))
							{
								end(refs.get(i));
								play(refs.get(i));
							}
						}
						else
							play(refs.get(i));
					}
					while(((isPlaying(refs.get(i)) || isPaused(refs.get(i)))) && !(next || back || !find.equals("")))
					{
						try
						{
							sleep(50);
						}
						catch(Exception ex)
						{
						}
					}
					if(((isPlaying(refs.get(i)) || isPaused(refs.get(i)))))
					{
						end(refs.get(i));
					}
					if(!back)
					{
						if(find.equals(""))
						{
							i++;
							if (playlistLoop && ( i == refs.size()))
								i = 0;
						}
						else
						{
							for(int a = 0; a < refs.size(); a++)
							{
								if(refs.get(a).equals(find))
								{
									i = a;
									a = refs.size();
								}
							}
						}
					}
					else
					{
						i--;
						if(i == -1)
							i = refs.size()-1;
					}
					back = false;
					next = false;
					find = "";
				}
			}
			catch(Exception ex)
			{
			}
		}

		//returns the reference of the current file playing.
		public String getPlaying()
		{
			return playlistRefs.get(i);
		}

		//Goes to the next reference on the list.
		public void next()
		{
			next = true;
		}

		//Goes back to the last reference on the list.
		public void back()
		{
			back = true;
		}

		//Goes directly to a certain reference.
		public void find(String ref)
		{
			find = ref;
		}
	}
}