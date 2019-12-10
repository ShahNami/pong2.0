package sh.nami.pong.sprites;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import sh.nami.pong.Constants;

public class WallSprite extends Sprite {
    public WallSprite(Bitmap image) {
        super(image);
    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStrokeWidth(10);
        paint.setColor(Color.CYAN);

        Rect wall = new Rect(this.getPosition().getX(), this.getPosition().getY(), this.getPosition().getX() + 10, Constants.screenHeight);
        canvas.drawRect(wall, paint);
    }
}
