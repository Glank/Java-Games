import java.util.*;
import java.io.*;

public class SudokuSolver
{
	public static void main(String[] args)
	{
		int row = 0, col = 0;
		char num;
		String continues, rowString, colString;
		boolean repeatIn, keepFinding, correctGuess;
		Scanner kb = new Scanner(System.in);
		SudokuPuzzle puzzle = new SudokuPuzzle();
		SudokuPuzzle guess;
		puzzle.set(0, 1, ('6'));
		puzzle.set(0, 3, ('1'));
		puzzle.set(0, 5, ('4'));
		puzzle.set(0, 7, ('5'));
		puzzle.set(1, 2, ('8'));
		puzzle.set(1, 3, ('3'));
		puzzle.set(1, 5, ('5'));
		puzzle.set(1, 6, ('6'));
		puzzle.set(2, 0, ('2'));
		puzzle.set(2, 8, ('1'));
		puzzle.set(3, 0, ('8'));
		puzzle.set(3, 3, ('4'));
		puzzle.set(3, 5, ('7'));
		puzzle.set(3, 8, ('6'));
		puzzle.set(4, 2, ('6'));
		puzzle.set(4, 6, ('3'));
		puzzle.set(5, 0, ('7'));
		puzzle.set(5, 3, ('9'));
		puzzle.set(5, 5, ('1'));
		puzzle.set(5, 8, ('4'));
		puzzle.set(6, 0, ('5'));
		puzzle.set(6, 8, ('2'));
		puzzle.set(7, 2, ('7'));
		puzzle.set(7, 3, ('2'));
		puzzle.set(7, 5, ('6'));
		puzzle.set(7, 6, ('9'));
		puzzle.set(8, 1, ('4'));
		puzzle.set(8, 3, ('5'));
		puzzle.set(8, 5, ('8'));
		puzzle.set(8, 7, ('7'));
		System.out.println(puzzle);
		do
		{
			do
			{
				repeatIn = false;
				System.out.print("Row: ");
				rowString = kb.nextLine();
				System.out.print("Collumn: ");
				colString = kb.nextLine();
				if (!(((int)rowString.charAt(0) <= 58) && ((int)rowString.charAt(0) >= 49) && ((int)colString.charAt(0) <= 58) && ((int)colString.charAt(0) >= 49)))
				{
					System.out.println("Bad Input. Only input integers 1-9");
					repeatIn = true;
				}
				else
				{
					row = (int)(rowString.charAt(0))-48;
					col = (int)(colString.charAt(0))-48;
				}
			}while(repeatIn);
			System.out.print("Character at (" + row + ", " + col + "): ");
			num = (kb.nextLine()).charAt(0);
			puzzle.set(row-1, col-1, (num));
			do
			{
				keepFinding = false;
				for (int count1 = 0; count1 < 9; count1++)
				{
					for (int count2 = 0; count2 < 9; count2++)
						if (puzzle.get(count1, count2) == '-')
						{
							puzzle.set(count1, count2, puzzle.find1(count1, count2));
							if (puzzle.find1(count1, count2) != '-')
								keepFinding = true;
						}
				}
			}while (keepFinding);

			/*for (int testRow = 0; testRow < 9; testRow++)
			{
				for (int testCol = 0; testCol < 9; testCol++)
				{
					if (puzzle.get(testRow,testCol) == '-')
					{
						for (int count = 49; count <= 57; count++)
						{
							guess = puzzle;
							guess.set(testRow, testCol, (char)count);
							do
							{
								keepFinding = false;
								for (int count1 = 0; count1 < 9; count1++)
								{
									for (int count2 = 0; count2 < 9; count2++)
										if (guess.get(count1, count2) == '-')
										{
											guess.set(count1, count2, guess.find1(count1, count2));
											if (guess.find1(count1, count2) != '-')
												keepFinding = true;
										}
								}
							}while (keepFinding);
							correctGuess = true;
							for (int count1 = 0; (count1 < 9) && correctGuess; count1++)
							{
								for (int count2 = 0; (count2 < 9) && correctGuess; count2++)
								{
									if (guess.get(count1, count2) == '-')
										correctGuess = false;
								}
							}
						}
					}
				}
			}*/

			System.out.println(puzzle);
		}while (1 == 1);
	}
}