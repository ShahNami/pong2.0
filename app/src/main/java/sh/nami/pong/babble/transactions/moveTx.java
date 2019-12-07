package sh.nami.pong.babble.transactions;

import com.google.gson.Gson;

import io.mosaicnetworks.babble.node.BabbleTx;
import sh.nami.pong.models.Move;

public class MoveTx extends Transaction<Move> implements BabbleTx {

    private final static Gson gson = new Gson();

    public MoveTx(String name, int x, int y) {
        // set type of transaction
        this.type = Transaction.Type.MOVE_PLAYER;

        // set payload data
        this.data = new Move(name, x, y);
    }

    public static MoveTx fromJson(String txJson) {
        return gson.fromJson(txJson, MoveTx.class);
    }

    @Override
    public byte[] toBytes() {
        return gson.toJson(this).getBytes();
    }
}
