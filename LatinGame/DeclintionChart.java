import java.util.*;

public class DeclintionChart
{
	ArrayList<String> chart = new ArrayList();
	ArrayList<String> type = new ArrayList();

	public DeclintionChart()
	{
	}

	public void add(String word, String t)
	{
		chart.add(word);
		type.add(t);
	}
}