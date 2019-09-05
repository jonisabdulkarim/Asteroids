package AsteroidsGame.game2;

import AsteroidsGame.utilities.Vector2D;
import static AsteroidsGame.game2.Constants.*;
import java.awt.*;

abstract class GameObject {
    public Vector2D position;
    int radius;
    Vector2D velocity;
    Vector2D direction;
    boolean dead;

    GameObject(Vector2D position, Vector2D velocity, Vector2D direction, int radius){
        this.position = position;
        this.velocity = velocity;
        this.direction = direction;
        this.radius = radius;
    }

    public void hit(){
        this.dead = true;
    }

    public void update(){
        position.addScaled(velocity, DT);
        position.wrap(FRAME_WIDTH, FRAME_HEIGHT);
    }

    public abstract void draw(Graphics2D g);

    private boolean overlap(GameObject other){
        return this.position.dist(other.position) < this.radius;
    }

    void areaHit(GameObject other){
        if(this.getClass() != other.getClass() && this.position.dist(other.position) < 500 && Mine.explode){
            this.hit();
            other.hit();
            Mine.explode = false;
        }
    }

    void collisionHandling(GameObject other){
        if(this.getClass() != other.getClass() && this.overlap(other)){
            this.hit();
            other.hit();
        }
    }
}
