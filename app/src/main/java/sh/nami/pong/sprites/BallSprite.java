package sh.nami.pong.sprites;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import sh.nami.pong.models.Vector;

public class BallSprite extends Sprite {
    private Bitmap image;
    private Vector position;

    public BallSprite(Bitmap image) {
        super(image);
    }

    @Override
    public int getWidth() {
        return this.image.getWidth();
    }

    @Override
    public int getHeight() {
        return this.image.getHeight();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, this.position.getX(), this.position.getY(), null);
    }

    @Override
    public void update() {

    }

    @Override
    public Vector getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Vector vector) {
        this.position = vector;
    }
}
