package sh.nami.pong.models;

import com.google.gson.annotations.SerializedName;

public class Hit {
    @SerializedName("x")
    public int x;
    @SerializedName("y")
    public int y;
    @SerializedName("velocity")
    public float velocity;

    public Hit(int x, int y, float velocity) {
        this.x = x;
        this.y = y;
        this.velocity = velocity;
    }
}