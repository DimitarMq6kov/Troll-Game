package entity;

import utils.Direction;

import java.awt.*;
import java.util.Random;

public class Ball extends Entity {

    private float ballSpeed = 3f;
    private Random rand = new Random();
    private Direction direction;
    public Ball (float posX, float posY) {
        super(posX, posY);

    }

    public void move() {
        if(posY >= 720) {
            if(Math.floor(Math.random()*2) == 0) {
                direction = Direction.UPRIGHT;
            } else if(Math.floor(Math.random()*2) == 1) {
                direction = Direction.UPLEFT;
            }
        } else if (posY <= 0) {
            if(rand.nextInt(2) == 0) {
                direction = Direction.DOWNRIGHT;
            } else if(rand.nextInt(2) == 1) {
                direction = Direction.DOWNLEFT;
            }
        } else if(posX >= 1280) {
            if(rand.nextInt(2) == 0) {
                direction = Direction.UPRIGHT;
            } else if(rand.nextInt(2) == 1) {
                direction = Direction.DOWNRIGHT;
            }
        } else if(posX <= 0) {
            if(rand.nextInt(2) == 0) {
                direction = Direction.UPLEFT;
            } else if(rand.nextInt(2) == 1) {
                direction = Direction.DOWNLEFT;
            }
        }

        if(direction == Direction.DOWNRIGHT) {
            posX -= ballSpeed;
            posY += ballSpeed;
        } else if(direction == Direction.UPRIGHT) {
            posX -= ballSpeed;
            posY -= ballSpeed;
        } else if(direction == Direction.DOWNLEFT) {
            posX += ballSpeed;
            posY += ballSpeed;
        } else if(direction == Direction.UPLEFT) {
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
