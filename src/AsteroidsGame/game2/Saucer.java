package AsteroidsGame.game2;

import AsteroidsGame.utilities.Vector2D;
import static AsteroidsGame.game2.Constants.*;

import java.awt.*;
import java.awt.geom.AffineTransform;

class Saucer extends Ship{
    private static final int RADIUS = 15;
    private static Color COLOR = null;
    private static final Color[] colorBelt = new Color[] {Color.RED, Color.GREEN, Color.YELLOW};
    private static final int[] XP = {0, -1, 0, 1};
    private static final int[] YP = {0, 1, -2, 1};
    private static final int DRAWING_SCALE = 10;

    Saucer(Controller ctrl){
        super(new Vector2D(), new Vector2D(), new Vector2D(), RADIUS);
        reset();
        this.ctrl = ctrl;
        COLOR = colorBelt[(int) (Math.random() * 3)];
    }


    private void reset(){
        position = new Vector2D(FRAME_WIDTH/4, FRAME_HEIGHT/2);
        velocity = new Vector2D(0, 0);
        direction = new Vector2D(3, 3);
    }

    public void draw(Graphics2D g) {
        g.setColor(COLOR);
        AffineTransform at = g.getTransform();
        g.translate(position.x, position.y);
        double rot = direction.angle() + Math.PI / 2;
        g.rotate(rot);
        g.scale(DRAWING_SCALE, DRAWING_SCALE);
        g.fillPolygon(XP, YP, XP.length);
        g.setTransform(at);
    }
}
