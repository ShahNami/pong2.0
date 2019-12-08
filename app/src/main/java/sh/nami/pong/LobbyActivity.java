package sh.nami.pong;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.List;

import io.mosaicnetworks.babble.discovery.HttpPeerDiscoveryRequest;
import io.mosaicnetworks.babble.discovery.Peer;
import io.mosaicnetworks.babble.discovery.ResponseListener;
import io.mosaicnetworks.babble.node.BabbleService;
import io.mosaicnetworks.babble.node.ServiceObserver;
import io.mosaicnetworks.babble.utils.Utils;
import sh.nami.pong.babble.Service;
import sh.nami.pong.models.Player;
import sh.nami.pong.models.Vector;

public class LobbyActivity extends Activity implements ResponseListener, ServiceObserver {

    private HttpPeerDiscoveryRequest httpCurrentPeerDiscoveryRequest;
    private List<Peer> gPeers;
    private List<Peer> cPeers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);


        Intent intent = getIntent();
        int type = intent.getIntExtra(Constants.EXTRA_TYPE, 0);
        String moniker = intent.getStringExtra(Constants.EXTRA_MONIKER);

        Bitmap paddle = BitmapFactory.decodeResource(getResources(), R.drawable.paddle);

        switch(type) {
            case 0:
                this.startNew(moniker);

                Vector p1Pos = new Vector(Constants.PADDLE_MARGIN, Constants.screenHeight / 2 - paddle.getHeight()/2);
                Player p1 = new Player(paddle, "Player 1", p1Pos);
                Service.getInstance().addPlayer(p1);

                break;
            case 1:
                this.join(moniker);

                Vector p1Pos2 = new Vector(Constants.PADDLE_MARGIN, Constants.screenHeight / 2 - paddle.getHeight()/2);
                Vector p2Pos = new Vector( Constants.screenWidth - Constants.PADDLE_MARGIN - paddle.getWidth() - 15, p1Pos2.getY());
                Player p2 = new Player(paddle, "Player 2", p2Pos);
                Service.getInstance().addPlayer(p2);

                break;
            default:
                break;
        }


        setContentView(R.layout.activity_lobby);
    }

    public void startGame(View view) {
        Bitmap paddle = BitmapFactory.decodeResource(getResources(), R.drawable.paddle);

        Vector p1Pos = new Vector(Constants.PADDLE_MARGIN, Constants.screenHeight / 2 - paddle.getHeight()/2);
        Vector p2Pos = new Vector( Constants.screenWidth - Constants.PADDLE_MARGIN - paddle.getWidth() - 15, p1Pos.getY());
        Player p2 = new Player(paddle, "Player 2", p2Pos);
        Service.getInstance().addPlayer(p2);

        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void startNew(String moniker) {
        Service.getInstance().configureNew(moniker, Utils.getIPAddr(this));

        // Start instance
        Service.getInstance().start();
        Service.getInstance().registerObserver(this);

        if (Service.getInstance().getState() != BabbleService.State.RUNNING_WITH_DISCOVERY) {
            // Toast.makeText(this, "Unable to advertise peers", Toast.LENGTH_LONG).show();
            Log.e("startNew", "Unable to advertise peers");
        }
    }

    public void join(String moniker) {
        String ip = Utils.getIPAddr(this);

        // TODO: Remove hardcoded IP
        this.getPeers("");

        Service.getInstance().configureJoin(this.gPeers, this.cPeers, moniker, ip);
        Service.getInstance().start();
    }


    // RESPONSE LISTENER
    private void getPeers(final String peerIP) {
        HttpPeerDiscoveryRequest httpGenesisPeerDiscoveryRequest;

        try {
            httpGenesisPeerDiscoveryRequest =
                    HttpPeerDiscoveryRequest.createGenesisPeersRequest(
                            peerIP,
                            Service.DEFAULT_DISCOVERY_PORT,
                            new ResponseListener() {
                                @Override
                                public void onReceivePeers(List<Peer> genesisPeers) {
                                    gPeers = genesisPeers;

                                    httpCurrentPeerDiscoveryRequest =
                                            HttpPeerDiscoveryRequest.createCurrentPeersRequest(
                                                    peerIP,
                                                    Service.DEFAULT_DISCOVERY_PORT,
                                                    LobbyActivity.this,
                                                    LobbyActivity.this);

                                    httpCurrentPeerDiscoveryRequest.send();
                                }

                                @Override
                                public void onFailure(Error error) {
                                    this.onFailure(error);
                                }
                            }, this);
        } catch (IllegalArgumentException ex) {
            return;
        }

        httpGenesisPeerDiscoveryRequest.send();
    }


    @Override
    public void onReceivePeers(List<Peer> currentPeers) {
        this.cPeers = currentPeers;
    }

    @Override
    public void onFailure(io.mosaicnetworks.babble.discovery.ResponseListener.Error error) {
        int messageId;

        switch (error) {
            case INVALID_JSON:
                messageId = R.string.peers_json_error_alert_message;
                break;
            case CONNECTION_ERROR:
                messageId = R.string.peers_connection_error_alert_message;
                break;
            case TIMEOUT:
                messageId = R.string.peers_timeout_error_alert_message;
                break;
            default:
                messageId = R.string.peers_unknown_error_alert_message;
        }

        Log.e("onFailure: ", "" + messageId);
    }

    @Override
    public void stateUpdated() {
        Player p1 = Service.getInstance().state.getPlayer(1);
        Player p2 = Service.getInstance().state.getPlayer(2);

       if (p1 != null){
           ((TextView)findViewById(R.id.txtP1)).setText(p1.getMoniker());
       }

       if (p2 != null) {
           ((TextView)findViewById(R.id.txtP2)).setText(p2.getMoniker());
       }
    }
}