package sh.nami.pong;

public class Constants {
    public static final String EXTRA_TYPE = "sh.nami.pong.TYPE";
    public static final String EXTRA_MONIKER = "sh.nami.pong.MONIKER";

    public static final long TARGET_FPS = 60;
    public enum Type {
        NEW_PLAYER,
        INIT_BALL
    }
    public static final int PADDLE_MARGIN = 30;
    public static final int PADDLE_HEIGHT = 50;
    public static final int PADDLE_WIDTH = 10;
}
