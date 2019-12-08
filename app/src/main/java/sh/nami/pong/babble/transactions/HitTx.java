package sh.nami.pong.babble.transactions;

import com.google.gson.Gson;

import io.mosaicnetworks.babble.node.BabbleTx;

import sh.nami.pong.Constants;
import sh.nami.pong.models.Hit;

public class HitTx extends Tx<Hit> implements BabbleTx {
    private final static Gson gson = new Gson();

    public HitTx(Hit h) {
        super(h);

        this.type = Constants.Type.HIT_BALL;
    }

    public static HitTx fromJson(String rawTx) {
        return gson.fromJson(rawTx, HitTx.class);
    }


    @Override
    public byte[] toBytes() {
        return gson.toJson(this).getBytes();
    }
}
