package sh.nami.pong.models;

import com.google.gson.annotations.SerializedName;

public class Miss {
    @SerializedName("player")
    public Player player;
    @SerializedName("direction")
    public NVector direction;

    public Miss(Player player, NVector direction) {
        this.player = player;
        this.direction = direction;
    }
}
