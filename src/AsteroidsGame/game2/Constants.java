package AsteroidsGame.game2;

import AsteroidsGame.utilities.JEasyFrameFull;

import java.awt.*;


class Constants {
    static final int FRAME_HEIGHT = /*525*/ JEasyFrameFull.HEIGHT;
    static final int FRAME_WIDTH = /*1023*/ JEasyFrameFull.WIDTH;
    static final Dimension FRAME_SIZE = new Dimension(
            Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
    // sleep time between two frames
    static final int DELAY = 20;  // in milliseconds
    static final double DT = DELAY / 1000.0;  // in seconds
}
