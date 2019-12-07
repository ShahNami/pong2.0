package sh.nami.pong.models;

import com.google.gson.annotations.SerializedName;

public class Miss {
    @SerializedName("x")
    public int x;
    @SerializedName("y")
    public int y;

    public Miss(int x, int y, float velocity) {
        this.x = x;
        this.y = y;
        this.velocity = velocity;
    }
}