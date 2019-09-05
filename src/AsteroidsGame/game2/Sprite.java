package AsteroidsGame.game2;

import AsteroidsGame.utilities.Vector2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.IOException;

class Sprite {
	static Image ASTEROID1, MILKYWAY4, BULLET, BOMB;
	static {
		try {
			ASTEROID1 = ImageManager.loadImage("asteroid1");
			MILKYWAY4 = ImageManager.loadImage("milkyway4");
            BULLET = ImageManager.loadImage("bullet3");
            BOMB = ImageManager.loadImage("mine");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private Image image;
	public Vector2D position;
	private Vector2D direction;
	private double width;
	private double height;

	Sprite(Image image, Vector2D s, Vector2D direction, double width,
		   double height) {
		super();
		this.image = image;
		this.position = s;
		this.direction = direction;
		this.width = width;
		this.height = height;
	}

	void draw(Graphics2D g) {
		double imW = image.getWidth(null);
		double imH = image.getHeight(null);
		AffineTransform t = new AffineTransform();
		t.rotate(direction.angle(), 0, 0);
		t.scale(width / imW, height / imH);
		t.translate(-imW / 2.0, -imH / 2.0);
		AffineTransform t0 = g.getTransform();
		g.translate(position.x, position.y);
		g.drawImage(image, t, null);
		g.setTransform(t0);
		g.setColor(Color.RED);
	}

}
