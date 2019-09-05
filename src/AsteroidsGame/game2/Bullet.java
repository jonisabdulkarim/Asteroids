package AsteroidsGame.game2;

import AsteroidsGame.utilities.Vector2D;

import java.awt.*;

import static AsteroidsGame.game2.Sprite.BULLET;

class Bullet extends GameObject{
    private static final int RADIUS = 5;
    private static final int MAX_SPEED = 250;
    private static final int TIME = 100;
    private int timer = 0;
    private Sprite sprite;

    Bullet(Vector2D pos, Vector2D vel){
        super(pos, vel, null, RADIUS);
        this.sprite = new Sprite(BULLET, position, new Vector2D(0, 0), radius, radius);
    }

    public void update(){
        if(velocity.mag() > MAX_SPEED)
            velocity.subtract(velocity.x * 0.01, velocity.y * 0.01);
        super.update();
        timer++;
        if (timer == TIME){
            timer = 0;
            this.dead = true;
        }
    }

    public void draw(Graphics2D g){
        sprite.draw(g);
    }

    public void hit() {
        super.hit();
    }
}
