package sh.nami.pong.models;

public class NVector extends Vector {

    public NVector(int x, int y) {
        super(x, y);
        normalise();
    }

    private void normalise(){
        int magnitude = (int)Math.sqrt(this.getX() * this.getX() + this.getY() * this.getY());
        int unitX = this.getX() / magnitude;
        int unitY = this.getY() / magnitude;
        this.setX(unitX);
        this.setY(unitY);
    }
}
