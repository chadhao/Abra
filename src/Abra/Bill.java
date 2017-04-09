package Abra;

import java.io.File;

public class Bill {

	private String billNumber;
	private File file;
	private double weight;
	private Channel channel;
	private String note;
	
	public Bill(File file) {
		this.file = file;
		this.billNumber = "";
		this.weight = 0;
		this.channel = Channel.TJBJPX;
		this.note = "";
	}
	
	public void setBillNumber(String number) { 
		this.billNumber = number;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	
	public void setNote(String note) {
		this.note = note;
	}
	
	public Boolean setFileName(String name) {
		String newNameWithFullPath = file.getAbsolutePath() + "\\" + name + ".jpg";
		File newFile = new File(newNameWithFullPath);
		if (newFile.exists()) {
			if (!newFile.delete()) {
				return false;
			}
		}	
		return file.renameTo(newFile);
	}
	
	public String getBillNumber() {
		return this.billNumber;
	}
	
	public double getWeight() {
		return this.weight;
	}
	
	public Channel getChannel() {
		return this.channel;
	}
	
	public String getNote() {
		return this.note;
	}
}
