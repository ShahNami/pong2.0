package sh.nami.pong.scenes;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;

import sh.nami.pong.R;
import sh.nami.pong.babble.Service;
import sh.nami.pong.models.Player;
import sh.nami.pong.sprites.Grid;


public class GamePlayScene implements Scene {

    private Grid grid;

    GamePlayScene(Context context, Resources res) {
        this.grid = new Grid(BitmapFactory.decodeResource(res, R.drawable.tile));

        Service.getInstance().startGame();
    }

    @Override
    public void update() {
        grid.update();

        Player p1 = Service.getInstance().state.getPlayer(1);
        Player p2 = Service.getInstance().state.getPlayer(2);

        if(p1 != null && p2 != null) {
            p1.update();
            p2.update();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        if(canvas != null) {
            grid.draw(canvas);
            Player p1 = Service.getInstance().state.getPlayer(1);
            Player p2 = Service.getInstance().state.getPlayer(2);

            if(p1 != null && p2 != null) {
                p1.draw(canvas);
                p2.draw(canvas);
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
