package Abra.view;

import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class ClientPanel extends JComponent {
	
	private JScrollPane jsp;
	private JList<String> list;
	
	public ClientPanel() {
		String[] strings = {"one", "two", "three", "four", "five11111111111111111111111"};
		list = new JList<>(strings);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jsp = new JScrollPane(list);
		add(jsp);
		jsp.setBounds(0, 0, 1185, 760);
	}
}
