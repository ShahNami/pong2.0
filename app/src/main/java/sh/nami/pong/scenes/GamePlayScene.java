package sh.nami.pong.scenes;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;

import sh.nami.pong.Constants;
import sh.nami.pong.R;
import sh.nami.pong.babble.Service;
import sh.nami.pong.models.Ball;
import sh.nami.pong.models.Hit;
import sh.nami.pong.models.Move;
import sh.nami.pong.models.NVector;
import sh.nami.pong.models.Player;
import sh.nami.pong.models.Vector;
import sh.nami.pong.sprites.Grid;


public class GamePlayScene implements Scene {

    private Grid grid;

    GamePlayScene(Context context, Resources res) {
        this.grid = new Grid(BitmapFactory.decodeResource(res, R.drawable.tile));

        Player p1 = Service.getInstance().state.getPlayer(1);

        Bitmap ball = BitmapFactory.decodeResource(res, R.drawable.ball);

        Vector ballPos = new Vector(p1.getPosition().getX() + p1.getWidth(), Constants.screenHeight / 2 - ball.getHeight()/2);
        Ball b = new Ball(ball, ballPos, new NVector(1, 1));

        Hit hit = new Hit(new NVector(1, 0));

        Service.getInstance().startGame(b);
        Service.getInstance().hitBall(hit);
    }

    @Override
    public void update() {
        grid.update();

        Player p1 = Service.getInstance().state.getPlayer(1);
        Player p2 = Service.getInstance().state.getPlayer(2);

        Ball b = Service.getInstance().state.getBall();

        if(b != null) {
            b.update();
        }

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
            Ball b = Service.getInstance().state.getBall();

            if(b != null) {
                b.draw(canvas);
            }

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
            Player p1 = Service.getInstance().state.getPlayer(1);
            Vector draggedPosition = new Vector(p1.getPosition().getX(), (int)event.getY());
            draggedPosition.setY((int)event.getY() - (p1.getHeight()/2));
            Move move = new Move(draggedPosition);
            Service.getInstance().movePlayer(move);
        }
    }
}
