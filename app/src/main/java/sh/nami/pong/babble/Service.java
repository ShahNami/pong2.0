package sh.nami.pong.babble;


import android.content.res.Resources;

import io.mosaicnetworks.babble.node.BabbleConfig;
import io.mosaicnetworks.babble.node.BabbleService;
import sh.nami.pong.Constants;
import sh.nami.pong.babble.transactions.NewBallTx;
import sh.nami.pong.babble.transactions.NewPlayerTx;

public class Service extends BabbleService<AppState> {
    private static Service INSTANCE;

    public static Service getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Service();
        }

        return INSTANCE;
    }

    private Service() {
        super(new AppState(), new BabbleConfig.Builder().heartbeat(1).logLevel(BabbleConfig.LogLevel.DEBUG).build());
    }

    public void startGame() {
        NewPlayerTx p1Tx = new NewPlayerTx("Player 1", Constants.PADDLE_MARGIN, Resources.getSystem().getDisplayMetrics().heightPixels / 2 - Constants.PADDLE_HEIGHT / 2);
        NewPlayerTx p2Tx = new NewPlayerTx("Player 2",  Resources.getSystem().getDisplayMetrics().widthPixels - 10, Resources.getSystem().getDisplayMetrics().heightPixels / 2 - Constants.PADDLE_HEIGHT / 2);

        NewBallTx ballTx = new NewBallTx(p1Tx.x, p1Tx.y + Constants.PADDLE_HEIGHT/2);

        getInstance().submitTx(p1Tx);
        getInstance().submitTx(p2Tx);
        getInstance().submitTx(ballTx);
    }

}