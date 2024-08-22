package entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utils.Constants.PlayerConstants.*;
import static utils.Constants.Directions.*;

public class Player extends Entity {

    private boolean isMovingUp;
    private boolean isMovingDown;
    private boolean isMoving = false;

    private int playerAction = 0;
    private int animationTick, animationIndex, animationSpeed = 20;
    private BufferedImage[][] animations;

//    Global Vars ( already set )

    private float movementSpeed = 1.5f;
    private int sprintSpeedMultiplayer = 2;
    private boolean isSprinting = false;
    private float size = 100f;
    private float reductionSpeed = 0.5f;


    public Player(float posX, float posY) {
        super(posX, posY);
    }

    public void move() {
            if (this.isSprinting) {
                reductionSpeed = 1f;
            }

            if (this.isSprinting && this.isMovingUp) {
                posY -= this.movementSpeed * this.sprintSpeedMultiplayer;
            } else if (isMovingUp) {
                posY -= this.movementSpeed;
            }

            if (this.isSprinting && this.isMovingDown) {
                posY += this.movementSpeed * this.sprintSpeedMultiplayer;
            } else if (isMovingDown) {
                posY += this.movementSpeed;
            }

            if (this.isMovingUp && posY <= 0) {
                posY = 719 - size;
            }

            if (this.isMovingDown && posY >= 720 - this.size) {
                posY = 1;
            }

            if((isMovingUp || isMovingDown) && size >= 30) {
                size -= reductionSpeed / 2;
            } else if (size < 30) {
                size = 30;
            }
    }

    public void render(Graphics g) {

        g.setColor(new Color(255, 255, 255));
        g.fillRect((int) this.posX, (int) this.posY, 15, (int) this.size);
    }

    public void update() {
        this.move();
    }

    //region get/set region
    public boolean isMovingUp() {
        return isMovingUp;
    }
    public void setMovingUp(boolean movingUp) {
        isMovingUp = movingUp;
    }

    public boolean isMovingDown() {
        return isMovingDown;
    }
    public void setMovingDown(boolean movingDown) {
        isMovingDown = movingDown;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public boolean isSprinting() {
        return isSprinting;
    }

    public void setSprinting(boolean sprinting) {
        isSprinting = sprinting;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    //endregion
}
