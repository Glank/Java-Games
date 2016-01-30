import java.awt.*;

public class Field
{
	Comet player1;
	Comet player2;
	Comet ball;

	public Field()
	{
		player1 = new Comet(20, 195, 240);
		player1.color = Color.RED;
		player2 = new Comet(20, 445, 240);
		player2.color = Color.BLUE;
		ball = new Comet(5, 320, 240);
		ball.color= Color.BLACK;
	}

	public void draw(Graphics g)
	{
		g.setColor(Color.GREEN);
		g.fillRect(70,65,500,350);
		g.setColor(Color.WHITE);
		g.drawLine(320,65,320,415);
		g.drawOval(270,190,100,100);
		g.setColor(Color.RED);
		g.fillRect(65,190,5,100);
		g.fillRect(570,190,5,100);
		g.setColor(Color.BLACK);
		g.drawRect(70,65,500,350);

		if((player1 != null) && (player2 != null) && (ball != null))
		{
			player1.draw(g);
			player2.draw(g);
			ball.draw(g);
		}
	}

	public void update(boolean p1, boolean p2)
	{
		if((player1 != null) && (player2 != null) && (ball != null))
		{
			if(p1)
			{
				player2.move(player1);
				ball.move(player1);
			}
			if(p2)
			{
				player1.move(player2);
				ball.move(player2);
			}

			player1.update();
			player2.update();

			ball.update();

			if(player1.x-player1.radius < 70)
			{
				player1.x = player1.radius+70;
				player1.xV = 0;
			}
			if(player1.y-player1.radius < 65)
			{
				player1.y = player1.radius+65;
				player1.yV = 0;
			}
			if(player1.x+player1.radius > 570)
			{
				player1.x = 570-player1.radius;
				player1.xV = 0;
			}
			if(player1.y+player1.radius > 415)
			{
				player1.y = 415-player1.radius;
				player1.yV = 0;
			}
			if(player2.x-player2.radius < 70)
			{
				player2.x = player2.radius+70;
				player2.xV = 0;
			}
			if(player2.y-player2.radius < 65)
			{
				player2.y = player2.radius+65;
				player2.yV = 0;
			}
			if(player2.x+player2.radius > 570)
			{
				player2.x = 570-player2.radius;
				player2.xV = 0;
			}
			if(player2.y+player2.radius > 415)
			{
				player2.y = 415-player2.radius;
				player2.yV = 0;
			}
			if(ball.x-ball.radius < 70)
			{
				ball.x = ball.radius+70;
				ball.xV = -ball.xV;
				if((ball.y > 190) && (ball.y < 290))
					score(2);
			}
			if(ball.y-ball.radius < 65)
			{
				ball.y = ball.radius+65;
				ball.yV = -ball.yV;
			}
			if(ball.x+ball.radius > 570)
			{
				ball.x = 570-ball.radius;
				ball.xV = -ball.xV;
				if((ball.y > 190) && (ball.y < 290))
					score(1);
			}
			if(ball.y+ball.radius > 415)
			{
				ball.y = 415-ball.radius;
				ball.yV = -ball.yV;
			}

			ball.decelerate();
			player1.decelerate();
			player2.decelerate();
		}
	}

	public void score(int player)
	{
		player1 = new Comet(20, 195, 240);
		player1.color = Color.RED;
		player2 = new Comet(20, 445, 240);
		player2.color = Color.BLUE;
		ball = new Comet(5, 320, 240);
		ball.color= Color.BLACK;
	}

}