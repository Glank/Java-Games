import javax.swing.*;
import java.awt.*;

public class PlayCraps extends JFrame
{
	public PlayCraps()
	{
		getContentPane().setLayout(new FlowLayout());
		add(new CrapsComponent());
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args)
	{
		new PlayCraps();
	}
}