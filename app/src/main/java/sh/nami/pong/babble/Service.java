package sh.nami.pong.babble;


import io.mosaicnetworks.babble.node.BabbleConfig;
import io.mosaicnetworks.babble.node.BabbleService;

import sh.nami.pong.babble.transactions.HitTx;
import sh.nami.pong.babble.transactions.MovePlayerTx;
import sh.nami.pong.babble.transactions.NewBallTx;
import sh.nami.pong.babble.transactions.NewPlayerTx;
import sh.nami.pong.models.Ball;
import sh.nami.pong.models.Hit;
import sh.nami.pong.models.Move;
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
        super(new AppState(), new BabbleConfig.Builder().heartbeat(10).logLevel(BabbleConfig.LogLevel.DEBUG).build());
    }

    public void startGame(Ball b) {
        NewBallTx ballTx = new NewBallTx(b);
        getInstance().submitTx(ballTx);
    }

    public void addPlayer(Player p1) {
        NewPlayerTx p1Tx = new NewPlayerTx(p1);
        getInstance().submitTx(p1Tx);
    }

    public void hitBall(Hit hit) {
        HitTx tx = new HitTx(hit);

        getInstance().submitTx((tx));
    }

    public void movePlayer(Move move) {
        MovePlayerTx tx = new MovePlayerTx(move);
        getInstance().submitTx(tx);
    }

}