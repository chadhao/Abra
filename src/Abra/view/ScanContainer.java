package Abra.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import Abra.Abra;
import Abra.Client;
import Abra.OutputType;

public class ScanContainer extends JComponent {
	
	private JLabel billTypeLabel;
	private JComboBox<String> billTypeSelection;
	private DefaultComboBoxModel<String> billTypeModel;
	private JLabel clientLabel;
	private JComboBox<String> clientSelection;
	private JTextField clientInput;
	private JProgressBar progress;
	private JButton scan;
	
	public ScanContainer(DefaultComboBoxModel<String> list) {
		billTypeLabel = new JLabel("选择面单类型: ");
		billTypeLabel.setHorizontalAlignment(JLabel.RIGHT);
		billTypeModel = getBillTypeList();
		billTypeSelection = new JComboBox<>(billTypeModel);
		billTypeSelection.setAutoscrolls(true);
		clientLabel = new JLabel("选择/输入客户代码: ");
		clientLabel.setHorizontalAlignment(JLabel.RIGHT);
		clientSelection = new JComboBox<>(list);
		clientSelection.setAutoscrolls(true);
		clientInput = new JTextField();
		progress = new JProgressBar(0, 100);
		progress.setStringPainted(true);
		scan = new JButton("提取面单");
		
		add(billTypeLabel);
		add(billTypeSelection);
		add(clientLabel);
		add(clientSelection);
		add(clientInput);
		add(progress);
		add(scan);
		
		billTypeLabel.setBounds(5, 5, 120, 30);
		billTypeSelection.setBounds(130, 5, 100, 30);
		clientLabel.setBounds(5, 40, 120, 30);
		clientSelection.setBounds(130, 40, 100, 30);
		clientInput.setBounds(235, 40, 100, 30);
		progress.setBounds(5, 75, 810, 30);
		scan.setBounds(360, 110, 100, 30);
		
		clientSelection.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (clientSelection.getSelectedIndex() != 0) {
					clientInput.setText(clientSelection.getItemAt(clientSelection.getSelectedIndex()));
					clientInput.setEnabled(false);
				} else {
					clientInput.setText("");
					clientInput.setEnabled(true);
				}
			}
		});
		
		scan.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!clientInput.getText().isEmpty()) {
					if (!Abra.flyway.hasClient(clientInput.getText())) {
						Abra.flyway.addClient(new Client(clientInput.getText(), 
								null, false, null));
						clientSelection.addItem(clientInput.getText().toUpperCase());
						clientSelection.setSelectedItem(clientInput.getText().toUpperCase());
						ClientPanel.reloadList();
					}
				}
			}
		});
	}
	
	private DefaultComboBoxModel<String> getBillTypeList() {
		DefaultComboBoxModel<String> list = new DefaultComboBoxModel<>();
		for (OutputType type : OutputType.values()) {
			list.addElement(type.getName());
		}
		return list;
	}

}
