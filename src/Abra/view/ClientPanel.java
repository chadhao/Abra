package Abra.view;

import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class ClientPanel extends JComponent {
	
	private JScrollPane jsp;
	private JList<String> list;
	private JLabel label;
	
	public ClientPanel(DefaultListModel<String> clientsList) {
		label = new JLabel("客户列表");
		list = new JList<>(clientsList);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jsp = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add(label);
		add(jsp);
		label.setBounds(0, 0, 100, 18);
		jsp.setBounds(0, 20, 100, 740);
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(100, 760);
	}
}
