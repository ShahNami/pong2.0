package sh.nami.pong.sprites;

import android.graphics.Bitmap;

public abstract class BallSprite extends Sprite {

    public BallSprite(Bitmap image) {
        super(image);
    }

    public abstract void update();

}
