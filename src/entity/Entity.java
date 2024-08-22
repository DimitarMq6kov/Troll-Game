package entity;

public abstract class Entity {
    protected float posX, posY;

    public Entity(float posX, float posY) {
        this.posX = posX;
        this.posY = posY;
    }

    //region get/set region
    public float getPosX() {
        return this.posX;
    }
    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
//        System.out.println(this.posY);
        return this.posY;
    }
    public void setPosY(float posY) {
        this.posY = posY;
    }
    //endregion
}
