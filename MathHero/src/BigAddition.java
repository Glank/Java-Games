public class BigAddition extends Enemy
{
	protected String problem;
	protected int solution;
	private int n1, n2;

	public BigAddition()
	{
		super(.3);
		n1 = (int)(Math.random()*90)+10;
		n2 = (int)(Math.random()*90)+10;
		solution = n1+n2;
		problem = ""+n1+"+"+n2;
		radius = 15;
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
