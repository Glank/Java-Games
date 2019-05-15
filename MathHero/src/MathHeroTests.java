/**
*author: @Sean LaFleur
*
*provides testing for the Addition, BigAddition, BigMultiplication,
*Division, Subtraction, Player, Enemy, and Level classes of the 
*MathHero game
*/
import static org.junit.Assert.fail;

import java.util.Vector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static java.util.concurrent.TimeUnit.SECONDS;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MathHeroTests {

	@Rule public Timeout globalTimeout = new Timeout(2, SECONDS);

	@Test public void addition1() {
		Addition add = new Addition();

		assertTrue("Actual solution for Addition does not equal expected",
				(add.getNum1() + add.getNum2()) == add.getSolution());
		assertTrue("Actual problem for Addition does not equal expected",
				(add.getNum1()+"+"+add.getNum2()).equals(add.getProblem()));
	}

	@Test public void bigAddition1() {
		BigAddition add = new BigAddition();

		assertTrue("Actual solution for BigAddition does not equal expected",
				(add.getNum1() + add.getNum2()) == add.getSolution());
		assertTrue("Actual problem for BigAddition does not equal expected",
				(add.getNum1()+"+"+add.getNum2()).equals(add.getProblem()));
	}

	@Test public void multiplication1() {
		Multiplication mult = new Multiplication();

		assertTrue("Actual solution for Multiplication does not equal expected",
				(mult.getNum1()*mult.getNum2()) == mult.getSolution());
		assertTrue("Actual problem for Multiplication does not equal expected",
				(mult.getNum1()+"*"+mult.getNum2()).equals(mult.getProblem()));
	}

	@Test public void bigMultiplication1() {
		BigMultiplication bg = new BigMultiplication();

		assertTrue("Actual solution for BigMultiplication does not equal expected",
				(bg.getNum1()*bg.getNum2()) == bg.getSolution());
		assertTrue("Actual problem for BigMultiplication does not equal expected",
				(bg.getNum1()+"*"+bg.getNum2()).equals(bg.getProblem()));
	}

	@Test public void division1() {
		Division div = new Division();

		assertTrue("Actual solution for Division does not equal expected",
				div.getNum1() == div.getSolution());
		assertTrue("Actual problem for Division does not equal expected",
				((div.getNum1()*div.getNum2())+ "/" + div.getNum2()).equals(div.getProblem()));
	}

	@Test public void subtraction1() {
		Subtraction sub = new Subtraction();

		assertTrue("Actual solution for Subtraction does not equal expected",
				sub.getNum1() == sub.getSolution());
		assertTrue("Actual problem for Subtraction does not equal expected",
				((sub.getNum1()+sub.getNum2())+ "-" + sub.getNum2()).equals(sub.getProblem()));
	}

	@Test public void player1() {
		Player player = new Player();

		player.hit(10);

		assertEquals("Health is not 90 after hit of 10", 90, player.getHealth());
		assertFalse("Player is dead after health is above 0", player.dead());
	}

	@Test public void player2() {
		Player player = new Player();

		player.hit(100);

		assertEquals("Health is not 0 after hit of 100", 0, player.getHealth());
		assertTrue("Player is not dead after health is 0", player.dead());
	}

	@Test public void player3() {
		Player player = new Player();

		player.addExp(13);

		assertEquals("Experience was not 2", 3, player.getExp());
		assertEquals("Level was not 2", 2, player.getLevel());
	}

	@Test public void player4() {
		Player player = new Player();

		player.addExp(9);

		assertEquals("Experience was not 9", 9, player.getExp());
		assertEquals("Level was not 1", 1, player.getLevel());
	}

	@Test public void player5() {
		Player player = new Player();

		player.addExp(12);
		player.heal();

		assertEquals("Health was not 125", 125, player.getHealth());
	}

	@Test public void player6() {
		Player player = new Player();

		player.addExp(30);

		assertEquals("Experience was not 0", 0, player.getExp());
		assertEquals("Level was not 3", 3, player.getLevel());
	}

	@Test public void player7() {
		Player player = new Player();

		player.addExp(30);
		player.heal();

		assertEquals("Health is not 150", 150, player.getHealth());
	}

	@Test public void enemy1() {
		Addition add = new Addition();

		assertFalse("dying1 is not false", add.getDying1());
	}

	@Test public void enemy2() {
		Addition add = new Addition();

		add.die();

		assertTrue("dying1 is not true", add.getDying1());
		assertFalse("dying2 is not false", add.getDying2());
	}

	@Test public void enemy3() {
		Addition add = new Addition();

		add.die();

		for(int i = 0; i < 31; i ++) {
			add.update();
		}

		assertTrue("dying2 is false", add.getDying2());
	}

	@Test public void enemy4() {
		Addition add = new Addition();

		for(int i = 0; i < 392; i ++) {
			add.update();
		}

		assertTrue("Add is not hitting the player", add.hitting());
	}

	@Test public void enemy5() {
		Addition add = new Addition();

		assertEquals("Max_R is not 250", 250, add.getR(), .01);
	}

	@Test public void enemy6() {
		Addition add = new Addition();

		add.setT(0);

		assertEquals("X was not 500 t = 0", 500, add.x());
		assertEquals("Y was not 250 with t = 0", 250, add.y());
	}

	@Test public void enemy7() {
		Addition add = new Addition();

		add.setT(0);

		assertEquals("arrowX1 was not 265 with t = 0", 265, add.arrowX1());
		assertEquals("arrowX2 was not 260 with t = 0", 260, add.arrowX2());
	}

	@Test public void enemy8() {
		Addition add = new Addition();

		add.setT(0);

		assertEquals("arrowY1 was not 265 with t = 0", 250, add.arrowY1());
		assertEquals("arrowY2 was not 260 with t = 0", 250, add.arrowY2());
	}

	@Test public void level1() {
		Vector<Enemy> vect= new Vector<>();
		Addition add = new Addition();
		vect.add(add);

		Level lvl = new Level(1, 1, vect);
		for(int i = 0; i < 1000; i ++) {
			lvl.update();
		}

		assertEquals("1 enemy was not killed", 1, lvl.process(add.getSolution()));
	}

	@Test public void level2() {
		Vector<Enemy> vect = new Vector<>();
		Addition add = new Addition();
		add.setDead(true);
		Division div = new Division();
		div.setDead(true);
		vect.add(add);
		vect.add(div);

		Level lvl = new Level(2, 1, vect);

		for(int i = 0; i < 1000; i ++) {
			lvl.update();
		}

		assertTrue("Not all enemies were killed", lvl.finished());
	}

	@Test public void level3() {
		Vector<Enemy> vect = new Vector<>();
		Addition add = new Addition();
		add.setDead(true);
		Division div = new Division();
		vect.add(add);
		vect.add(div);

		Level lvl = new Level(2, 1, vect);

		lvl.update();

		assertFalse("All Enemies were Killed", lvl.finished());
	}

	@Test public void level4() {
		Vector<Enemy> vect= new Vector<>();
		Addition add = new Addition();

		for(int i = 0; i < 391; i ++) {
			add.update();
		}

		vect.add(add);

		Level lvl = new Level(1, 1, vect);

		for(int i = 0; i < 1000; i ++) {
			lvl.update();
		}

		assertEquals("No enemies were hitting", 1, lvl.getHitting());
	}
}
