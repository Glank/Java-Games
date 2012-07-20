import java.util.*;

public class SudokuPuzzle
{
	public static char[][] board;
	public SudokuPuzzle()
	{
		board = new char[9][9];
		for (int count1 = 0; count1<9; count1++)
		{
			for (int count2 = 0; count2<9; count2++)
			{
				board[count1][count2] = '-';
			}
		}
	}

	public String toString()
	{
		return(
			"   1  2  3  4  5  6  7  8  9\n\n" +
			"1  " + board[0][0] + "  " + board[0][1] + "  " + board[0][2] + "||" + board[0][3] + "  " + board[0][4] + "  " + board[0][5] + "||" + board[0][6] + "  " + board[0][7] + "  " + board[0][8] + "\n" +
			"   -------------------------\n" +
			"2  " + board[1][0] + "  " + board[1][1] + "  " + board[1][2] + "||" + board[1][3] + "  " + board[1][4] + "  " + board[1][5] + "||" + board[1][6] + "  " + board[1][7] + "  " + board[1][8] + "\n" +
			"   -------------------------\n" +
			"3  " + board[2][0] + "  " + board[2][1] + "  " + board[2][2] + "||" + board[2][3] + "  " + board[2][4] + "  " + board[2][5] + "||" + board[2][6] + "  " + board[2][7] + "  " + board[2][8] + "\n" +
			"   =========================\n" +
			"4  " + board[3][0] + "  " + board[3][1] + "  " + board[3][2] + "||" + board[3][3] + "  " + board[3][4] + "  " + board[3][5] + "||" + board[3][6] + "  " + board[3][7] + "  " + board[3][8] + "\n" +
			"   -------------------------\n" +
			"5  " + board[4][0] + "  " + board[4][1] + "  " + board[4][2] + "||" + board[4][3] + "  " + board[4][4] + "  " + board[4][5] + "||" + board[4][6] + "  " + board[4][7] + "  " + board[4][8] + "\n" +
			"   -------------------------\n" +
			"6  " + board[5][0] + "  " + board[5][1] + "  " + board[5][2] + "||" + board[5][3] + "  " + board[5][4] + "  " + board[5][5] + "||" + board[5][6] + "  " + board[5][7] + "  " + board[5][8] + "\n" +
			"   =========================\n" +
			"7  " + board[6][0] + "  " + board[6][1] + "  " + board[6][2] + "||" + board[6][3] + "  " + board[6][4] + "  " + board[6][5] + "||" + board[6][6] + "  " + board[6][7] + "  " + board[6][8] + "\n" +
			"   -------------------------\n" +
			"8  " + board[7][0] + "  " + board[7][1] + "  " + board[7][2] + "||" + board[7][3] + "  " + board[7][4] + "  " + board[7][5] + "||" + board[7][6] + "  " + board[7][7] + "  " + board[7][8] + "\n" +
			"   -------------------------\n" +
			"9  " + board[8][0] + "  " + board[8][1] + "  " + board[8][2] + "||" + board[8][3] + "  " + board[8][4] + "  " + board[8][5] + "||" + board[8][6] + "  " + board[8][7] + "  " + board[8][8]
			);
	}

	public void set(int row, int col, char num)
	{
		board[row][col] = num;
	}

	public char get(int row, int col)
	{
		return (board[row][col]);
	}

	public char find1(int row, int col)
	{
		boolean[] is = new boolean[9];
		int count, number, answers, sectionX, sectionY;
		sectionX = row/3;
		sectionY = col/3;
		answers = 0;
		number = 0;
		char num;
		boolean isNum, notThere;
		for (count = 0; count < 9; count++)
			is[count] = true;
		if (board[row][col] == '-')
		{
			for (count = 0; count < 9; count++)
			{
				switch (board[row][count])
				{
					case '1':
						is[0] = false;
						break;
					case '2':
						is[1] = false;
						break;
					case '3':
						is[2] = false;
						break;
					case '4':
						is[3] = false;
						break;
					case '5':
						is[4] = false;
						break;
					case '6':
						is[5] = false;
						break;
					case '7':
						is[6] = false;
						break;
					case '8':
						is[7] = false;
						break;
					case '9':
						is[8] = false;
						break;
					default:
				}
			}
			for (count = 0; count < 9; count++)
			{
				switch (board[count][col])
				{
					case '1':
						is[0] = false;
						break;
					case '2':
						is[1] = false;
						break;
					case '3':
						is[2] = false;
						break;
					case '4':
						is[3] = false;
						break;
					case '5':
						is[4] = false;
						break;
					case '6':
						is[5] = false;
						break;
					case '7':
						is[6] = false;
						break;
					case '8':
						is[7] = false;
						break;
					case '9':
						is[8] = false;
						break;
					default:
				}
			}
			for (int count1 = sectionX*3; count1 < sectionX*3 + 3; count1++)
			{
				for (int count2 = sectionY*3; count2 < sectionY*3 + 3; count2++)
				{
					switch (board[count1][count2])
					{
						case '1':
							is[0] = false;
							break;
						case '2':
							is[1] = false;
							break;
						case '3':
							is[2] = false;
							break;
						case '4':
							is[3] = false;
							break;
						case '5':
							is[4] = false;
							break;
						case '6':
							is[5] = false;
							break;
						case '7':
							is[6] = false;
							break;
						case '8':
							is[7] = false;
							break;
						case '9':
							is[8] = false;
							break;
						default:
					}
				}
			}
			for (count = 0; count < 9; count++)
			{
				if (is[count])
				{
					number = count+1;

					answers++;
				}
			}
			if (answers == 1)
			{
				System.out.println("I found: " + ("" + number).charAt(0));
				return(("" + number).charAt(0));
			}
			else
			{
				/*int timesNotThere;
				for (count = 49; count <= 57; count++)
				{
					num = (char)count;
					timesNotThere = 0;
					for (int count1 = sectionX*3; count1 < sectionX*3 + 3; count1++)
					{
						for (int count2 = sectionY*3; count2 < sectionY*3 + 3; count2++)
						{
							notThere = false;
							if (board[count1][count2]!='-')
								notThere = true;
							else
							{
								for (int count3 = 0; count3 < 9; count3++)
								{
									if (board[count1][count3]==num)
										notThere = true;
								}
								for (int count3 = 0; count3 < 9; count3++)
								{
									if (board[count3][count2]==num)
										notThere = true;
								}
								for (int count4 = sectionX*3; count4 < sectionX*3 + 3; count4++)
								{
									for (int count5 = sectionY*3; count5 < sectionY*3 + 3; count5++)
									{
										if (board[count4][count5]==num)
											notThere = true;
									}
								}
							}
							if (notThere)
								timesNotThere++;
						}
					}
					if (timesNotThere == 7)
					{
						notThere = false;
						for (int count3 = 0; count3 < 9; count3++)
						{
							if (board[row][count3]==num)
								notThere = true;
						}
						for (int count3 = 0; count3 < 9; count3++)
						{
							if (board[count3][col]==num)
								notThere = true;
						}
						for (int count1 = sectionX*3; count1 < sectionX*3 + 3; count1++)
						{
							for (int count2 = sectionY*3; count2 < sectionY*3 + 3; count2++)
							{
								if (board[count1][count2]==num)
									notThere = true;
							}
						}
						if (!notThere)
							return(num);
					}
				}*/
			}
		}
		else
		{
			return (board[row][col]);
		}
		return (board[row][col]);
	}

	public boolean areErrors()
	{
		boolean error = false;
		char number;
		int numberOfNumber;
		for(int num = 49; (num <= 57) && !error; num++)
		{
			number = (char)num;
			numberOfNumber = 0;
			for (int count1 = 0; (count1 < 9) && !error; count1++)
			{


			}
			for (int count2 = 0; (count2 < 9) && !error; count2++)
			{
				if
}