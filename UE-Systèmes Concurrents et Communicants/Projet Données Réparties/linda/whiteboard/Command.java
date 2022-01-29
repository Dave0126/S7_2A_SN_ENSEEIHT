package linda.whiteboard;

import java.util.*;

public class Command implements java.io.Serializable {
    enum Action { DRAW, ERASEALL, ROTATE, LOCK, UNLOCK };

    public Action action;
    public ColoredShape shape; // only if action = DRAW
    public int angle; // in degrees; only if action = ROTATE

    private Command() {}

    public static Command newCommandLock() {
        Command c = new Command();
        c.action = Action.LOCK;
        return c;
    }

    public static Command newCommandUnlock() {
        Command c = new Command();
        c.action = Action.UNLOCK;
        return c;
    }

    public static Command newCommandEraseAll() {
        Command c = new Command();
        c.action = Action.ERASEALL;
        return c;
    }

    public static Command newCommandDraw(ColoredShape rc) {
        Command c = new Command();
        c.action = Action.DRAW;
        c.shape = rc;
        return c;
    }

    public static Command newCommandRotateAll(int angle) {
        Command c = new Command();
        c.action = Action.ROTATE;
        c.angle = angle;
        return c;
    }

}
