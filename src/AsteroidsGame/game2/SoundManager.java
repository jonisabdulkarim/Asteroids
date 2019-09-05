package AsteroidsGame.game2;

// change package name to fit your own package structure!

import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;
import java.io.File;

// SoundManager for Asteroids

public class SoundManager {

	private static int nBullet = 0;
	private static boolean thrusting = false;

	// this may need modifying
	private final static String path = "sounds/";

	// note: having too many clips open may cause
	// "LineUnavailableException: No Free Voices"
	private final static Clip[] bullets = new Clip[15];

	private final static Clip bangLarge = getClip("bangLarge");
	private final static Clip bangMedium = getClip("bangMedium");
	private final static Clip bangSmall = getClip("bangSmall");
	private final static Clip beat1 = getClip("beat1");
	private final static Clip beat2 = getClip("beat2");
	private final static Clip extraShip = getClip("extraShip");
	private final static Clip fire = getClip("fire");
	private final static Clip saucerBig = getClip("saucerBig");
	private final static Clip saucerSmall = getClip("saucerSmall");
	private final static Clip thrust = getClip("thrust");
	private final static Clip bomb = getClip("bomb");

	private final static Clip[] clips = {bangLarge, bangMedium, bangSmall, beat1, beat2,
			extraShip, fire, saucerBig, saucerSmall, thrust, bomb};

	static {
		for (int i = 0; i < bullets.length; i++)
			bullets[i] = getClip("fire");
	}

	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 20; i++) {
			fire();
			Thread.sleep(100);
		}
		for (Clip clip : clips) {
			play(clip);
			Thread.sleep(1000);
		}
	}

	// methods which do not modify any fields

	private static void play(Clip clip) {
		clip.setFramePosition(0);
		clip.start();
	}

	private static Clip getClip(String filename) {
		Clip clip = null;
		try {
			clip = AudioSystem.getClip();
			AudioInputStream sample = AudioSystem.getAudioInputStream(new File(path
					+ filename + ".wav"));
			clip.open(sample);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clip;
	}

	// methods which modify (static) fields

	static void fire() {
		// fire the n-th bullet and increment the index
		Clip clip = bullets[nBullet];
		clip.setFramePosition(0);
		clip.start();
		nBullet = (nBullet + 1) % bullets.length;
	}

	static void startThrust() {
		if (!thrusting) {
			thrust.loop(-1);
			thrusting = true;
		}
	}

	static void stopThrust() {
		thrust.loop(0);
		thrusting = false;
	}

	// Custom methods playing a particular sound
	// Please add your own methods below

	static void asteroidsLarge() {
		play(bangLarge);
	}
	static void asteroidsMedium() {
		play(bangMedium);
	}
	static void asteroidsSmall() {
		play(bangSmall);
	}
	static void extraShip() {
		play(extraShip);
	}
	static void bomb() {
		play(bomb); }

}
