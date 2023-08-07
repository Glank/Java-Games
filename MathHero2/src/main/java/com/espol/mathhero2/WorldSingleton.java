package com.espol.mathhero2;
import com.espol.mathhero2.SubtractionEnemyFactory;
import com.espol.mathhero2.DivisionEnemyFactory;
import com.espol.mathhero2.AdditionEnemyFactory;
import com.espol.mathhero2.EnemyFactory;
import com.espol.mathhero2.MultiplicationEnemyFactory;

import java.awt.*;
import java.util.*;

public class WorldSingleton
{
	public Player player;
	public ArrayList<Level> levels;
	public int level = 0;
	public boolean win = false;
	public boolean lose = false;
        private static WorldSingleton instance = null;
        
        public static WorldSingleton getInstance() {
            if (instance == null) {  
                // add logic to create levels and player  
                instance = new WorldSingleton();
            }
            return instance;
        }
        
	private WorldSingleton()
	{
                
		this.player = new Player();
		this.levels = new ArrayList<>();
                
                // Creamos las fábricas de enemigos
            EnemyFactory additionFactory = new AdditionEnemyFactory();
            EnemyFactory multiplicationFactory = new MultiplicationEnemyFactory();
            EnemyFactory subtractionFactory = new SubtractionEnemyFactory();  
            EnemyFactory divisionFactory = new DivisionEnemyFactory();  
             
                //Level 1
		Vector<Enemy> l1e = new Vector();
		for(int i = 0; i < 10; i ++) l1e.add(additionFactory.createSmallEnemy());
		Level l1 = new Level(2,1234567,l1e);

		//Level 2
		Vector<Enemy> l2e = new Vector();
		for(int i = 0; i < 8; i ++) l2e.add(additionFactory.createSmallEnemy());
		for(int i = 0; i < 5; i ++) l2e.add(multiplicationFactory.createSmallEnemy());
		Level l2 = new Level(2,6394658,l2e);

		//Level 3
		Vector<Enemy> l3e = new Vector();
		for(int i = 0; i < 5; i ++) l3e.add(additionFactory.createSmallEnemy());
		for(int i = 0; i < 8; i ++) l3e.add(multiplicationFactory.createSmallEnemy());
		Level l3 = new Level(3,1563826,l3e);

		//Level 4
		Vector<Enemy> l4e = new Vector();
		for(int i = 0; i < 7; i ++) l4e.add(additionFactory.createSmallEnemy());
		for(int i = 0; i < 7; i ++) l4e.add(multiplicationFactory.createSmallEnemy());
		for(int i = 0; i < 3; i ++) l4e.add(subtractionFactory.createSmallEnemy());
		Level l4 = new Level(3,1927462,l4e);

		//Level 5
		Vector<Enemy> l5e = new Vector();
		for(int i = 0; i < 7; i ++) l5e.add(additionFactory.createSmallEnemy());
		for(int i = 0; i < 7; i ++) l5e.add(multiplicationFactory.createSmallEnemy());
		for(int i = 0; i < 5; i ++) l5e.add(subtractionFactory.createSmallEnemy());
		for(int i = 0; i < 3; i ++) l5e.add(divisionFactory.createSmallEnemy());
		Level l5 = new Level(3,3728465,l5e);

		//Level 6
		Vector<Enemy> l6e = new Vector();
		for(int i = 0; i < 7; i ++) l6e.add(additionFactory.createSmallEnemy());
		for(int i = 0; i < 7; i ++) l6e.add(multiplicationFactory.createSmallEnemy());
		for(int i = 0; i < 7; i ++) l6e.add(subtractionFactory.createSmallEnemy());
		for(int i = 0; i < 5; i ++) l6e.add(divisionFactory.createSmallEnemy());
		Level l6 = new Level(3,7384920,l6e);

		//Level 7
		Vector<Enemy> l7e = new Vector();
		for(int i = 0; i < 5; i ++) l7e.add(additionFactory.createBigEnemy());
		for(int i = 0; i < 7; i ++) l7e.add(multiplicationFactory.createSmallEnemy());
		for(int i = 0; i < 7; i ++) l7e.add(subtractionFactory.createSmallEnemy());
		for(int i = 0; i < 7; i ++) l7e.add(divisionFactory.createSmallEnemy());
		Level l7 = new Level(3,6374198,l7e);

		//Level 8
		Vector<Enemy> l8e = new Vector();
		for(int i = 0; i < 5; i ++) l8e.add(additionFactory.createBigEnemy());
		for(int i = 0; i < 1; i ++) l8e.add(multiplicationFactory.createBigEnemy());
		for(int i = 0; i < 7; i ++) l8e.add(subtractionFactory.createSmallEnemy());
		for(int i = 0; i < 7; i ++) l8e.add(divisionFactory.createSmallEnemy());
		Level l8 = new Level(3,1738295,l8e);
                levels.add(l1);
                levels.add(l2);
                levels.add(l3);
                levels.add(l4);
                levels.add(l5);
                levels.add(l6);
                levels.add(l7);
                levels.add(l8);
		
                
	}

	public void tryKey(int key)
	{
		for(int i = 0; i < levels.size(); i++)
		{
			if(levels.get(i).key==key)
			{  
				level = i;
				i = levels.size();
			}
		}
	}

	public Level getLevel()
	{
		return levels.get(level);
	}

	public void update()
	{
		if(!win && !lose)
		{
			getLevel().update();
			player.hit(getLevel().getHitting());
			if(player.dead())
				lose = true;
			if(getLevel().finished()&& !lose)
			{
				player.heal();
				level++;
				if(level==levels.size())
				{
					win = true;
					level--;
				}
			}
		}
	}

	public void draw(Graphics g)
	{
		getLevel().drawEnemies(g);
		player.draw(g);
		g.setColor(Color.BLACK);
		g.drawString("Level: "+(level+1),5,15);
		g.drawString("Key: "+getLevel().key,Util.MAX_R*2-90,15);
		if(win)
		{
			g.setColor(Color.GREEN);
			g.setFont(new Font("Verdana",Font.BOLD,14));
			g.drawString("You WIN!",140,150);
		}
		if(lose)
		{
			g.setColor(Color.RED);
			g.setFont(new Font("Verdana",Font.BOLD,14));
			g.drawString("You lose.",140,150);
		}
	}

	
}