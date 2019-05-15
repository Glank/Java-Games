/**
*author: @Sean LaFleur
*
*provides testing for the Planet, Goal, and Comet classes
*of the comet game
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
public class CometTests {

	@Rule public Timeout globalTimeout = new Timeout(2, SECONDS);

	@Test public void planet1() {
		Planet p1 = new Planet(100.0, 4, 4);
		Planet p2 = new Planet(80.0, 3, 3);

		assertTrue("p1 does not contain p2", p1.contains(p2));
	}

	@Test public void planet2() {
		Planet p1 = new Planet(10.0, 10, 10);
		Planet p2 = new Planet(8.0, 2, 2);

		assertFalse("p2 contains p1", p2.contains(p1));
	}

	@Test public void planet3() {
		Planet p1 = new Planet(10.0, 5, 5);

		assertEquals("The mass is not within .001 of 4188.79", 4188.79, p1.getMass(), .001);
	}

	@Test public void goal1() {
		Goal g = new Goal(10, 10, 10);
		Comet c = new Comet(10, 3, 3);

		g.testReached(c);

		assertTrue("reached is false", g.reached);
	}

	@Test public void goal2() {
		Goal g = new Goal(10, 10, 10);
		Comet c = new Comet(10, 1, 1);

		g.testReached(c);

		assertFalse("reached is true", g.reached);
	}

	@Test public void goal3() {
		Goal g = new Goal(10, 10, 10);
		Comet c = new Comet(10, 3, 3);

		g.testReached(c);

		g.reset();

		assertFalse("reached is still true after reset", g.reached);
	}

	@Test public void comet1() {
		Comet c = new Comet(3, 300, 250);
		Planet p = new Planet(15, 298, 248);

		assertEquals("Distance is not within .01 of 2.83", 2.83, c.getDistance(p), .01);
	}

	@Test public void comet2() {
		Comet c = new Comet(3, 300, 250);
		Planet p = new Planet(15, 138, 90);

		assertEquals("Distance is not withing .01 of 227.69", 227.69, c.getDistance(p), .01);
	}

	@Test public void comet3() {
		Comet c = new Comet(5, 289, 315);

		c.move(Direction.NORTH);

		assertEquals("Y velocity is not -.33", -.33, c.getyVel(), .01);
		assertEquals("X velocity is not zero", 0, c.getxVel(), .01);
	}

	@Test public void comet4() {
		Comet c = new Comet(5, 289, 315);

		c.move(Direction.EAST);

		assertEquals("X velocity is not .33", .33, c.getxVel(), .01);
		assertEquals("Y velocity is not 0", 0, c.getyVel(), .01);
	}

	@Test public void comet5() {
		Comet c = new Comet(5, 290, 320);

		c.move(Direction.WEST);
		c.move(Direction.NORTH);

		assertEquals("X velocity is not -.33", -.33, c.getxVel(), .01);
		assertEquals("Y veloctiy is not -.33", -.33, c.getyVel(), .01);
	}

	@Test public void comet6() {
		Comet c = new Comet(5, 300, 200);

		c.move(Direction.WEST);
		c.move(Direction.WEST);

		assertEquals("X velocity is not -.66", -.66, c.getxVel(), .01);
	}

	@Test public void comet7() {
		Comet c = new Comet();

		assertEquals("Mass is not 113.1 with radius of 3", 113.1, c.getMass(), .01);
	}

	@Test public void comet8() {
		Comet c = new Comet(5, 300, 200);

		c.move(Direction.EAST);
		c.move(Direction.SOUTH);
		c.decelerate();

		assertEquals(.32, c.getxVel(), .01);
		assertEquals(.32, c.getyVel(), .01);
	}

	@Test public void comet9() {
		Comet c = new Comet(5, 300, 200);

		c.move(Direction.EAST);
		c.move(Direction.SOUTH);

		for(int i = 0; i < 10; i ++) {
			c.decelerate();
		}

		assertEquals(.3, c.getxVel(), .01);
		assertEquals(.3, c.getyVel(), .01);
	}

	@Test public void comet10() {
		Comet c = new Comet(5, 10, 30);

		c.move(Direction.EAST);
		c.move(Direction.NORTH);

		c.update();

		assertEquals("Velocity was not added onto 10", 10.33, c.x, .01);
		assertEquals("Velocity was not subtracted from 30", 29.67, c.y, .01);
	}

	@Test public void comet11() {
		Comet c = new Comet(5, 1000, 1000);

		c.move(Direction.EAST);
		c.move(Direction.NORTH);

		c.update();

		assertEquals("x value was not within .01 of 953.78", 953.78, c.x, .01);
		assertEquals("y value was not within .01 of 947.69", 947.69, c.y, .01);
	}

	@Test public void comet12() {
		Comet c = new Comet(3, 400, 250);
		Planet p = new Planet(10, 370, 290);

		c.move(p);

		assertEquals("x velocity was not within .01 of -.03", -.03, c.getxVel(), .01);
		assertEquals("y velocity was not within .01 of .04", .04, c.getyVel(), .01);
	}
}
