package Abra.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import Abra.Abra;
import Abra.Channel;
import Abra.Client;
import Abra.Driver;
import Abra.OutputType;
import Abra.Utils;

public class ScanContainer extends JComponent {
	
	private JLabel billTypeLabel;
	private JComboBox<String> billTypeSelection;
	private DefaultComboBoxModel<String> billTypeModel;
	private JLabel clientLabel;
	private JComboBox<String> clientSelection;
	private JTextField clientInput;
	private JLabel driverLabel;
	private JComboBox<String> driverSelection;
	private DefaultComboBoxModel<String> driverModel;
	private JLabel channelLabel;
	private JComboBox<String> channelSelection;
	private DefaultComboBoxModel<String> channelModel;
	private JCheckBox isDaKa;
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
		driverLabel = new JLabel("取货司机: ");
		driverLabel.setHorizontalAlignment(JLabel.RIGHT);
		driverModel = getDriverList();
		driverSelection = new JComboBox<>(driverModel);
		channelLabel = new JLabel("渠道: ");
		channelLabel.setHorizontalAlignment(JLabel.RIGHT);
		channelModel = getChannelList();
		channelSelection = new JComboBox<>(channelModel);
		isDaKa = new JCheckBox("放大卡");
		progress = new JProgressBar(0, 100);
		progress.setStringPainted(true);
		scan = new JButton("提取面单");
		
		add(billTypeLabel);
		add(billTypeSelection);
		add(clientLabel);
		add(clientSelection);
		add(clientInput);
		add(driverLabel);
		add(driverSelection);
		add(channelLabel);
		add(channelSelection);
		add(isDaKa);
		add(progress);
		add(scan);
		
		billTypeLabel.setBounds(5, 5, 120, 30);
		billTypeSelection.setBounds(130, 5, 100, 30);
		channelLabel.setBounds(235, 5, 50, 30);
		channelSelection.setBounds(290, 5, 100, 30);
		clientLabel.setBounds(5, 40, 120, 30);
		clientSelection.setBounds(130, 40, 100, 30);
		clientInput.setBounds(235, 40, 100, 30);
		driverLabel.setBounds(340, 40, 80, 30);
		driverSelection.setBounds(425, 40, 100, 30);
		isDaKa.setBounds(560, 40, 70, 30);
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
		
		billTypeSelection.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				clientSelection.setEnabled(true);
				clientInput.setEnabled(true);
				channelSelection.setEnabled(true);
				channelSelection.setSelectedIndex(0);
				driverSelection.setEnabled(true);
				driverSelection.setSelectedIndex(0);
				isDaKa.setEnabled(true);
				if (billTypeSelection.getSelectedItem().equals(OutputType.NAI.getName())) {
					channelSelection.setSelectedItem(Channel.CQNFX.getName());
					channelSelection.setEnabled(false);
					driverSelection.setEnabled(false);
					isDaKa.setEnabled(false);
				} else if (billTypeSelection.getSelectedItem().equals(OutputType.AA.getName())
						|| billTypeSelection.getSelectedItem().equals(OutputType.CC.getName())
						|| billTypeSelection.getSelectedItem().equals(OutputType.EMS.getName())) {
					clientSelection.setSelectedIndex(0);
					clientSelection.setEnabled(false);
					clientInput.setEnabled(false);
					channelSelection.setEnabled(false);
					driverSelection.setEnabled(false);
					isDaKa.setEnabled(false);
				}
			}
		});
		
		scan.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Abra.flyway.getPackDir() == null || Abra.flyway.getWorkDir() == null || Abra.flyway.getScanDir() == null) {
					Utils.popMsg("请设置扫描/工作/打包目录！");
					return;
				}
				if (billTypeSelection.getSelectedItem().equals(OutputType.AA.getName())
					|| billTypeSelection.getSelectedItem().equals(OutputType.CC.getName())
					|| billTypeSelection.getSelectedItem().equals(OutputType.EMS.getName())
					|| clientInput.getText().isEmpty()) {
					Abra.flyway.setCurrentClient(Abra.flyway.getClient(0));
				}
				if (!clientInput.getText().isEmpty()) {
					if (!Abra.flyway.hasClient(clientInput.getText())) {
						if (billTypeSelection.getSelectedItem().equals(OutputType.NAI.getName())) {
							Abra.flyway.addClient(new Client(clientInput.getText(), null, false, Channel.CQNFX));
						} else {
							Abra.flyway.addClient(new Client(clientInput.getText(),
									Driver.valueOf((String)driverSelection.getSelectedItem()),
									isDaKa.isSelected(),
									Channel.findChannelByName((String)channelSelection.getSelectedItem())));
							
						}
						Abra.flyway.setCurrentClient(Abra.flyway.getClient(Abra.flyway.getAllClients().size()-1));
						reloadClients();
						clientSelection.setSelectedIndex(clientSelection.getItemCount()-1);
						ClientPanel.reloadList();
					} else {
						Abra.flyway.setCurrentClient(Abra.flyway.getClient(clientInput.getText()));
					}
				}
				ClientPanel.setSelected(Abra.flyway.getCurrentClient().getCode());
			}
		});
	}
	
	private void reloadClients() {
		DefaultComboBoxModel<String> listData = Abra.flyway.getClientsComboBoxModel();
		clientSelection.removeAllItems();
		for (int i = 0; i < listData.getSize(); i++) {
			clientSelection.addItem(listData.getElementAt(i));
		}
	}
	
	private DefaultComboBoxModel<String> getBillTypeList() {
		DefaultComboBoxModel<String> list = new DefaultComboBoxModel<>();
		for (OutputType type : OutputType.values()) {
			list.addElement(type.getName());
		}
		return list;
	}
	
	private DefaultComboBoxModel<String> getDriverList() {
		DefaultComboBoxModel<String> list = new DefaultComboBoxModel<>();
		for (Driver type : Driver.values()) {
			list.addElement(type.getName());
		}
		return list;
	}

	private DefaultComboBoxModel<String> getChannelList() {
		DefaultComboBoxModel<String> list = new DefaultComboBoxModel<>();
		for (Channel type : Channel.values()) {
			list.addElement(type.getName());
		}
		return list;
	}
}
