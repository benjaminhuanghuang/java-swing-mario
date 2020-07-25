import ui.GameFrame;
import util.KeyListener;

/**
 */
public class Run {
    public static void main(String[] args) throws Exception {
        GameFrame gf = new GameFrame();
        KeyListener kl = new KeyListener(gf);
        gf.addKeyListener(kl);
    }
}
