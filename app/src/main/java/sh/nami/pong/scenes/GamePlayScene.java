package sh.nami.pong.scenes;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.view.MotionEvent;
import android.widget.Toast;

import sh.nami.pong.babble.Service;
import sh.nami.pong.models.Player;


public class GamePlayScene implements Scene {

    private Grid grid;
    private Resources res;
    private Handler handler;

    private Context context;

    GamePlayScene(Context context, Resources res) {
        this.context = context;
        this.res = res;
        this.grid = new Grid(BitmapFactory.decodeResource(res, R.drawable.tile));
        this.handler = new Handler();

        Player p1 = new Player(getRandomSprite(), Service.getInstance().state.getMoniker(), 10, 10);
        Service.addPlayer(p1);

        Player p2 = new Player(getRandomSprite(), "Player9999", 10, 10);
        Service.addPlayer(p2);
    }

    private Bitmap getRandomSprite() {
        TypedArray images = this.res.obtainTypedArray(R.array.avatars);

        int choice = (int) (Math.random() * images.length());

        Bitmap image =  BitmapFactory.decodeResource(this.res, images.getResourceId(choice, R.drawable.bad1));
        images.recycle();

        return image;
    }

    private Bitmap createSprite(int resource) {
        return BitmapFactory.decodeResource(this.res, resource);
    }

    // TODO: This bugs out the sprites -> Perhaps something to do with UI threading
    private void sendNotification(final String notification) {
        handler.post(new Runnable(){
            public void run(){
                Toast.makeText(context, notification, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void update() {
        grid.update();

        for(Player player : Service.getInstance().state.getPlayers().values()) {
            player.update();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        if(canvas != null) {
            grid.draw(canvas);
            for(Player player : Service.getInstance().state.getPlayers().values()) {
                player.draw(canvas);
            }
        }
    }

    @Override
    public void terminate() {
        SceneManager.setActiveScene(0);
    }

    @Override
    public void receiveTouch(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {

        }
    }
}
