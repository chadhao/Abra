package Abra.view;

import java.awt.Dimension;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ConsolePanel extends JComponent {
	
	private JScrollPane jsp;
	private static JTextArea text;
	
	public ConsolePanel() {
		text = new JTextArea();
		text.setEditable(false);
		text.setFocusable(false);
		text.setWrapStyleWord(true);
		text.setLineWrap(true);
		
		jsp = new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add(jsp);
		jsp.setBounds(0, 0, 250, 760);
	}
	
	public static void addMsg(String msg) {
		String timestamp = new SimpleDateFormat("HH:mm:ss").format(new Timestamp(System.currentTimeMillis()));
		text.setText(text.getText() + (text.getText().length()==0?"":"\n") + "[" + timestamp + "]" + msg);
		text.setCaretPosition(text.getDocument().getLength());
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(250, 760);
	}
}
