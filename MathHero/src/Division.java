public class Division extends Enemy
{
	protected String problem;
	protected int solution;

	public Division()
	{
		super(.3);
		int n1 = (int)(Math.random()*9)+1;
		int n2 = (int)(Math.random()*9)+1;
		solution = n1;
		problem = ""+(n1*n2)+"/"+n2;
		radius = 8;
		color = java.awt.Color.RED;
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