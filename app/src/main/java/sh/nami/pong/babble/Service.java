package sh.nami.pong.babble;


import io.mosaicnetworks.babble.node.BabbleConfig;
import io.mosaicnetworks.babble.node.BabbleService;

import sh.nami.pong.babble.transactions.NewBallTx;
import sh.nami.pong.babble.transactions.NewPlayerTx;
import sh.nami.pong.models.Ball;
import sh.nami.pong.models.Player;

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

    public void startGame(Ball b, Player p1, Player p2) {
        NewPlayerTx p1Tx = new NewPlayerTx(p1);
        NewPlayerTx p2Tx = new NewPlayerTx(p2);

        NewBallTx ballTx = new NewBallTx(b);
        getInstance().submitTx(ballTx);

        getInstance().submitTx(p1Tx);
        getInstance().submitTx(p2Tx);
    }

}