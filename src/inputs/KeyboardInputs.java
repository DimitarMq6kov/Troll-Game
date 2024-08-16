package inputs;

import entity.Player;
import main.Panel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class KeyboardInputs implements KeyListener {

    private Panel panel;
    private Player player;

    private List<Integer> pressedKeys = new ArrayList<>();

    public KeyboardInputs(Panel panel) {
        this.panel = panel;
        this.player = panel.getPlayer();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("KeyTyped " + e.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_W:
                this.player.setMovingUp(true);

                break;
            case KeyEvent.VK_S:
                this.player.setMovingDown(true);
                break;
        }

        if(key == KeyEvent.VK_SHIFT && this.player.isMoving()) {
            this.player.setSprinting(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_W:
                this.player.setMovingUp(false);

                break;
            case KeyEvent.VK_S:
                this.player.setMovingDown(false);
                break;
        }

        if(key == KeyEvent.VK_SHIFT) {
            this.player.setSprinting(false);
        }
    }
}
