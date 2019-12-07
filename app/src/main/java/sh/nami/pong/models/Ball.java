package sh.nami.pong.models;


public class Ball {
    private int velocity;
    private Vector direction;

    public Ball(Vector position) {
        this.calculateTrajectory(position);
    }


    private void calculateTrajectory(Vector position) {
        // y = kx + b
        // k = tanr
    }

    public Vector getDirection() {
        return this.direction;
    }

    public int getVelocity() {
        return this.velocity;
    }
}
