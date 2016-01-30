import java.util.*;

import java.awt.*;

public class Level
{
	protected Vector<Enemy> enemies;
	protected Vector<Enemy> actives;
	protected int active;
	public int key;

	public Level(int active, int key, Vector<Enemy> enemies)
	{
		this.active = active;
		this.key = key;
		this.enemies = enemies;
		actives = new Vector();
	}

	public int process(int solution)
	{
		int killed = 0;
		for(int i = 0; i < actives.size(); i++)
		{
			if(actives.get(i).getSolution()==solution)
			{
				actives.get(i).die();
				killed++;
			}
		}
		return killed;
	}

	public int getHitting()
	{
		int hitting = 0;
		for(int i = 0; i < actives.size(); i++)
			if(actives.get(i).hitting())
				hitting++;
		return hitting;
	}

	public boolean finished()
	{
		return actives.size()==0&&enemies.size()==0;
	}

	public void update()
	{
		if(actives.size()<active&&enemies.size()>0&&Math.random()<Util.ENEMY_FREQUENCY*active)
		{
			actives.add(enemies.remove((int)(Math.random()*enemies.size())));
		}
		for(int i = 0; i < actives.size(); i++)
		{
			actives.get(i).update();
			if(actives.get(i).dead())
			{
				actives.remove(i);
				i--;
			}
		}
	}

	public void drawEnemies(Graphics g)
	{
		for(int i = 0; i < actives.size(); i++)
		{
			actives.get(i).draw(g);
		}
	}
}