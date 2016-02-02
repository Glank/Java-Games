import java.util.*;
import java.awt.*;

public class TetrisGrid extends BlockGrid
{
	private Vector<TetrisBlock> lockedBlocks = new Vector();
	private TetrisBlock workingBlock;
	private int points = 0;

	public TetrisGrid(int width, int height)
	{
		super(width, height);
	}

	public void update()
	{
		if(workingBlock == null)
			setWorkingBlock(TetrisBlock.getRandomBlock().setLocation(getWidth()/2, 0));
	}

	public void draw(Graphics g)
	{
		clear();
		if(workingBlock != null)
			workingBlock.draw(this);
		for(int i = 0; i < lockedBlocks.size(); i++)
		{
			lockedBlocks.get(i).draw(this);
		}
		if(g != null)
			super.draw(g);

		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.BLACK);
		g2.drawString("Points: " + points, 5, 12+5);
		if(isFilled())
		{
			g2.drawString("You Lose", getGraphicsWidth()/2-20, getGraphicsHeight()/2-6);
		}
	}

	/**
	* Sets the current working block.
	* @return True if the block is set, false if the block will not fit.
	*/
	public boolean setWorkingBlock(TetrisBlock block)
	{
		if(canFit(block))
		{
			workingBlock = block;
			return true;
		}
		return false;
	}

	public void lockWorkingBlock()
	{
		points++;
		if(workingBlock != null)
			lockedBlocks.addAll(workingBlock.getSingleBlocks());
		workingBlock = null;
		rowCheck();
	}

	private boolean canFit(TetrisBlock block)
	{
		if((block.getX() < 0) || (block.getX()+block.getWidth() > getWidth()))
			return false;

		if(block.getY() >= getHeight())
			return false;

		for(int i = 0; i < lockedBlocks.size(); i++)
		{
			if(lockedBlocks.get(i).overlaps(block))
				return false;
		}

		return true;
	}

	public void moveDown()
	{
		if(workingBlock == null)
			return;

		TetrisBlock newBlock = workingBlock.moveDown();
		if(canFit(newBlock))
			workingBlock = newBlock;
		else
		{
			lockWorkingBlock();
		}
	}

	public void moveLeft()
	{
		if(workingBlock == null)
			return;

		TetrisBlock newBlock = workingBlock.moveLeft();
		if(canFit(newBlock))
			workingBlock = newBlock;
	}

	public void moveRight()
	{
		if(workingBlock == null)
			return;

		TetrisBlock newBlock = workingBlock.moveRight();
		if(canFit(newBlock))
			workingBlock = newBlock;
	}

	public void turnLeft()
	{
		if(workingBlock == null)
			return;

		TetrisBlock newBlock = workingBlock.turnLeft();
		if(canFit(newBlock))
			workingBlock = newBlock;
	}

	public void turnRight()
	{
		if(workingBlock == null)
			return;

		TetrisBlock newBlock = workingBlock.turnRight();
		if(canFit(newBlock))
			workingBlock = newBlock;
	}

	public boolean isFilled()
	{
		for(int i = 0; i < lockedBlocks.size(); i++)
		{
			if(lockedBlocks.get(i).getY() == 0)
				return true;
		}
		return false;
	}

	public boolean isRowFilled(int row)
	{
		int blocksInRow = 0;
		for(int i = 0; i < lockedBlocks.size(); i++)
		{
			if(lockedBlocks.get(i).getY() == row)
				blocksInRow++;
		}
		return blocksInRow == getWidth();
	}

	public void clearRow(int row)
	{
		for(int i = 0; i < lockedBlocks.size(); i++)
		{
			if(lockedBlocks.get(i).getY() == row)
			{
				lockedBlocks.remove(i);
				i--;
			}
			else if(lockedBlocks.get(i).getY() < row)
			{
				lockedBlocks.add(i,lockedBlocks.get(i).moveDown());
				lockedBlocks.remove(i+1);
			}
		}
	}

	public void rowCheck()
	{
		for(int row = 1; row < getHeight(); row++)
		{
			if(isRowFilled(row))
				clearRow(row);
		}
	}

	public static void main(String[] args)
	{
		TetrisGrid grid = new TetrisGrid(10,20);
		System.out.println(grid.setWorkingBlock(TetrisBlock.RIGHT_L.setLocation(10,10)));
		Scanner s = new Scanner(System.in);
		while(true)
		{
			grid.update();
			grid.draw(null);
			System.out.println(grid);
			String line = s.nextLine();
			if(line.equals(""))
				grid.moveDown();
			else if(line.equals("<"))
				grid.moveLeft();
			else if(line.equals(">"))
				grid.moveRight();
			else if(line.equals("("))
				grid.turnLeft();
			else if(line.equals(")"))
				grid.turnRight();
		}
	}
}
