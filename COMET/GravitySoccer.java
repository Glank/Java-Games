import gameutil.*;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class GravitySoccer extends ListeningGameComponent
{
	Field field = new Field();

	public GravitySoccer()
	{
		super(640,480);
	}

	public void draw(Graphics g)
	{
		if(field != null)
			field.draw(g);
	}

	public void update()
	{
		if(field != null)
		{
			if(isKeyPressed("UP"))
				field.player2.move(Direction.NORTH);
			if(isKeyPressed("DOWN"))
				field.player2.move(Direction.SOUTH);
			if(isKeyPressed("RIGHT"))
				field.player2.move(Direction.EAST);
			if(isKeyPressed("LEFT"))
				field.player2.move(Direction.WEST);

			if(isKeyPressed("W"))
				field.player1.move(Direction.NORTH);
			if(isKeyPressed("S"))
				field.player1.move(Direction.SOUTH);
			if(isKeyPressed("D"))
				field.player1.move(Direction.EAST);
			if(isKeyPressed("A"))
				field.player1.move(Direction.WEST);

			if(isKeyPressed("H"))
			{
				resetKeys();
				JOptionPane.showMessageDialog(this, "-1st Player-\nW/A/S/D - Move\nSpace - Turn on gravitational pull\n\n-2nd Player-\nArrows - Move\nNumpad-0 - Turn on gravitational pull\n\nH: Help Menu");
			}

			field.update(isKeyPressed("SPACE"), isKeyPressed("Numpad-0"));
		}
	}

	public static void main(String[] args)
	{
		JOptionPane.showMessageDialog((new GravitySoccer()).makeTestWindow(), "Press H for help.");
	}
}