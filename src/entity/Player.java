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
                posY = 629;
            }

            if (this.isMovingDown && posY >= 620) {
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

    private void loadAnimations() {
        InputStream inputStream = getClass().getResourceAsStream("/images/player_sprites.png");
        this.animations = new BufferedImage[12][16];

        try {
            BufferedImage img = ImageIO.read(inputStream);

            for (int i = 0; i < this.animations.length; i++)
                for (int j = 0; j < this.animations[i].length; j++)
                    this.animations[i][j] = img.getSubimage(j * 128, i * 128, 128, 128);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void setAnimation() {
        if (this.isSprinting && this.isMoving) this.playerAction = RUNNING;
        else if (this.isMoving) this.playerAction = WALKING;
        else this.playerAction = IDLE;
    }

    private void updateAnimationTick() {
        this.animationTick++;
        if (this.animationTick >= this.animationSpeed){
            this.animationTick = 0;
            this.animationIndex++;
            if (this.animationIndex >= GetSpriteAmount(this.playerAction))
                this.animationIndex = 0;
        }
    }

    //region get/set region
    public float getPosX() {
        return this.posX;
    }
    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }
    public void setPosY(float posY) {
        this.posY = posY;
    }

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
