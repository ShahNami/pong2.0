package sh.nami.pong.babble.transactions;

import com.google.gson.annotations.SerializedName;

import com.google.gson.Gson;

import io.mosaicnetworks.babble.node.BabbleTx;
import sh.nami.pong.Constants;

public class NewPlayerTx implements BabbleTx {

    @SerializedName("moniker")
    private String moniker;
    @SerializedName("x")
    private int x;
    @SerializedName("y")
    private int y;
    private final static Gson gson = new Gson();

    @SerializedName("type")
    public final Constants.Type type = Constants.Type.NEW_PLAYER;

    public NewPlayerTx(String moniker, int x, int y) {
        this.moniker = moniker;
        this.x = x;
        this.y = y;
    }

    @Override
    public byte[] toBytes() {
        return gson.toJson(this).getBytes();
    }

}
