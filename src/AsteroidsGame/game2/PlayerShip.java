package AsteroidsGame.game2;

import AsteroidsGame.utilities.Vector2D;

import java.awt.*;
import java.awt.geom.AffineTransform;

import static AsteroidsGame.game2.Constants.FRAME_HEIGHT;
import static AsteroidsGame.game2.Constants.FRAME_WIDTH;

class PlayerShip extends Ship{
    private static final int RADIUS = 10;
    private static final Color COLOR = Color.CYAN;
    private static final Color INV_COLOR = Color.WHITE;
    private static final int[] XP = {0, -1, 0, 1};
    private static final int[] YP = {0, 1, -2, 1};
    private static final int DRAWING_SCALE = 10;
    static final double STEER_RATE = 2* Math.PI;
    static final double MAG_ACC = 200;
    static final double DRAG = 0.01;
    static final int MAX_SPEED = 350;

    PlayerShip(Controller ctrl){
        super(new Vector2D(), new Vector2D(), new Vector2D(), RADIUS);
        reset();
        this.ctrl = ctrl;
    }

    private void reset(){
        position = new Vector2D(FRAME_WIDTH/2, FRAME_HEIGHT/2);
        velocity = new Vector2D(0, 0);
        direction = new Vector2D(3, 3);
    }

    public void draw(Graphics2D g) {
        if(Game.invincible){
            g.setColor(INV_COLOR);
        }
        else{
            g.setColor(COLOR);
        }
        AffineTransform at = g.getTransform();
        g.translate(position.x, position.y);
        double rot = direction.angle() + Math.PI / 2;
        g.rotate(rot);
        g.scale(DRAWING_SCALE, DRAWING_SCALE);
        g.fillPolygon(XP, YP, XP.length);
        if (ctrl.action().thrust == 1) {
            SoundManager.startThrust();
        }
        else SoundManager.stopThrust();
        g.setTransform(at);
    }
}
