import java.awt.Color;
import java.util.Vector;

public class TetrisBlock
{
	public static final TetrisBlock LONG = new TetrisBlock(
		new boolean[][]{{true, true, true, true}}, Color.RED);

	public static final TetrisBlock LEFT_L = new TetrisBlock(new boolean[][]{
		{true, true, true}, {true, false, false}}, Color.GREEN);

	public static final TetrisBlock TEE = new TetrisBlock(
		new boolean[][]{{true, true, true}, {false, true, false}}, Color.BLUE);

	public static final TetrisBlock RIGHT_L = new TetrisBlock(new boolean[][]{
		{true, true, true}, {false, false, true}}, Color.CYAN);

	public static final TetrisBlock SQUARE = new TetrisBlock(new boolean[][]{
		{true, true}, {true, true}}, Color.BLACK);

	private boolean[][] blocks;
	private Color color;

	// location of the bottum left hand corner.
	private int x, y;

	private TetrisBlock(boolean[][] blocks, Color color)
	{
		this.blocks = blocks;
		this.color = color;
	}

	private TetrisBlock(boolean[][] blocks, Color color, int x, int y)
	{
		this(blocks, color);
		this.x = x;
		this.y = y;
	}

	public static TetrisBlock getRandomBlock()
	{
		switch((int)(Math.random()*5))
		{
			case 0: return LONG;
			case 1: return LEFT_L;
			case 2: return TEE;
			case 3: return RIGHT_L;
			case 4: return SQUARE;
		}
		return null;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public int getWidth()
	{
		return blocks.length;
	}

	public int getHeight()
	{
		return blocks[0].length;
	}

	public TetrisBlock setLocation(int x, int y)
	{
		return new TetrisBlock(blocks, color, x, y);
	}

	public TetrisBlock moveDown()
	{
		return new TetrisBlock(blocks, color, x, y+1);
	}

	public TetrisBlock moveLeft()
	{
		return new TetrisBlock(blocks, color, x-1, y);
	}

	public TetrisBlock moveRight()
	{
		return new TetrisBlock(blocks, color, x+1, y);
	}

	public boolean overlaps(int x, int y)
	{
		for(int i = 0; i < blocks.length; i++)
		{
			for(int j = 0; j < blocks[0].length; j++)
			{
				if(blocks[i][j])
				{
					if((this.x+i == x) && (this.y-blocks[0].length+1+j == y))
						return true;
				}
			}
		}
		return false;
	}

	public boolean overlaps(TetrisBlock other)
	{
		for(int i = 0; i < blocks.length; i++)
		{
			for(int j = 0; j < blocks[0].length; j++)
			{
				if(blocks[i][j])
				{
					if(other.overlaps(x+i, y-blocks[0].length+1+j))
						return true;
				}
			}
		}
		return false;
	}

	public TetrisBlock turnRight()
	{
		boolean[][] newBlocks = new boolean[blocks[0].length][blocks.length];
		for(int i = 0; i < blocks.length; i++)
		{
			for(int j = 0; j < blocks[0].length; j++)
			{
				newBlocks[blocks[0].length-1-j][i] = blocks[i][j];
			}
		}
		return new TetrisBlock(newBlocks, color, x, y);
	}

	public TetrisBlock turnLeft()
	{
		boolean[][] newBlocks = new boolean[blocks[0].length][blocks.length];
		for(int i = 0; i < blocks.length; i++)
		{
			for(int j = 0; j < blocks[0].length; j++)
			{
				newBlocks[j][blocks.length-1-i] = blocks[i][j];
			}
		}
		return new TetrisBlock(newBlocks, color, x, y);
	}

	public String toString()
	{
		String ret = "";
		for(int j = 0; j < blocks[0].length; j++)
		{
			ret+=(j!=0)?"\n":"";
			for(int i = 0; i < blocks.length; i++)
			{
				if(blocks[i][j])
					ret+="#";
				else
					ret+=" ";
			}
		}
		return ret;
	}

	public static TetrisBlock getSingleBlock(int x, int y, Color color)
	{
		return new TetrisBlock(new boolean[][]{{true}}, color, x, y);
	}

	public Vector<TetrisBlock> getSingleBlocks()
	{
		Vector<TetrisBlock> parts = new Vector();
		for(int i = 0; i < blocks.length; i++)
		{
			for(int j = 0; j < blocks[0].length; j++)
			{
				if(blocks[i][j])
					parts.add(getSingleBlock(x+i,y-blocks[0].length+1+j, color));
			}
		}
		return parts;
	}

	public void draw(BlockGrid grid)
	{
		grid.setColor(color);
		for(int i = 0; i < blocks.length; i++)
		{
			for(int j = 0; j < blocks[0].length; j++)
			{
				if(blocks[i][j])
					grid.drawSquare(x+i,y-blocks[0].length+1+j);
			}
		}
	}

	public static void main(String[] args)
	{
		TetrisBlock a = TetrisBlock.SQUARE.setLocation(2,2);
		TetrisBlock b = TetrisBlock.SQUARE.setLocation(5,2);
		System.out.println(a.overlaps(b));
	}
}