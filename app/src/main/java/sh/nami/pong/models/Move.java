package sh.nami.pong.models;

import com.google.gson.annotations.SerializedName;

public class Move {
    @SerializedName("direction")
    public Vector position;

    public Move(Vector position) {
        this.position = position;
    }
}
