package sh.nami.pong.sprites;

import android.graphics.Bitmap;

public abstract class PaddleSprite extends Sprite {

    public PaddleSprite(Bitmap image) {
        super(image);
    }

    public abstract void update();
}
