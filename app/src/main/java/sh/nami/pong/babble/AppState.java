package sh.nami.pong.babble;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import io.mosaicnetworks.babble.node.BabbleState;

import sh.nami.pong.R;
import sh.nami.pong.babble.transactions.NewBallTx;
import sh.nami.pong.babble.transactions.NewPlayerTx;
import sh.nami.pong.babble.transactions.Transaction;
import sh.nami.pong.models.Ball;
import sh.nami.pong.models.NVector;
import sh.nami.pong.models.Player;
import sh.nami.pong.models.Vector;

import static java.nio.charset.StandardCharsets.UTF_8;

public class AppState implements BabbleState {

    private Player player1 = null;
    private Player player2= null;
    private Ball ball = null;

    @Override
    public byte[] applyTransactions(byte[][] transactions) {

        for(byte[] transaction : transactions) {
            String rawTx = new String(transaction, UTF_8);
            Transaction tx = Transaction.fromJson(rawTx);

            switch(tx.type) {
                case NEW_PLAYER :
                    NewPlayerTx newPlayerTx = NewPlayerTx.fromJson(rawTx);

                    if(this.player1 == null) {
                        this.player1 = new Player(newPlayerTx.moniker, new Vector(newPlayerTx.x, newPlayerTx.y));
                    }
                    else if(this.player2 == null){
                        this.player2 = new Player(newPlayerTx.moniker, new Vector(newPlayerTx.x, newPlayerTx.y));
                    }
                    break;
                case INIT_BALL :
                    NewBallTx newBallTx = NewBallTx.fromJson(rawTx);
                    if(this.ball == null) {
                        this.ball = new Ball(new Vector(newBallTx.x, newBallTx.y), new NVector(1,1));
                    }
                    break;
                default :
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

}
