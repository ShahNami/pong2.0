package sh.nami.pong.babble.transactions;

import com.google.gson.Gson;

import io.mosaicnetworks.babble.node.BabbleTx;
import sh.nami.pong.Constants;
import sh.nami.pong.models.Ball;

public class NewBallTx extends Tx<Ball> implements BabbleTx {
    private final static Gson gson = new Gson();

    public NewBallTx(Ball b) {
        super(b);

        this.type = Constants.Type.INIT_BALL;
    }

    public static NewBallTx fromJson(String rawTx) {
        return gson.fromJson(rawTx, NewBallTx.class);
    }


    @Override
    public byte[] toBytes() {
        return gson.toJson(this).getBytes();
    }

}
