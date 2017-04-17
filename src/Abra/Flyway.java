package Abra;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;;

public class Flyway {

	private File scanDir;
	private File workDir;
	private File packDir;
	private ArrayList<Client> clients;
	
	public Flyway() {
		this.scanDir = null;
		this.workDir = null;
		this.packDir = null;
		this.clients = new ArrayList<>();
		this.clients.add(new Client("未分类", null, false, null));
	}
	
	public Flyway(File scanDir, File workDir, File packDir) {
		this.scanDir = scanDir;
		this.workDir = workDir;
		this.packDir = packDir;
		this.clients = new ArrayList<>();
		this.clients.add(new Client("未分类", null, false, null));
	}
	
	public void setScanDir(File dir) throws FileNotFoundException {
		checkDir(dir);
		this.scanDir = dir;
	}
	
	public void setWorkDir(File dir) throws FileNotFoundException {
		checkDir(dir);
		this.workDir = dir;
	}
	
	public void setPackDir(File dir) throws FileNotFoundException {
		checkDir(dir);
		this.packDir = dir;
	}
	
	private void checkDir(File dir) throws FileNotFoundException {
		if (!dir.exists()) {
			throw new FileNotFoundException("The passed in File does not exist!");
		}
		if (!dir.isDirectory()) {
			throw new FileNotFoundException("The paseds in File is not a directory!");
		}
	}
	
	public void addClient(Client client) {
		this.clients.add(client);
	}
	
	public Client getClient(int index) {
		return this.clients.get(index);
	}
	
	public ArrayList<Client> getAllClients() {
		return this.clients;
	}
	
	public File getScanDir() {
		return this.scanDir;
	}
	
	public File getWorkDir() {
		return this.workDir;
	}
	
	public File getPackDir() {
		return this.packDir;
	}
	
	public DefaultListModel<String> getClientsListModel() {
		DefaultListModel<String> list = new DefaultListModel<>();
		Iterator<Client> it = clients.iterator();
		
		while(it.hasNext()) {
			Client thisClient = it.next();
			int size = thisClient.getAllBills().size();
			list.addElement(thisClient.getCode() + "(" + size + ")");
		}
		
		return list;
	}
	
	public DefaultComboBoxModel<String> getClientsComboBoxModel() {
		DefaultComboBoxModel<String> list = new DefaultComboBoxModel<>();
		Iterator<Client> it = clients.iterator();
		
		while(it.hasNext()) {
			Client thisClient = it.next();
			int size = thisClient.getAllBills().size();
			list.addElement(thisClient.getCode());
		}
		
		return list;
	}
	
	public boolean hasClient(String code) {
		Iterator<Client> it = clients.iterator();
		
		while(it.hasNext()) {
			Client client = it.next();
			if (client.getCode().equals(code.toUpperCase())) {
				return true;
			}
		}
		
		return false;
	}
	
}
