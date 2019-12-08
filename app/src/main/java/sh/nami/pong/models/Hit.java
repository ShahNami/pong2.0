package sh.nami.pong.models;

import com.google.gson.annotations.SerializedName;

public class Hit {
    @SerializedName("direction")
    public NVector direction;

    public Hit(NVector direction) {
        this.direction = direction;
    }
}