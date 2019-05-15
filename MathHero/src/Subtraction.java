public class Subtraction extends Enemy
{
	protected String problem;
	protected int solution;
	private int n1, n2;

	public Subtraction()
	{
		super(.4);
		n1 = (int)(Math.random()*9)+1;
		n2 = (int)(Math.random()*9)+1;
		solution = n1;
		problem = (n1+n2)+"-"+n2;
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

	public int getNum1() {
		return n1;
	}

	public int getNum2() {
		return n2;
	}
}
