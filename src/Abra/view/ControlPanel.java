package Abra.view;

import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ControlPanel extends JComponent {
	
	private JTabbedPane tabs;
	private ScanContainer scan;
	private InputContainer input;
	private OutputContainer output;
	private SettingContainer setting;
	
	public ControlPanel(DefaultComboBoxModel<String> list) {
		scan = new ScanContainer(list);
		input = new InputContainer();
		output = new OutputContainer();
		setting = new SettingContainer();
		
		tabs = new JTabbedPane();
		tabs.add("扫单", scan);
		tabs.add("录单", input);
		tabs.add("导出", output);
		tabs.add("设置", setting);
		
		add(tabs);
		tabs.setBounds(0, 0, 825, 175);
		
		tabs.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				PicPanel.setDefault();
			}
		});
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(825, 175);
	}
}
