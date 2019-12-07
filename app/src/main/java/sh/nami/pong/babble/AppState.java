package sh.nami.pong.babble;

import android.content.res.Resources;
import android.util.Log;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import io.mosaicnetworks.babble.node.BabbleState;
import sh.nami.pong.Constants;
import sh.nami.pong.babble.transactions.InitBallTx;
import sh.nami.pong.babble.transactions.NewPlayerTx;
import sh.nami.pong.babble.transactions.Transaction;
import sh.nami.pong.models.Ball;
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
                    InitBallTx initBallTx = InitBallTx.fromJson(rawTx);
                    if(this.ball == null) {
                        this.ball = new Ball(new Vector(initBallTx.x, initBallTx.y));
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
    }

    public Map<String, Player> getPlayers() {
        return this.players;
    }

    private void addPlayer(Player p) {
        this.players.put(p.getName(), p);
    }

    private void removePlayer(Player p) {
        this.players.remove(p.getName());
    }

    // TEMP till localStorage
    private String moniker = "Player 1";

    public void setMoniker(String name) {
        this.moniker = name;
    }
    public String getMoniker() {
        return this.moniker;
    }
}
