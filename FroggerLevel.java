public class FroggerLevel implements java.io.Serializable
{
	//public static final FroggerLevel TEST = new FroggerLevel(new int[]{1,1,1}, new String[]{"RRR  LLL  BB  MM   ","RRR  LLL  BB  MM   ","RRR  LLL  BB  MM   "});

	private int[] speeds;
	private String[] patterns;

	public FroggerLevel(int[] speeds, String[] patterns)
	{
		this.speeds = speeds;
		this.patterns = patterns;
	}

	public int[] getSpeeds()
	{
		return speeds;
	}

	public String[] getPatterns()
	{
		return patterns;
	}
}