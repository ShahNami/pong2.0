package sh.nami.pong.babble.transactions;

import com.google.gson.Gson;

import io.mosaicnetworks.babble.node.BabbleTx;
import sh.nami.pong.models.Player;

public class NewTx extends Transaction<Player> implements BabbleTx {
    private final static Gson gson = new Gson();

    public NewTx(Player player) {
        // set type of transaction
        this.type = Type.NEW_PLAYER;

        // set payload data
        this.data = player;
    }

    public static NewTx fromJson(String txJson) {
        return gson.fromJson(txJson, NewTx.class);
    }

    @Override
    public byte[] toBytes() {
        return gson.toJson(this).getBytes();
    }
}


