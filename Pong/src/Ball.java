import java.awt.*;

public class Ball
{
    public static final int RADIUS = 10;

    private int x, y;
    private int dx, dy;
    private Color color;

    private BallSpeedAttributes attributesSpeed;

    public Ball(Color c, int x, int y)
    {
        color = c;
        this.x = x;
        this.y = y;
        dx = (Math.random() < .5) ? 1 : -1;
        dy = (Math.random() < .5) ? 1 : -1;
        attributesSpeed = new BallSpeedAttributes();
    }

    public void move()
    {
        attributesSpeed.incrementDelay();
        if (attributesSpeed.isTimeToSpeedUp())
            attributesSpeed.incrementSpeed();
        x += (int) (getUnitDX() * attributesSpeed.getSpeed());
        y += (int) (getUnitDY() * attributesSpeed.getSpeed());
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    private double getUnitDX()
    {
        return ((double) dx / (double) (Math.sqrt(dx * dx + dy * dy)));
    }

    private double getUnitDY()
    {
        return ((double) dy / (double) (Math.sqrt(dx * dx + dy * dy)));
    }

    public void bounceSide()
    {
        dx = -dx;
        dy = (int) (Math.random() * 8) - 4;
        move();
        move();
    }

    public void bounceTop()
    {
        dy = -dy;
        move();
    }

    public void draw(Graphics g)
    {
        g.setColor(color);
        g.fillOval(x - RADIUS, y - RADIUS, RADIUS * 2, RADIUS * 2);
    }
}
