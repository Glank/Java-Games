import java.awt.*;

public class Player
{
	protected int maxHealth = 100;
	protected int health = maxHealth;
	protected int exp = 0;
	protected int expToNextLevel = 10;
	protected int level = 1;

	public void hit(int amount)
	{
		health-=amount;
	}

	public void heal()
	{
		health = maxHealth;
	}

	public boolean dead()
	{
		return health <= 0;
	}

	public void addExp(int amount)
	{
		exp+=amount;
		while(exp>expToNextLevel)
			levelUp();
	}

	public void levelUp()
	{
		exp-=expToNextLevel;
		expToNextLevel*=2;
		maxHealth+=25;
		level++;
	}

	public void draw(Graphics g)
	{
		g.setColor(Color.BLUE);
		g.fillOval(Util.MAX_R-Util.PLAYER_RADIUS,Util.MAX_R-Util.PLAYER_RADIUS,Util.PLAYER_RADIUS*2,Util.PLAYER_RADIUS*2);
		g.setColor(Color.RED);
		g.fillRect(Util.MAX_R-Util.PLAYER_RADIUS,Util.MAX_R-Util.PLAYER_RADIUS-6,Util.PLAYER_RADIUS*2,5);
		g.setColor(Color.GREEN);
		g.fillRect(Util.MAX_R-Util.PLAYER_RADIUS,Util.MAX_R-Util.PLAYER_RADIUS-6,Util.PLAYER_RADIUS*2*health/maxHealth,5);
		g.setColor(Color.BLUE);
		g.fillRect(Util.MAX_R-Util.PLAYER_RADIUS,Util.MAX_R-Util.PLAYER_RADIUS-1,
			Util.PLAYER_RADIUS*2*exp/expToNextLevel,1);
	}
}