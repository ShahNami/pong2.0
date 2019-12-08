package sh.nami.pong.models;

import com.google.gson.annotations.SerializedName;

public class Vector {


    @SerializedName("x")
    private float x;
    @SerializedName("y")
    private float y;

    public Vector(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
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
