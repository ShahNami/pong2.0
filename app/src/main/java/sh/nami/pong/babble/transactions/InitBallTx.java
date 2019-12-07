package sh.nami.pong.babble.transactions;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import io.mosaicnetworks.babble.node.BabbleTx;

public class InitBallTx implements BabbleTx {

    @SerializedName("x")
    private int x;
    @SerializedName("y")
    private int y;
    private final static Gson gson = new Gson();

    public InitBallTx(int x, int y) {
        this.x = x;
        this.y = y;
    }


    @Override
    public byte[] toBytes() {
        return gson.toJson(this).getBytes();
    }

}
