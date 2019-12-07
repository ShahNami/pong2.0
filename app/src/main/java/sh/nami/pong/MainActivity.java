package sh.nami.pong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import io.mosaicnetworks.babble.node.BabbleService;
import io.mosaicnetworks.babble.utils.Utils;
import sh.nami.pong.babble.Service;

public class MainActivity extends Activity {
    public static final String EXTRA_TYPE = "sh.nami.pong.TYPE";
    public static final String EXTRA_MONIKER = "sh.nami.pong.MONIKER";

    private String moniker = "Player";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);
    }

    public void hostNode(View view) {

        this.moniker = ((EditText)findViewById(R.id.moniker)).getText().toString();

        Service.getInstance().configureNew(moniker, Utils.getIPAddr(this));

        // Start instance
        Service.getInstance().start();

        if (Service.getInstance().getState() != BabbleService.State.RUNNING_WITH_DISCOVERY) {
            // Toast.makeText(this, "Unable to advertise peers", Toast.LENGTH_LONG).show();
            Log.e("startNew", "Unable to advertise peers");
        }

        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra(EXTRA_TYPE, 0);
        intent.putExtra(EXTRA_MONIKER, this.moniker);

        startActivity(intent);
    }

    public void joinNode(View view) {
//        this.moniker = ((EditText)findViewById(R.id.moniker)).getText().toString();
//
//        Intent intent = new Intent(this, LobbyActivity.class);
//        intent.putExtra(EXTRA_TYPE, 1);
//        intent.putExtra(EXTRA_MONIKER, this.moniker);
//        startActivity(intent);
    }
}
