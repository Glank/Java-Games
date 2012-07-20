public class Addition extends Enemy
{
	protected String problem;
	protected int solution;

	public Addition()
	{
		super(.6);
		int n1 = (int)(Math.random()*9)+1;
		int n2 = (int)(Math.random()*9)+1;
		solution = n1+n2;
		problem = ""+n1+"+"+n2;
	}

	public String getProblem()
	{
		return problem;
	}

	public int getSolution()
	{
		return solution;
	}
}