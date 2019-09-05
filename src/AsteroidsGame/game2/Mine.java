package AsteroidsGame.game2;

import AsteroidsGame.utilities.Vector2D;
import static AsteroidsGame.game2.Constants.*;
import static AsteroidsGame.game2.Sprite.BOMB;

import java.awt.*;

class Mine extends GameObject {
    private static final int STARTING_RADIUS = 60;
    private Sprite sprite;
    static boolean explode = false;

    private Mine(double x, double y, double vx, double vy) {
        this(x, y, vx, vy, STARTING_RADIUS);
    }

    private Mine(double x, double y, double vx, double vy, int radius){
        super(new Vector2D(x, y), new Vector2D(vx, vy), null,radius);
        this.sprite = new Sprite(BOMB, position, new Vector2D(0, 0),  radius, radius);
    }

    static Mine makeRandomMine() {
        double x = Math.random()*FRAME_WIDTH;
        double y = Math.random()*FRAME_HEIGHT;
        return new Mine(x, y, 0, 0);
    }

    public void update() {
        super.update();
    }

    public void hit() {
        explode = true;
        super.hit();
        SoundManager.bomb();
    }

    public void draw(Graphics2D g) {
        sprite.draw(g);
    }
}
