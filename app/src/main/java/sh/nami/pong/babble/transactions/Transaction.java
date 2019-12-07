package sh.nami.pong.babble.transactions;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Type;

public class Transaction<T> {

    private final static Gson gson = new Gson();

    @SerializedName("type")
    public Type type = Type.MOVE_PLAYER;

    @SerializedName("data")
    public T data = null;

    public static Transaction fromJson(String txJson) {
        return gson.fromJson(txJson, Transaction.class);
    }

}
