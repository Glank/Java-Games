public class Addition extends Enemy
{
	protected String problem;
	protected int solution;
	private int n1, n2;

	public Addition()
	{
		super(.6);
		n1 = (int)(Math.random()*9)+1;
		n2 = (int)(Math.random()*9)+1;
		solution = n1+n2;
		problem = n1+"+"+n2;
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
