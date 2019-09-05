package AsteroidsGame.game2;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import static AsteroidsGame.game2.Constants.*;

class View extends JComponent{
    private static final Color FG_COLOR = Color.white;

    private Game game;
    private Image im = Sprite.MILKYWAY4;
    private AffineTransform bgTransf;

    View(Game game) {
        this.game = game;
        double imWidth = im.getWidth(null);
        double imHeight = im.getHeight(null);
        double stretchx = (imWidth >  FRAME_WIDTH? 1 :
                FRAME_WIDTH/imWidth);
        double stretchy = (imHeight > FRAME_HEIGHT? 1 :
                FRAME_HEIGHT/imHeight);
        bgTransf = new AffineTransform();
        bgTransf.scale(stretchx, stretchy);
    }

    @Override
    public void paintComponent(Graphics g0) {
        Graphics2D g = (Graphics2D) g0;
        g.drawImage(im, bgTransf, null);
        g.setColor(FG_COLOR);
        g.drawString("Score: " + game.getScore(), 20, 20);
        g.drawString("Lives: " + game.getLives(), 100, 20);
        g.drawString("Level: " + (game.getLevels() + 1) , 180, 20);
        /*g.scale(1.5, 1.5);
        g.drawString("GAME OVER! FINAL SCORE: " + game.getScore(), FRAME_WIDTH/4 - 30, FRAME_HEIGHT/4);
        g.scale( 1d/1.5d, 1d/1.5d);*/
        if(Game.gameOver) {
            g.scale(1.5, 1.5);
            g.drawString("GAME OVER, FINAL SCORE: " + game.getScore(), FRAME_WIDTH/3-80, FRAME_HEIGHT/3);
            g.scale( 1d/1.5d, 1d/1.5d);
        }
        if(game.isInvincible()) g.drawString("Invincible", 260, 20);
        synchronized (Game.class){
            for (GameObject object : game.objects)
                object.draw(g);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return Constants.FRAME_SIZE;
    }
}
