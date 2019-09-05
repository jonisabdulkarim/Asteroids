package AsteroidsGame.game2;

import AsteroidsGame.utilities.Vector2D;

import java.awt.*;
import java.util.ArrayList;

import static AsteroidsGame.game2.Constants.FRAME_HEIGHT;
import static AsteroidsGame.game2.Constants.FRAME_WIDTH;
import static AsteroidsGame.game2.Sprite.ASTEROID1;

class Asteroid extends GameObject {
    private static final int STARTING_RADIUS = 60;
    private static final int MEDIUM_RADIUS = 45;
    private static final int SMALL_RADIUS = 30;
    private static final int MAX_SPEED = 200;
    ArrayList<Asteroid> spawnedAsteroids;
    private Sprite sprite;

    private Asteroid(double x, double y, double vx, double vy) {
        this(x, y, vx, vy, STARTING_RADIUS);
    }

    private Asteroid(double x, double y, double vx, double vy, int radius){
        super(new Vector2D(x, y), new Vector2D(vx, vy), null,radius);
        this.sprite = new Sprite(ASTEROID1, position, new Vector2D(0, 0), radius, radius);
    }

    static Asteroid makeRandomAsteroid() {
        double x = Math.random()*FRAME_WIDTH;
        double y = Math.random()*FRAME_HEIGHT;
        double vx = Math.random()*MAX_SPEED*2-MAX_SPEED;
        double vy = Math.random()*MAX_SPEED*2-MAX_SPEED;
        return new Asteroid(x, y, vx, vy);
    }

    public void update() {
        super.update();
    }

    public void hit() {
        super.hit();
        spawnedAsteroids = new ArrayList<>();
        if (this.radius == STARTING_RADIUS) {
            SoundManager.asteroidsLarge();
            spawnedAsteroids.add(mkAsteroidMedium(this.position.x, this.position.y,
                    this.velocity.x * Math.cos(90) - this.velocity.y * Math.sin(90),
                    this.velocity.x * Math.sin(90) + this.velocity.y * Math.cos(90)));
            spawnedAsteroids.add(mkAsteroidMedium(this.position.x, this.position.y,
                    this.velocity.x * Math.cos(270) - this.velocity.y * Math.sin(270),
                    this.velocity.x * Math.sin(270) + this.velocity.y * Math.cos(270)));

        }
        else if (this.radius == MEDIUM_RADIUS) {
            SoundManager.asteroidsMedium();
            spawnedAsteroids.add(mkAsteroidSmall(this.position.x, this.position.y,
                    this.velocity.x * Math.cos(90) - this.velocity.y * Math.sin(90),
                    this.velocity.x * Math.sin(90) + this.velocity.y * Math.cos(90)));
            spawnedAsteroids.add(mkAsteroidSmall(this.position.x, this.position.y,
                    this.velocity.x * Math.cos(270) - this.velocity.y * Math.sin(270),
                    this.velocity.x * Math.sin(270) + this.velocity.y * Math.cos(270)));
        }
        else{
            SoundManager.asteroidsSmall();
        }
    }

    private static Asteroid mkAsteroidMedium(double x, double y, double vx, double vy){
        return new Asteroid(x, y, vx, vy, MEDIUM_RADIUS);
    }

    private static Asteroid mkAsteroidSmall(double x, double y, double vx, double vy){
        return new Asteroid(x, y, vx, vy, SMALL_RADIUS);
    }

    public void draw(Graphics2D g) {
        sprite.draw(g);
    }
}
