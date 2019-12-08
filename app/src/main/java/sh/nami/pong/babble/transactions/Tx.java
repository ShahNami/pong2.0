package sh.nami.pong.babble.transactions;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;


import sh.nami.pong.Constants;


public class Tx<T> {

    private final static Gson gson = new Gson();

    @SerializedName("type")
    public Constants.Type type = Constants.Type.NEW_PLAYER;

    @SerializedName("data")
    public T data  = null;

    public Tx(T data) {
        this.data = data;
    }

    public static Tx fromJson(String txJson) {
        return gson.fromJson(txJson, Tx.class);
    }

}
