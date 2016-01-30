public class BigMultiplication extends Enemy
{
	protected String problem;
	protected int solution;

	public BigMultiplication()
	{
		super(.05);
		int n1 = (int)(Math.random()*90)+10;
		int n2 = (int)(Math.random()*90)+10;
		solution = n1*n2;
		problem = ""+n1+"*"+n2;
		radius = 20;
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