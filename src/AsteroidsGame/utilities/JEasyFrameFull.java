package AsteroidsGame.utilities;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import javax.swing.*;


public class JEasyFrameFull extends JFrame {
	private final static GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
	private final static GraphicsDevice device = env.getScreenDevices()[0];
	private static final Rectangle RECTANGLE = device.getDefaultConfiguration().getBounds();
	public static final int WIDTH = RECTANGLE.width; 
	public static final int HEIGHT = RECTANGLE.height;

	public JEasyFrameFull(Component comp) {
		super();
		getContentPane().add(BorderLayout.CENTER, comp);
		comp.setPreferredSize(new Dimension (WIDTH, HEIGHT)); 
		this.setUndecorated(true); 
		pack();
		this.setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		repaint();
	}
}
