/* FILE.java
 *
 * @author Thomas Johnson
 *
 * Tetris tests suite
 */

import static org.junit.Assert.fail;
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

public class TetrisTests {

  @Rule public Timeout globalTimeout = new Timeout(2, SECONDS);

  /*
 *  TETRIS GAME TESTS SUITE
 */

// BlockGrid Tests

  @Test
public void testBlockGrid1() {

  // empty test
}

@Test
public void testBlockGrid2() {

  // test for creating one single BlockGrid object
  BlockGrid bg = new BlockGrid(1, 1);
}

@Test
public void testSetColorBlockGrid() {

  // tests for checking the setColor() and getColor() methods
  BlockGrid bg = new BlockGrid(1, 1);

  bg.setColor(white);
  assertEquals("The background color of the tetris game is white", white, bg.getColor());

  bg.setColor(black);
  assertEquals("The background color of the tetris game is black", black, bg.getColor());

  bg.setColor(red);
  assertEquals("The background color of the tetris game is red", red, bg.getColor());

  bg.setColor(blue);
  assertEquals("The background color of the tetris game is blue", blue, bg.getColor());

  bg.setColor(green);
  assertEquals("The background color of the tetris game is green", green, bg.getColor());

  bg.setColor(yellow);
  assertEquals("The background color of the tetris game is yellow", yellow, bg.getColor());
}

@Test
public void testGetWidthBlockGrid() {

  // tests for checking the getWidth() method
  BlockGrid bg = new BlockGrid(1, 1);
  assertEquals("The width of the block grid is 1", 1, bg.getWidth());

  BlockGrid bg1 = new BlockGrid(10, 10);
  assertEquals("The width of the block grid is 10", 10, bg1.getWidth());

  BlockGrid bg2 = new BlockGrid(100, 10);
  assertEquals("The width of the block grid is 100", 100, bg2.getWidth());

  BlockGrid bg3 = new BlockGrid(50, 10);
  assertEquals("The width of the block grid is 50", 50, bg3.getWidth());

  BlockGrid bg4 = new BlockGrid(0, 10);
  assertEquals("The width of the block grid is 0", 0, bg4.getWidth());
}

@Test
public void testGetHeightBlockGrid() {

  // tests for checking the getHeight() method
  BlockGrid bg = new BlockGrid(1, 1);
  assertEquals("The width of the block grid is 1", 1, bg.getHeight());

  BlockGrid bg1 = new BlockGrid(10, 100);
  assertEquals("The width of the block grid is 100", 100, bg1.getHeight());

  BlockGrid bg2 = new BlockGrid(100, 50);
  assertEquals("The width of the block grid is 50", 50, bg2.getHeight());

  BlockGrid bg3 = new BlockGrid(50, 10);
  assertEquals("The width of the block grid is 10", 10, bg3.getHeight());

  BlockGrid bg4 = new BlockGrid(1, 25);
  assertEquals("The width of the block grid is 25", 25, bg4.getHeight());
}

@Test
public void testGetGraphicsWidthBlockGrid() {

  // test for checking graphics width of tetris grid
  BlockGrid bg = new BlockGrid(1, 1);

  assertEquals("The graphics width of the block grid is 25", 25, bg.getGraphicsWidth());
}

@Test
public void testGetGraphicsHeightBlockGrid() {

  // test for checking graphics height of tetris grid
  BlockGrid bg = new BlockGrid(1, 1);

  assertEquals("The graphics height of the block grid is 25", 25, bg.getGraphicsHeight());
}

@Test
public void testToStringBlockGrid1() {

  // test for toString() method
  BlockGrid bg = new BlockGrid(1, 1);

  assertEquals("The expected result of toString() is -","-", bg.toString());
}

// TetrisComponent Tests

@Test
public void testTetrisComponent1() {

  // empty test
}

@Test
public void testTetrisComponent2() {

  // test for creating one single TetrisComponent object
  TetrisComponent tc = new TetrisComponent(1, 1);
}

// TetrisGrid Tests

@Test
public void testTetrisGrid1() {

  // empty constructor
}

@Test
public void testTetrisGrid2() {

  // test for creating one single TetrisGrid object
  TetrisGrid tg = new TetrisGrid(1, 1);
}

// PlayTetris Tests

@Test
public void testPlayTetris1() {

  // empty test
}

@Test
public void testPlayTetris2() {

  // test for creating one single PlayTetris object
  PlayTetris pt = new PlayTetris();
}
}
