package sh.nami.pong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

public class MainActivity extends Activity {

    private String moniker = "Player";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);
    }

    public void hostNode(View view) {
        Intent intent = new Intent(this, LobbyActivity.class);

        intent.putExtra(Constants.EXTRA_TYPE, 0);
        intent.putExtra(Constants.EXTRA_MONIKER, this.moniker);

        startActivity(intent);
    }

    public void joinNode(View view) {
        this.moniker = ((EditText)findViewById(R.id.moniker)).getText().toString();

        Intent intent = new Intent(this, LobbyActivity.class);

        intent.putExtra(Constants.EXTRA_TYPE, 1);
        intent.putExtra(Constants.EXTRA_MONIKER, this.moniker);

        startActivity(intent);
    }
}
