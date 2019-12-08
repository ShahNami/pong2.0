package sh.nami.pong.babble.transactions;

import com.google.gson.Gson;

import io.mosaicnetworks.babble.node.BabbleTx;
import sh.nami.pong.Constants;
import sh.nami.pong.models.Move;

public class MovePlayerTx extends Tx<Move> implements BabbleTx {
    private final static Gson gson = new Gson();

    public MovePlayerTx(Move m) {
        super(m);

        this.type = Constants.Type.MOVE_PLAYER;
    }

    public static MovePlayerTx fromJson(String rawTx) {
        return gson.fromJson(rawTx, MovePlayerTx.class);
    }


    @Override
    public byte[] toBytes() {
        return gson.toJson(this).getBytes();
    }
}
