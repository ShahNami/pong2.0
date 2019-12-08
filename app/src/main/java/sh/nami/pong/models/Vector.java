package sh.nami.pong.models;

import com.google.gson.annotations.SerializedName;

public class Vector {


    @SerializedName("x")
    private int x;
    @SerializedName("y")
    private int y;

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void add(Vector vector) {
       this.setX(this.getX() + vector.getX());
       this.setY(this.getY() + vector.getY());
    }

    public Vector mult(int magnitude) {
        return new Vector(this.x * magnitude, this.y * magnitude);
    }
}
