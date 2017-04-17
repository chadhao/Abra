package Abra;

import java.io.File;

public class Bill {

	private String billNumber;
	private File file;
	private double weight;
	private Channel channel;
	private String note;
	private boolean dianFaHuo;
	private OutputType type;
	
	public Bill(File file) {
		this.file = file;
		this.billNumber = "";
		this.weight = 0;
		this.channel = Channel.TJBJPX;
		this.note = "";
		this.dianFaHuo = false;
		this.type = OutputType.BJP;
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
	
	public void setDianFaHuo(boolean isDianFaHuo) {
		this.dianFaHuo = isDianFaHuo;
	}
	
	public void setBillType(OutputType type) {
		this.type = type;
	}
	
	public boolean setFileName(String name) {
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
	
	public boolean isDianFaHuo() {
		return this.dianFaHuo;
	}
	
	public OutputType getBillType() {
		return this.type;
	}
}
