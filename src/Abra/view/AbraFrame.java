package Abra.view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class AbraFrame extends JFrame {
	
	public static final int DEFAULT_WIDTH = 1200;
	public static final int DEFAULT_HEIGHT = 800;
	public static final String title = "Abra快递面单处理辅助系统";
	public static final String version = "V1.0";
	private static ControlPanel controlPanel = new ControlPanel();
	private static ClientPanel clientPanel = new ClientPanel();
	private static PicPanel picPanel = new PicPanel();
	private static ConsolePanel consolePanel = new ConsolePanel();
	
	public AbraFrame() {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setLocationRelativeTo(null);
		setLayout(null);
		setTitle(title + " " + version);
		setResizable(false);
		setIconImage(new ImageIcon("abra.png").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(clientPanel);
//		add(controlPanel);
//		add(picPanel);
//		add(consolePanel);
		clientPanel.setBounds(5, 5, 1185, 760);
//		controlPanel.setBounds(55, 0, 1000, 95);
//		picPanel.setBounds(55, 155, 1000, 700);
//		consolePanel.setBounds(1060, 0, 140, 800);
	}

}
