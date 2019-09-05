package AsteroidsGame.game2;

import AsteroidsGame.utilities.JEasyFrameFull;

import java.util.ArrayList;
import java.util.List;

import static AsteroidsGame.game2.Constants.DELAY;
import static AsteroidsGame.game2.Constants.FRAME_HEIGHT;
import static AsteroidsGame.game2.Constants.FRAME_WIDTH;

public class Game {
    private static final int N_INITIAL_ASTEROIDS = 1;
    private static final int N_INITIAL_MINES = 0;
    PlayerShip playerShip;
    Saucer saucer;
    private Keys ctrl;
    private int score;
    private int lives = 3;
    private int lifeLimit = 10000;
    private int extraAsteroids = 0;
    private int extraMines = 0;
    private int safeTime = 2000;
    static boolean invincible = false;
    static boolean gameOver = false;
    List<GameObject> objects;


    private Game() {
        objects = new ArrayList<>();
        for (int i = 0; i < N_INITIAL_ASTEROIDS; i++) {
            objects.add(Asteroid.makeRandomAsteroid());
        }
        ctrl = new Keys();
        playerShip = new PlayerShip(ctrl);
        saucer = new Saucer(new RotateNShoot());
        objects.add(playerShip);
        objects.add(saucer);
    }

    public static void main(String[] args) throws Exception {
        Game game = new Game();
        View view = new View(game);
        new JEasyFrameFull(view).addKeyListener(game.ctrl);
        while (true) {
            game.update();
            view.repaint();
            Thread.sleep(DELAY);
            if (gameOver) {
                new Thread(() -> {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.exit(0);
                }).start();
            }
        }
    }

    private void update() {
        List<GameObject> alive = new ArrayList<>();

        for (int i = 0; i < objects.size(); i++) {
            for (int j = i; j < objects.size(); j++) {
                if(objects.get(i) instanceof Mine
                        || objects.get(j) instanceof Mine){
                    objects.get(i).areaHit(objects.get(j));
                }
                if ((!(objects.get(i) instanceof PlayerShip ||
                        objects.get(j) instanceof PlayerShip) || !invincible))
                    objects.get(i).collisionHandling(objects.get(j));
            }
        }
        boolean empty = true;
        for (GameObject object : objects) {
            object.update();
            if (!object.dead) {
                alive.add(object);
            } else {
                if (object instanceof Asteroid) {
                    if (object.radius == 30) incScore(100);
                    else if (object.radius == 45) incScore(50);
                    else if (object.radius == 60) incScore(20);
                    Asteroid a = (Asteroid) object;
                    if (a.spawnedAsteroids != null) {
                        alive.addAll(a.spawnedAsteroids);
                    }
                }
            }

            if (object instanceof Asteroid) {
                empty = false;
            }


            if (!invincible) object.collisionHandling(playerShip);
            else safeTime--;
            if (safeTime <= 0) invincible = false;
            if (playerShip.dead && getLives() > 0) {
                decLives();
                invincible = true;
                safeTime = 2000;
                playerShip.position.set(FRAME_WIDTH / 2, FRAME_HEIGHT / 2);
                playerShip.dead = false;
            } else if (playerShip.dead) {
                gameOver = true;
            }
        }

        if(empty){
            invincible = true;
            safeTime = 2000;
            extraAsteroids++;
            extraMines++;
            for (int i = 0; i < N_INITIAL_ASTEROIDS + extraAsteroids; i++) {
                alive.add(Asteroid.makeRandomAsteroid());
            }
            for (int i = 0; i < N_INITIAL_MINES + extraMines; i++) {
                alive.add(Mine.makeRandomMine());
            }
            alive.add(new Saucer(new RotateNShoot()));
        }

        if (playerShip.bullet != null) {
            alive.add(playerShip.bullet);
            playerShip.bullet = null;
        }

        if (saucer.bullet != null) {
            alive.add(saucer.bullet);
            saucer.bullet = null;
        }

        synchronized (Game.class) {
            objects.clear();
            objects.addAll(alive);
        }

        if (score > lifeLimit) {
            SoundManager.extraShip();
            incLives();
            lifeLimit += 10000;
        }
    }

    private void incScore(int add) {
        score += add;
    }

    int getScore() {
        return score;
    }

    int getLives() {
        return lives;
    }

    private void incLives() {
        lives++;
    }

    private void decLives() {
        lives--;
    }

    boolean isInvincible() {
        return invincible;
    }

    int getLevels() { return extraAsteroids; }
}
