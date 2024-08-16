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

    private boolean isMovingLeft;
    private boolean isMovingRight;
    private boolean isMovingUp;
    private boolean isMovingDown;
    private boolean isMoving = false;

    private int playerAction = 0;
    private int animationTick, animationIndex, animationSpeed = 20;
    private BufferedImage[][] animations;


//    Global Vars ( already set )

    private int hp = 6;
    private int atk = 2;
    private int mp = 10;
    private int stamina = 20;
    private float movementSpeed = 1.5f;
    private int sprintSpeedMultiplayer = 2;
    private boolean isSprinting = false;
    private int moveCameraToPlayer = KeyEvent.VK_CLOSE_BRACKET;


    public Player(float posX, float posY) {
        super(posX, posY);
    }

    public void move() {
            if (this.isSprinting && this.isMovingUp) posY -= this.movementSpeed * this.sprintSpeedMultiplayer;
            else if (isMovingUp) posY -= this.movementSpeed;

            if (this.isSprinting && this.isMovingDown) posY += this.movementSpeed * this.sprintSpeedMultiplayer;
            else if (isMovingDown) posY += this.movementSpeed;

            if (this.isMovingUp && posY <= 0) {
                posY = 629;
            }

            if (this.isMovingDown && posY >= 620) {
                posY = 1;
            }
    }

    public void render(Graphics g) {

        g.setColor(Color.WHITE);
        g.fillRect((int) this.posX, (int) this.posY, 15, 100);
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

    public boolean isMovingLeft() {
        return isMovingLeft;
    }
    public void setMovingLeft(boolean movingLeft) {
        isMovingLeft = movingLeft;
    }

    public boolean isMovingRight() {
        return isMovingRight;
    }
    public void setMovingRight(boolean movingRight) {
        isMovingRight = movingRight;
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

    //endregion
}
