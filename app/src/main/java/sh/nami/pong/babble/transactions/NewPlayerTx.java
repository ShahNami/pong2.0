package sh.nami.pong.babble.transactions;

import com.google.gson.Gson;

import io.mosaicnetworks.babble.node.BabbleTx;

import sh.nami.pong.Constants;
import sh.nami.pong.models.Player;

public class NewPlayerTx extends Tx<Player> implements BabbleTx {
    private final static Gson gson = new Gson();

    public NewPlayerTx(Player p) {
        super(p);

        this.type = Constants.Type.NEW_PLAYER;
    }

    public static NewPlayerTx fromJson(String rawTx) {
        return gson.fromJson(rawTx, NewPlayerTx.class);
    }

    @Override
    public byte[] toBytes() {
        return gson.toJson(this).getBytes();
    }
}
