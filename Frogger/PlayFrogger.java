import javax.swing.*;
import java.awt.*;

public class PlayFrogger extends JFrame
{
	public PlayFrogger()
	{
		getContentPane().setLayout(new FlowLayout());
		add(new FroggerComponent());
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args)
	{
		new PlayFrogger();
	}
}