public class BallSpeedAttributes {
    private int speed;
    private int speedUpDelay;
    private int delay;

    public BallSpeedAttributes() {
        speed = 0;
        speedUpDelay = 25;
        delay = 0;
    }

    public BallSpeedAttributes(int speed, int speedUpDelay, int delay) {
        this.speed = speed;
        this.speedUpDelay = speedUpDelay;
        this.delay = delay;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        if (speed >= 0) {
            this.speed = speed;
        }
    }

    public int getSpeedUpDelay() {
        return speedUpDelay;
    }

    public void setSpeedUpDelay(int speedUpDelay) {
        if (speedUpDelay >= 0) {
            this.speedUpDelay = speedUpDelay;
        }
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        if (delay >= 0) {
            this.delay = delay;
        }
    }

}
