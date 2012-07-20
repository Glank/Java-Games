import java.io.Serializable;

public class PongUpdate implements Serializable
{
	public boolean serverUpdate = false;
	public double ballX = 0, ballY = 0;
	public int playerY = 0, player1p = 0, player2p = 0;
	public PongUpdate(double bX, double bY, int pY, int p1p, int p2p)
	{
		ballX = bX;
		ballY = bY;
		playerY = pY;
		player1p = p1p;
		player2p = p2p;
	}

	public PongUpdate(int pY)
	{
		serverUpdate = true;
		playerY = pY;
	}
}