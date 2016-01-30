import javax.swing.*;
import java.awt.*;

public class PlayTetris extends JFrame
{
	public PlayTetris()
	{
		getContentPane().setLayout(new FlowLayout());
		add(new TetrisComponent(10,20));
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args)
	{
		new PlayTetris();
	}
}