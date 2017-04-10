package Abra;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Flyway {

	private File scanDir;
	private File workDir;
	private File packDir;
	private ArrayList<Client> clients;
	
	public Flyway(File scanDir, File workDir, File packDir) {
		this.scanDir = scanDir;
		this.workDir = workDir;
		this.packDir = packDir;
		this.clients = new ArrayList<>();
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
	
}
