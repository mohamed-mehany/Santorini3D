package eg.edu.guc.santorini.gui;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class BaseFrame extends JFrame {

	public static final int WIDTH = 1024;
	public static final int HEIGHT = 768;

	public static final ImageIcon CUBE_ICON_1 = new ImageIcon("C1.png");
	public static final ImageIcon PYRAMID_ICON_1 = new ImageIcon("P1.png");
	public static final ImageIcon CUBE_ICON_2 = new ImageIcon("C2.png");
	public static final ImageIcon PYRAMID_ICON_2 = new ImageIcon("P2.png");

	public BaseFrame(String title) {
		super(title);
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(new ImageIcon("icon.png").getImage());
		setLayout(new BorderLayout());
	}
}