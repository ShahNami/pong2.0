package sh.nami.pong.sprites;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import sh.nami.pong.models.Vector;

public class Sprite {
    private Bitmap image;
    private Vector position;

    public Sprite(Bitmap image) {
        this.image = image;
    }

    public int getWidth() {
        return this.image.getWidth();
    }

    public int getHeight() {
        return this.image.getHeight();
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(this.image, this.position.getX(), this.position.getY(), null);
    }

    public void update() {

    }

    public Bitmap getImage() {
        return this.image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public Vector getPosition() {
        return this.position;
    }

    public void setPosition(Vector vector) {
        this.position = vector;
    }
}
