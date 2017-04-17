package Abra.view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import Abra.Flyway;

public class AbraFrame extends JFrame {
	
	public static final int DEFAULT_WIDTH = 1200;
	public static final int DEFAULT_HEIGHT = 800;
	public static final String title = "Abra快递面单处理辅助系统";
	public static final String version = "V1.0";
	private static ControlPanel controlPanel;
	private static ClientPanel clientPanel;
	private static PicPanel picPanel;
	private static ConsolePanel consolePanel;
	
	public AbraFrame(Flyway flyway) {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setLocationRelativeTo(null);
		setLayout(null);
		setTitle(title + " " + version);
		setResizable(false);
		setIconImage(new ImageIcon("abra.png").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		clientPanel = new ClientPanel(flyway.getClientsList());
		consolePanel = new ConsolePanel();
		controlPanel = new ControlPanel();
		picPanel = new PicPanel();
		add(clientPanel);
		add(controlPanel);
		add(picPanel);
		add(consolePanel);
		clientPanel.setBounds(5, 5, 100, 760);
		controlPanel.setBounds(110, 5, 825, 175);
		picPanel.setBounds(110, 185, 825, 580);
		consolePanel.setBounds(940, 5, 250, 760);
	}

}
