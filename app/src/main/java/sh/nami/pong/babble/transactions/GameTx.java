package sh.nami.pong.babble.transactions;

import android.content.res.Resources;

import java.util.HashMap;
import java.util.Map;

import io.mosaicnetworks.babble.node.BabbleTx;
import sh.nami.pong.Constants;
import sh.nami.pong.models.Ball;
import sh.nami.pong.models.Player;
import sh.nami.pong.models.Vector;

public class GameTx implements BabbleTx {

    private Player player1 = new Player("player1", new Vector(10, Resources.getSystem().getDisplayMetrics().heightPixels / 2 - Constants.PADDLE_HEIGHT / 2));
    private Player player2= new Player("player2", new Vector( Resources.getSystem().getDisplayMetrics().widthPixels - 10, Resources.getSystem().getDisplayMetrics().heightPixels / 2 - Constants.PADDLE_HEIGHT / 2));
    private Ball ball = new Ball(new Vector(0, 0));

    @Override
    public byte[] toBytes() {
        return new byte[0];
    }

}
