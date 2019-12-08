package sh.nami.pong.models;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

import sh.nami.pong.R;
import sh.nami.pong.sprites.PaddleSprite;

public class Player extends PaddleSprite {

    @SerializedName("moniker")
    private String moniker;
    @SerializedName("score")
    private int score = 0;

    public Player(String moniker, Vector position) {
        super(null);
        this.moniker = moniker;
        this.setPosition(position);
    }

    public String getMoniker() {
        return this.moniker;
    }

    public int getScore() {
        return this.score;
    }

    @Override
    public void update(){
        if(this.getImage() == null) {
            Log.e("Update Player", "Image is null");
            Bitmap paddle = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.paddle);
            this.setImage(paddle);
        }
    }

}
