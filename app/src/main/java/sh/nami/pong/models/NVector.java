package sh.nami.pong.models;

public class NVector extends Vector {

    public NVector(Vector vector) {

        int magnitude = Math.sqrt(vector.getX() * vector.getX() + vector.getY() * vector.getY());
        int unitX = vector.getX() / magnitude;
        int unitY = vector.getY() / magnitude;


    }

}
