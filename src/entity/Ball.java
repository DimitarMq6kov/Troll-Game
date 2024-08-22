package entity;

import entity.Player;

import utils.Direction;

import java.awt.*;
import java.util.Random;

public class Ball extends Entity {

    private Player player;

    private float ballSpeed = 3f;
    private Random rand = new Random();
    private Direction direction;

    public Ball(float posX, float posY, Player player) {
        super(posX, posY);
        this.player = player;
    }

    public boolean areColliding(float x1, float y1, float w1, float h1, float x2, float y2, float w2, float h2) {
        if (x2 <= x1 + w1 && y2 <= y1 + h1 && x2 + w2 >= x1 && y2 + h2 >= y1) {
            return true;
        }
        return false;
    }

    public void move() {

        if (areColliding(this.posX, this.posY, 25, 25, player.getPosX(), player.getPosY(), 15, player.getSize())) {
           if (this.posX <= player.getPosX()) {
               if (Math.floor(Math.random()) == 0) {
                   this.direction = Direction.DOWNRIGHT;
               } else if (Math.floor(Math.random()) == 1) {
                   this.direction = Direction.UPRIGHT;
               }
           }
        } else if (posY >= 720) {
            if (Math.round(Math.random()) == 0) {
                direction = Direction.UPRIGHT;
            } else if (Math.round(Math.random()) == 1) {
                direction = Direction.UPLEFT;
            }
        } else if (posY <= 0) {
            if (Math.round(Math.random()) == 0) {
                direction = Direction.DOWNRIGHT;
            } else if (Math.round(Math.random()) == 1) {
                direction = Direction.DOWNLEFT;
            }
        } else if (posX >= 1280) {
            if (Math.round(Math.random()) == 0) {
                direction = Direction.UPLEFT;
            } else if (Math.round(Math.random()) == 1) {
                direction = Direction.DOWNLEFT;
            }
        }

        if (direction == Direction.DOWNLEFT) {
            posX -= ballSpeed;
            posY += ballSpeed;
        } else if (direction == Direction.UPLEFT) {
            posX -= ballSpeed;
            posY -= ballSpeed;
        } else if (direction == Direction.DOWNRIGHT) {
            posX += ballSpeed;
            posY += ballSpeed;
        } else if (direction == Direction.UPRIGHT) {
            posX += ballSpeed;
            posY -= ballSpeed;
        }

    }

    public void render(Graphics g) {

        g.setColor(new Color(255, 0, 255));
        g.fillOval((int) posX, (int) posY, 25, 25);
    }

    public float getBallSpeed() {
        return ballSpeed;
    }

    public void setBallSpeed(float ballSpeed) {
        this.ballSpeed = ballSpeed;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
