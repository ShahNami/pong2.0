package sh.nami.pong.babble.transactions;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class Transaction<T> {

    private final static Gson gson = new Gson();

    public enum Type {
        HIT_PADDLE,
        MOVE_PLAYER,
        MISS_PADDLE,
    }

    @SerializedName("type")
    public Type type = Type.MOVE_PLAYER;

    @SerializedName("data")
    public T data = null;

    public static Transaction fromJson(String txJson) {
        return gson.fromJson(txJson, Transaction.class);
    }

}
