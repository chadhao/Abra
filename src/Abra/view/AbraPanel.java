package Abra.view;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Abra.Abra;

public class AbraPanel extends JComponent {
	
	private Image bgImage;
	private JButton loadDir;
	private JProgressBar progressBar;
	private JScrollPane responsePane;
	private JTextArea response;
	private JLabel copyright;
	
	public AbraPanel() {
		bgImage = null;
		init();
	}
	
	public AbraPanel(String imgSrc) {
		bgImage = new ImageIcon(imgSrc).getImage();
		init();
	}
	
	private void init() {
		setLayout(null);
		
		loadDir = new JButton("选择目录");
		progressBar = new JProgressBar(0, 100);
		response = new JTextArea();
		response.setEditable(false);
		response.setOpaque(false);
		responsePane = new JScrollPane(response);
		responsePane.setOpaque(false);
		responsePane.getViewport().setOpaque(false);
		copyright= new JLabel("Made by Chad", JLabel.RIGHT);
		
		add(loadDir);
		add(progressBar);
		add(responsePane);
		add(copyright);
		
		loadDir.setBounds(10, 10, 100, 30);
		progressBar.setBounds(115, 10, 320, 30);
		responsePane.setBounds(10, 50, AbraFrame.DEFAULT_WIDTH-25, 200);
		copyright.setBounds(10, 250, AbraFrame.DEFAULT_WIDTH-25, 20);
		
		loadDir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileChooser.showOpenDialog(null);
				File dir = fileChooser.getSelectedFile();
				if (dir != null && Abra.setPath(dir.getAbsolutePath())) {
					loadDir.setEnabled(false);
					response.setText("");
					progressBar.setValue(0);
					progressBar.setStringPainted(true);
					addResponse("已选择目录：" + dir.getAbsolutePath());
					Abra.process();
				}
			}
		});
	}
	
	public void setProgress(int rate) {
		progressBar.setValue(rate);
	}
	
	public void addResponse(String msg) {
		response.setText(response.getText() + (response.getText().length()==0?"":"\n") + msg);
		response.setCaretPosition(response.getDocument().getLength());
	}
	
	public void setLoadDirEnabled(boolean isEnabled) {
		loadDir.setEnabled(isEnabled);
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(AbraFrame.DEFAULT_WIDTH, AbraFrame.DEFAULT_HEIGHT);
		
	}
	
	public void paintComponent(Graphics g) {
		if (bgImage != null) {
			AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f);
			Graphics2D g2d = (Graphics2D)g;
			g2d.setComposite(ac);
			g2d.drawImage(bgImage, AbraFrame.DEFAULT_WIDTH-bgImage.getWidth(null)-10, AbraFrame.DEFAULT_HEIGHT-bgImage.getHeight(null)-30, null);
			ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);
			g2d.setComposite(ac);
		}
	}
	
}
