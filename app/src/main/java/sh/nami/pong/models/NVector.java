package sh.nami.pong.models;

public class NVector extends Vector {

    public NVector(float x, float y) {
        super(x, y);
        normalise();
    }

    private void normalise(){
        float magnitude = (float)Math.sqrt(this.getX() * this.getX() + this.getY() * this.getY());
        float unitX = this.getX() / magnitude;
        float unitY = this.getY() / magnitude;
        this.setX(unitX);
        this.setY(unitY);
    }
}
