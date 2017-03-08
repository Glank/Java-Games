import java.awt.*;
import java.util.*;

public class TrafficPattern
{

	// The pattern should be of the form "RRR  BB L   MMM B  GG "
	// R == RED
	// B == BLUE
	// L == BLACK
	// M == MAGENTA
        // Y == YELLOW
        // C == CYAN
        // G == GREEN

	private int speed;
	private String pattern;
	private boolean left;
	private int next = 0;
	private Rectangle bounds;
	private Vector<Car> cars;
	private int y;

	public TrafficPattern(int speed, String pattern, boolean left, Rectangle bounds, int y)
	{
		this.speed = speed;
		this.pattern = pattern;
		this.left = left;
		this.bounds = bounds;
		this.y = y;
		initCars();
	}

	public void initCars()
	{
		cars = new Vector<Car>();
		for(next = 0; next*Car.WIDTH <= bounds.getWidth(); next++)
		{
			switch(pattern.charAt(next))
			{
				case 'R':
					cars.add(new Car(left?next*Car.WIDTH:(int)bounds.getWidth()-next*Car.WIDTH, y, speed, Color.RED));
					break;
				case 'B':
					cars.add(new Car(left?next*Car.WIDTH:(int)bounds.getWidth()-next*Car.WIDTH, y, speed, Color.BLUE));
					break;
				case 'L':
					cars.add(new Car(left?next*Car.WIDTH:(int)bounds.getWidth()-next*Car.WIDTH, y, speed, Color.BLACK));
					break;
				case 'M':
					cars.add(new Car(left?next*Car.WIDTH:(int)bounds.getWidth()-next*Car.WIDTH, y, speed, Color.MAGENTA));
					break;
                                case 'Y':
					cars.add(new Car(left?next*Car.WIDTH:(int)bounds.getWidth()-next*Car.WIDTH, y, speed, Color.YELLOW));
					break;
                                case 'C':
					cars.add(new Car(left?next*Car.WIDTH:(int)bounds.getWidth()-next*Car.WIDTH, y, speed, Color.CYAN));
					break;
                                case 'G':
					cars.add(new Car(left?next*Car.WIDTH:(int)bounds.getWidth()-next*Car.WIDTH, y, speed, Color.GREEN));
					break;
				default:
					cars.add(new Car(left?next*Car.WIDTH:(int)bounds.getWidth()-next*Car.WIDTH, y, speed, null));
			}
		}
	}

	public void addCar()
	{
		next++;
		if(next >= pattern.length())
			next = 0;
		switch(pattern.charAt(next))
		{
			case 'R':
				cars.add(new Car(left?(int)bounds.getWidth():0, y, speed, Color.RED));
				break;
			case 'B':
				cars.add(new Car(left?(int)bounds.getWidth():0, y, speed, Color.BLUE));
				break;
			case 'L':
				cars.add(new Car(left?(int)bounds.getWidth():0, y, speed, Color.BLACK));
				break;
			case 'M':
				cars.add(new Car(left?(int)bounds.getWidth():0, y, speed, Color.MAGENTA));
				break;
                        case 'Y':
				cars.add(new Car(left?(int)bounds.getWidth():0, y, speed, Color.YELLOW));
				break;
                        case 'C':
				cars.add(new Car(left?(int)bounds.getWidth():0, y, speed, Color.CYAN));
				break;
                        case 'G':
				cars.add(new Car(left?(int)bounds.getWidth():0, y, speed, Color.GREEN));
				break;
			default:
				cars.add(new Car(left?(int)bounds.getWidth():0, y, speed, null));
		}
	}

	public void moveCars()
	{
		for(int i = 0; i < cars.size(); i++)
		{
			if(cars.get(i) != null)
			{
				if(left)
				{
					cars.add(i, cars.get(i).moveLeft());
					cars.remove(i+1);
				}
				else
				{
					cars.add(i, cars.get(i).moveRight());
					cars.remove(i+1);
				}
			}
		}
	}

	public void testForNewCar()
	{
		if(!cars.get(0).intersects(bounds))
		{
			cars.remove(0);
			addCar();
		}
	}

	public boolean intersects(Frog f)
	{
		for(int i = 0; i < cars.size(); i++)
		{
			if((cars.get(i) != null) && !cars.get(i).empty() && cars.get(i).intersects(f.getBounds()))
				return true;
		}
		return false;
	}

	public void draw(Graphics g)
	{
		//g.setColor(Color.DARK_GRAY);
		//g.fillRect(0,y,(int)bounds.getWidth(), Car.HEIGHT);
		for(int i = 0; i < cars.size(); i++)
		{
			if(cars.get(i) != null)
				cars.get(i).draw(g);
		}
	}

	public void update()
	{
		moveCars();
		testForNewCar();
	}
}