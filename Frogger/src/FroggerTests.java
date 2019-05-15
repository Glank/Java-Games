/* FILE.java
 *
 * @author Thomas Johnson
 *
 * Frogger tests suite
 */

import static org.junit.Assert.fail;

import java.awt.Color;
import java.awt.Rectangle;

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

public class FroggerTests {

  @Rule
public Timeout globalTimeout = new Timeout(10, SECONDS);

Rectangle rectangle = new Rectangle(); // rectangle for testing, created at postion (0, 0, 0, 0)

boolean boolF = false; // boolean false variable
boolean boolT = true; // boolean true variable

Color black = Color.black; // black color
Color white = Color.white; // white color
Color blue = Color.blue; // blue color
Color red = Color.red; // red color
Color green = Color.green; // green color
Color yellow = Color.yellow; // yellow color
Color nil = null; // null color for testing

/*
 *  FROGGER GAME TESTS SUITE
 */

// Car class Tests

@Test
public void testCar1() {

  // empty Car test
}

@Test
public void testCar2() {

  // test with one single Car object
  Car car = new Car(1, 1, 10, black);
}

@Test
public void testCar3() {

  // testing coordinates of car, along with its height and width
  Car car = new Car(1, 1, 10, black);

  assertEquals("This car has an x-coord of 1", 1, car.getx());
  assertEquals("This car has an y-coord of 1", 1, car.gety());

  // according to given code, height and weight of cars are always 18
  assertTrue("This car has a width and height of 18", car.getW() == 18 && car.getH() == 18);
}

@Test
public void testGetSpeed() {

  // tests for getSpeed() method
  Car car = new Car(1, 1, 10, black);
  Car car1 = new Car(1, 1, 100, black);
  Car car2 = new Car(1, 1, 1, green);
  Car car3 = new Car(1, 1, 55, blue);
  Car car4 = new Car(1, 1, 120, black);
  Car car5 = new Car(1, 1, 0, black);

  assertTrue("This car has a speed of 10", car.getSpeed() == 10);
  assertFalse("This car has a speed of 10, not 100", car.getSpeed() == 100);
  assertEquals("The speed of the car is 10.", 10, car.getSpeed());
  assertEquals("The speed of the car is 100.", 100, car1.getSpeed());
  assertEquals("The speed of the car is 1.", 1, car2.getSpeed());
  assertEquals("The speed of the car is 55.", 55, car3.getSpeed());
  assertEquals("The speed of the car is 120.", 120, car4.getSpeed());
  assertEquals("The speed of the car is 0.", 0, car5.getSpeed());
}

@Test
public void testGetColor() {

  // testing getColor() method
  Car car = new Car(1, 1, 10, black);
  Car car1 = new Car(1, 1, 10, white);
  Car car2 = new Car(1, 1, 10, red);
  Car car3 = new Car(1, 1, 10, blue);
  Car car4 = new Car(1, 1, 10, green);
  Car car5 = new Car(1, 1, 10, yellow);


  assertTrue("This color of this car is black", car.getColor() == black);
  assertFalse("This color of this car is black, not yellow", car.getColor() == yellow);
  assertEquals("The color of the car is black", black, car.getColor());
  assertEquals("The color of the car is white", white, car1.getColor());
  assertEquals("The color of the car is red", red, car2.getColor());
  assertEquals("The color of the car is blue", blue, car3.getColor());
  assertEquals("The color of the car is green", green, car4.getColor());
  assertEquals("The color of the car is yellow", yellow, car5.getColor());
}

@Test
public void testColorNull() {

  // testing empty method when the color of the car is null
  Car car = new Car(1, 1, 10, nil);

  // check if color is null
  assertNull("Testing when the color of the car is null", car.getColor());
}

// Frog class Tests

@Test
public void testFrog1() {

  // empty Frog test
}

@Test
public void testFrog2() {

  // test for creating one single Frog object
  Frog frog = new Frog(1, 1, rectangle);
}

@Test
public void testFrog3() {

  // testing Frog coordinates with getX() and getY() methods
  Frog frog = new Frog(1, 1, rectangle);

  assertEquals("The beginning x poistion of the Frog should be at 1", 1, frog.getX());
  assertEquals("The beginning y poistion of the Frog should be at 1", 1, frog.getY());

  assertEquals("Testing the getRectangle method to make sure rectangles are the same", rectangle,
      frog.getRectangle());
}

@Test
public void testFrogMove() {

  // test for move() method
  Frog frog = new Frog(1, 1, rectangle);
  frog.move(5, 5);

  assertEquals("Testing the move() method in Frog class", 1, frog.getX());
  assertEquals("Testing the move() method in Frog class", 1, frog.getY());
}

@Test
public void testGetBoundsFrog() {

  // test for checking
  Frog frog = new Frog(1, 1, rectangle);

  assertTrue("Testing to make sure frog bounds don't equal rectangle bounds",
      frog.getBounds() != rectangle.getBounds());
}

@Test
public void testGetX() {

  // tests for getX() and setX() methods
  Frog frog = new Frog(1, 1, rectangle);

  frog.setX(2);
  assertEquals("The x poistion of the Frog should be at 2", 2, frog.getX());

  frog.setX(50);
  assertEquals("The x poistion of the Frog should be at 50", 50, frog.getX());

  frog.setX(100);
  assertEquals("The x poistion of the Frog should be at 100", 100, frog.getX());

  frog.setX(10);
  assertEquals("The x poistion of the Frog should be at 10", 10, frog.getX());

  frog.setX(1);
  assertEquals("The x poistion of the Frog should be at 1", 1, frog.getX());
}

@Test
public void testGetY() {

  // tests for getY() and setY() methods
  Frog frog = new Frog(1, 1, rectangle);

  frog.setY(2);
  assertEquals("X poistion of the Frog should be at 2", 2, frog.getY());

  frog.setY(5);
  assertEquals("X poistion of the Frog should be at 5", 5, frog.getY());

  frog.setY(100);
  assertEquals("X poistion of the Frog should be at 100", 100, frog.getY());

  frog.setY(50);
  assertEquals("X poistion of the Frog should be at 50", 50, frog.getY());

  frog.setY(10);
  assertEquals("X poistion of the Frog should be at 10", 10, frog.getY());
}

// FroggerComponent class Tests

@Test
public void testFroggerComponent1() {

  // empty FroggerComponent test
}

@Test
public void testFroggerComponent2() {

  // testing constructor with one single FroggerComponent object
  FroggerComponent fc = new FroggerComponent();
}

@Test
public void testGetLevel() {

  // tests for setLevel() and getLevel() methods
  FroggerComponent fc = new FroggerComponent();

  fc.setLevel(2);
  assertEquals("The current level is 2", 2, fc.getLevel());

  fc.setLevel(5);
  assertEquals("The current level is 5", 5, fc.getLevel());

  fc.setLevel(1);
  assertEquals("The current level is 1", 1, fc.getLevel());

  fc.setLevel(10);
  assertEquals("The current level is 10", 10, fc.getLevel());

  fc.setLevel(25);
  assertEquals("The current level is 25", 25, fc.getLevel());
}

@Test
public void testGetLife() {

  // tests for setLife() and getLife() methods
  FroggerComponent fc = new FroggerComponent();

  fc.setLife(5);
  assertEquals("The Frog has 5 lives", 5, fc.getLife());

  fc.setLife(4);
  assertEquals("The Frog has 4 lives", 4, fc.getLife());

  fc.setLife(3);
  assertEquals("The Frog has 3 lives", 3, fc.getLife());

  fc.setLife(1);
  assertEquals("The Frog has 1 lives", 1, fc.getLife());

  fc.setLife(0);
  assertEquals("The Frog has 0 lives", 0, fc.getLife());
}

@Test
public void testGetScore() {

  // tests for getScore() method
  FroggerComponent fc = new FroggerComponent();

  fc.setScore(50);
  assertEquals("There is a current score of 50", 50, fc.getScore());

  fc.setScore(150);
  assertEquals("There is a current score of 150", 150, fc.getScore());

  fc.setScore(500);
  assertEquals("There is a current score of 500", 500, fc.getScore());

  fc.setScore(1000);
  assertEquals("There is a current score of 1000", 1000, fc.getScore());

  fc.setScore(15000);
  assertEquals("There is a current score of 50", 15000, fc.getScore());
}

@Test
public void testGetHighscore() {

  // tests for getHighscore() and setHighscore() methods
  FroggerComponent fc = new FroggerComponent();

  fc.setHighscore(1000);
  assertEquals("The highscore is 1000", 1000, fc.getHighscore());

  fc.setHighscore(5000);
  assertEquals("The highscore is 5000", 5000, fc.getHighscore());

  fc.setHighscore(10000);
  assertEquals("The highscore is 10000", 10000, fc.getHighscore());

  fc.setHighscore(20000);
  assertEquals("The highscore is 20000", 20000, fc.getHighscore());

  fc.setHighscore(1000000);
  assertEquals("The highscore is 1000000", 1000000, fc.getHighscore());
}

// TrafficPattern class Tests

@Test
public void testTrafficPattern1() {

  // empty TrafficPattern test
}

@Test
public void testTrafficPattern2() {

  // test for creating single TrafficPattern object
  String s = "Hello";
  Rectangle r = new Rectangle(1, 1);

  TrafficPattern tp = new TrafficPattern(1, s, true, r, 1);
}

@Test
public void testGetNext() {

  // tests for getNext() method
  String s = "Hello";
  Rectangle r = new Rectangle(1, 1);

  TrafficPattern tp = new TrafficPattern(1, s, true, r, 1);

  assertTrue("The next value of tp object is 1", tp.getNext() == 1);
  assertFalse("The next value of tp object is 1, not 100", tp.getNext() == 100);
  assertEquals("The next value of tp object is 1", 1, tp.getNext());
}

@Test
public void testgetTrafficSpeed() {

  String s = "Hello";
  Rectangle r = new Rectangle(1, 1);

  TrafficPattern tp = new TrafficPattern(1, s, true, r, 1);

  assertEquals("The speed of tp object is 1", 1, tp.getSpeed());
}

@Test
public void testgetTrafficPattern() {

  String s = "Hello";
  Rectangle r = new Rectangle(1, 1);

  TrafficPattern tp = new TrafficPattern(1, s, true, r, 1);

  assertEquals("The pattern is equal to s variable", s, tp.getPattern());
}

@Test
public void testgetTrafficBool() {

  String s = "Hello";
  Rectangle r = new Rectangle(1, 1);
  boolean b = true;

  TrafficPattern tp = new TrafficPattern(1, s, b, r, 1);

  assertTrue("The boolean value in tp object is true", tp.getBool());
}

@Test
public void testgetTrafficRect() {

  String s = "Hello";
  Rectangle r = new Rectangle(1, 1);

  TrafficPattern tp = new TrafficPattern(1, s, true, r, 1);

  assertEquals("The rectangle is tp object is equal to r object", r, tp.getRect());
}

// Cronometro class Tests

@Test
public void testCronometro1() {

  // empty test
}

@Test
public void testCronometro2() {

  // test for creating single Cronometro object
  Cronometro cron = new Cronometro();
}

// FroggerLevel class Tests

@Test
public void testFroggerLevel1() {

  // empty test
}

@Test
public void testFroggerLevel2() {

  int[] speeds = new int[2];
  String[] patterns = new String[5];

  // test for one single FroggerLevel object
  FroggerLevel fl = new FroggerLevel(speeds, patterns);
}

@Test
public void testGetSpeeds() {

  // test for getSpeed() method
  int[] speeds = new int[2];
  String[] patterns = new String[5];

  FroggerLevel fl = new FroggerLevel(speeds, patterns);

  assertTrue("There are 2 ints in this speed.", fl.getSpeeds() == speeds);
  assertEquals("There are 2 speeds.", 2, fl.getSpeeds().length);
}

@Test
public void testGetPatterns() {

  // test for getPattern() method
  int[] speeds = new int[2];
  String[] patterns = new String[5];

  FroggerLevel fl = new FroggerLevel(speeds, patterns);

  assertTrue("There are 5 strings in this pattern.", fl.getPatterns() == patterns);
  assertEquals("There are 5 patterns.", 5, fl.getPatterns().length);
}

// PlayFrogger class Tests

@Test
public void testPlayFrogger1() {

  // empty test
}

@Test
public void testPlayFrogger2() {

  // test for creating one single PlayFrogger object
  PlayFrogger play = new PlayFrogger();
}
}
