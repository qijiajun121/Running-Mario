import ui.window;
import util.keyListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class begin {
    public static void main(String[] args) {
        window win = new window();

        keyListener kLis = new keyListener(win);

        win.addKeyListener(kLis);

    }
}
