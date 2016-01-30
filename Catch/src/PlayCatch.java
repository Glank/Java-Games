import javax.swing.*;
import java.awt.*;

public class PlayCatch extends JFrame
{
	public PlayCatch()
	{
		getContentPane().setLayout(new FlowLayout());
		add(new CatchComponent());
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args)
	{
		new PlayCatch();
	}
}
