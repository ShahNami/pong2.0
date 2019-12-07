package sh.nami.pong.babble.transactions;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


import io.mosaicnetworks.babble.node.BabbleTx;
import sh.nami.pong.Constants;
import sh.nami.pong.models.Ball;
import sh.nami.pong.models.NVector;
import sh.nami.pong.models.Player;
import sh.nami.pong.models.Vector;

public class GameTx implements BabbleTx {

    private Player player1 = new Player(BitmapFactory.decodeResource(Resources.getSystem(), 1) , "player1", new Vector(10, Resources.getSystem().getDisplayMetrics().heightPixels / 2 - Constants.PADDLE_HEIGHT / 2));
    private Player player2= new Player(BitmapFactory.decodeResource(Resources.getSystem(), 1), "player2", new Vector( Resources.getSystem().getDisplayMetrics().widthPixels - 10, Resources.getSystem().getDisplayMetrics().heightPixels / 2 - Constants.PADDLE_HEIGHT / 2));
    private Ball ball = new Ball(BitmapFactory.decodeResource(Resources.getSystem(), 1), new Vector(0, 0), new NVector(1,1));


    @Override
    public byte[] toBytes() {
        return new byte[0];
    }

}
