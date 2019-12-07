package sh.nami.pong.babble.transactions;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import io.mosaicnetworks.babble.node.BabbleTx;
import sh.nami.pong.Constants;

public class NewBallTx implements BabbleTx {

    @SerializedName("x")
    public int x;
    @SerializedName("y")
    public int y;
    private final static Gson gson = new Gson();

    @SerializedName("type")
    public final Constants.Type type = Constants.Type.INIT_BALL;

    public NewBallTx(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static NewBallTx fromJson(String rawTx) {
        return gson.fromJson(rawTx, NewBallTx.class);
    }


    @Override
    public byte[] toBytes() {
        return gson.toJson(this).getBytes();
    }

}
