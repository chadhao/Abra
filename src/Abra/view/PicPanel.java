package Abra.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PicPanel extends JComponent {
	
	private static JPanel imgPanel;
	private static BorderLayout layout;
	
	public PicPanel() {
		layout = new BorderLayout();
		imgPanel = new JPanel(layout);
		imgPanel.setPreferredSize(new Dimension(825, 580));
		imgPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		setDefault();
		add(imgPanel);
		imgPanel.setBounds(0, 0, 825, 580);
	}
	
	public static void setDefault() {
		JLabel imgLabel = new JLabel("", new ImageIcon("abra.png"), JLabel.CENTER);
		JLabel textLabel = new JLabel("Made by Chad (me@chadhao.com)", JLabel.CENTER);
		imgPanel.add(imgLabel, BorderLayout.CENTER);
		imgPanel.add(textLabel, BorderLayout.SOUTH);
	}
	
	public static void setImage(BufferedImage img ) {
		imgPanel.removeAll();
		JLabel imgLabel = new JLabel("", new ImageIcon(img), JLabel.CENTER);
		imgPanel.add(imgLabel, BorderLayout.CENTER);
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(825, 580);
	}
}
