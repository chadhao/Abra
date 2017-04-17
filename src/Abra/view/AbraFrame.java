package Abra.view;

import java.awt.CardLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class AbraFrame extends JFrame {
	
	public static final int DEFAULT_WIDTH = 450;
	public static final int DEFAULT_HEIGHT = 300;
	public static final String title = "Abra快递面单处理辅助系统";
	public static final String version = "V0.1";
	public static final CardLayout CARD_LAYOUT = new CardLayout();
	
	public AbraFrame() {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setLocationRelativeTo(null);
		setLayout(CARD_LAYOUT);
		setTitle(title + " " + version);
		setResizable(false);
		setIconImage(new ImageIcon("abra.png").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
