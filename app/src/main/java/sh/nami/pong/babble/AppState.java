package sh.nami.pong.babble;

import android.util.Log;

import io.mosaicnetworks.babble.node.BabbleState;

import sh.nami.pong.babble.transactions.HitTx;
import sh.nami.pong.babble.transactions.NewBallTx;
import sh.nami.pong.babble.transactions.NewPlayerTx;
import sh.nami.pong.babble.transactions.Tx;
import sh.nami.pong.models.Ball;
import sh.nami.pong.models.Player;

import static java.nio.charset.StandardCharsets.UTF_8;

public class AppState implements BabbleState {

    private Player player1 = null;
    private Player player2= null;
    private Ball ball = null;

    @Override
    public byte[] applyTransactions(byte[][] transactions) {

        for(byte[] transaction : transactions) {
            String rawTx = new String(transaction, UTF_8);
            Tx tx = Tx.fromJson(rawTx);

            switch(tx.type) {
                case NEW_PLAYER:
                    Log.i("NewPlayerTx", "Incoming NewPlayerTx");
                    NewPlayerTx newPlayerTx = NewPlayerTx.fromJson(rawTx);

                    if(this.player1 == null) {
                        this.player1 = newPlayerTx.data;
                    }  else if(this.player2 == null){
                        this.player2 = newPlayerTx.data;
                    }

                    break;

                case INIT_BALL:
                    Log.i("InitBallTx", "Incoming InitBallTx");
                    NewBallTx newBallTx = NewBallTx.fromJson(rawTx);

                    if(this.ball == null) {
                        this.ball = newBallTx.data;
                    }

                    break;

                case HIT_BALL:
                    Log.i("HitBallTx", "Incoming HitBallTx");
                    HitTx hitTx = HitTx.fromJson(rawTx);

                    if(this.ball != null) {
                        this.ball.setDirection(hitTx.data.direction);
                    }

                    break;

                default:
                    break;

            }
        }

        return new byte[0];
    }

    @Override
    public void reset() {
        // Do reset of state here
        this.player1 = null;
        this.player2 = null;
        this.ball = null;
    }

    public Player getPlayer(int number) {
        switch(number) {
            case 1:
                return this.player1;
            case 2:
                return this.player2;
            default:
                break;
        }
        return this.player1;
    }

    public Ball getBall() {
        return this.ball;
    }

}
