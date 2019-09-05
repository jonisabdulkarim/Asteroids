
package AsteroidsGame.game2;

import AsteroidsGame.utilities.Vector2D;

import static AsteroidsGame.game2.Constants.DT;
import static AsteroidsGame.game2.PlayerShip.*;

abstract class Ship extends GameObject{
    Controller ctrl;
    Bullet bullet;
    private int bulletCounter = 0;

    Ship(Vector2D position, Vector2D velocity, Vector2D direction, int radius){
        super(position, velocity, direction ,radius);
    }

    public void update(){
        direction.rotate(ctrl.action().turn * STEER_RATE * DT);
        if(velocity.mag() < MAX_SPEED)
            velocity.addScaled(direction, MAG_ACC * DT * ctrl.action().thrust);
        if(velocity.mag() < 10)
            velocity.set(0, 0);
        else
            velocity.subtract(velocity.x * DRAG, velocity.y * DRAG);
        super.update();
        if(bulletCounter <= 0) {
            if (ctrl.action().shoot) {
                mkBullet();
                ctrl.action().shoot = false;
            }
        }
        if (bulletCounter > 0) bulletCounter--;
    }

    private void mkBullet(){
        Vector2D bulletPosition = new Vector2D(position).addScaled(new Vector2D(direction).normalise(), 20);
        bullet = new Bullet(bulletPosition, new Vector2D(velocity));
        bullet.velocity.addScaled(direction, 100);
        SoundManager.fire();
        bulletCounter += 30;
    }
}

