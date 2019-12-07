package sh.nami.pong.models;

import com.google.gson.annotations.SerializedName;

public class Hit {
    @SerializedName("x")
    public int x;
    @SerializedName("y")
    public int y;
    @SerializedName("direction")
    public NVector direction;

    public Hit(int x, int y, NVector direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }
}