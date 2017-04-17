package Abra.view;

import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JTabbedPane;

public class ControlPanel extends JComponent {
	
	private JTabbedPane tabs;
	private ScanContainer scan;
	private InputContainer input;
	private OutputContainer output;
	private SettingContainer setting;
	
	public ControlPanel() {
		scan = new ScanContainer();
		input = new InputContainer();
		output = new OutputContainer();
		setting = new SettingContainer();
		
		tabs = new JTabbedPane();
		tabs.add("ɨ��", scan);
		tabs.add("¼��", input);
		tabs.add("����", output);
		tabs.add("����", setting);
		
		add(tabs);
		tabs.setBounds(0, 0, 825, 175);
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(825, 175);
	}
}
