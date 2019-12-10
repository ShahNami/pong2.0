package sh.nami.pong.babble.transactions;

import com.google.gson.Gson;

import io.mosaicnetworks.babble.node.BabbleTx;
import sh.nami.pong.Constants;
import sh.nami.pong.models.Miss;

public class MissTx extends Tx<Miss> implements BabbleTx {
    private final static Gson gson = new Gson();

    public MissTx(Miss m) {
        super(m);

        this.type = Constants.Type.MISS_BALL;
    }

    public static MissTx fromJson(String rawTx) {
        return gson.fromJson(rawTx, MissTx.class);
    }


    @Override
    public byte[] toBytes() {
        return gson.toJson(this).getBytes();
    }
}
