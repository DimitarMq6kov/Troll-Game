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

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_W:
                this.player.setMoving(true);
                this.player.setMovingUp(true);

                this.pressedKeys.add(key);
                break;
            case KeyEvent.VK_S:
                this.player.setMoving(true);
                this.player.setMovingDown(true);
                this.pressedKeys.add(key);
                break;
            case KeyEvent.VK_A:
                this.player.setMoving(true);
                this.player.setMovingLeft(true);
                this.pressedKeys.add(key);
                break;
            case KeyEvent.VK_D:
                this.player.setMoving(true);
                this.player.setMovingRight(true);
                this.pressedKeys.add(key);
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

                this.pressedKeys.remove(this.pressedKeys.indexOf(key));

                if (this.pressedKeys.isEmpty()) this.player.setMoving(false);
                break;
            case KeyEvent.VK_S:
                this.player.setMovingDown(false);

                this.pressedKeys.remove(this.pressedKeys.indexOf(key));

                if (this.pressedKeys.isEmpty()) this.player.setMoving(false);
                break;
            case KeyEvent.VK_A:
                this.player.setMovingLeft(false);

                this.pressedKeys.remove(this.pressedKeys.indexOf(key));

                if (this.pressedKeys.isEmpty()) this.player.setMoving(false);
                break;
            case KeyEvent.VK_D:
                this.player.setMovingRight(false);

                this.pressedKeys.remove(this.pressedKeys.indexOf(key));

                if (this.pressedKeys.isEmpty()) this.player.setMoving(false);
                break;
        }

        if(key == KeyEvent.VK_SHIFT) {
            this.player.setSprinting(false);
        }
    }
}
