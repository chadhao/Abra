package Abra.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

import Abra.Abra;

public class SettingContainer extends JComponent {
	
	private JButton scanSelect;
	private JTextField scanDir;
	private JButton workSelect;
	private JTextField workDir;
	private JButton packSelect;
	private JTextField packDir;
	
	public SettingContainer() {
		scanSelect = new JButton("选择扫描目录");
		workSelect = new JButton("选择工作目录");
		packSelect = new JButton("选择打包目录");
		scanDir = new JTextField();
		workDir = new JTextField();
		packDir = new JTextField();
		scanDir.setEditable(false);
		scanDir.setFocusable(false);
		workDir.setEditable(false);
		workDir.setFocusable(false);
		packDir.setEditable(false);
		packDir.setFocusable(false);
		
		add(scanSelect);
		add(scanDir);
		add(workSelect);
		add(workDir);
		add(packSelect);
		add(packDir);
		scanSelect.setBounds(5, 5, 120, 30);
		scanDir.setBounds(130, 5, 685, 30);
		workSelect.setBounds(5, 40, 120, 30);
		workDir.setBounds(130, 40, 685, 30);
		packSelect.setBounds(5, 75, 120, 30);
		packDir.setBounds(130, 75, 685, 30);
		
		scanSelect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileChooser.showOpenDialog(null);
				File dir = fileChooser.getSelectedFile();
				if (dir != null) {
					try {
						Abra.flyway.setScanDir(dir);
						scanDir.setText(dir.getAbsolutePath());
						ConsolePanel.addMsg("已选择扫描目录: " + dir.getAbsolutePath());
					} catch (FileNotFoundException e) {
						scanDir.setText("");
						ConsolePanel.addMsg("选择扫描目录失败！");
					}
				}
			}
		});
		
		workSelect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileChooser.showOpenDialog(null);
				File dir = fileChooser.getSelectedFile();
				if (dir != null) {
					try {
						Abra.flyway.setWorkDir(dir);
						workDir.setText(dir.getAbsolutePath());
						ConsolePanel.addMsg("已选择工作目录: " + dir.getAbsolutePath());
					} catch (FileNotFoundException e) {
						workDir.setText("");
						ConsolePanel.addMsg("选择工作目录失败！");
					}
				}
			}
		});
		
		packSelect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileChooser.showOpenDialog(null);
				File dir = fileChooser.getSelectedFile();
				if (dir != null) {
					try {
						Abra.flyway.setPackDir(dir);
						packDir.setText(dir.getAbsolutePath());
						ConsolePanel.addMsg("已选择打包目录: " + dir.getAbsolutePath());
					} catch (FileNotFoundException e) {
						packDir.setText("");
						ConsolePanel.addMsg("选择打包目录失败！");
					}
				}
			}
		});
	}
	
}
