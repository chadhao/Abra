package Abra;

public enum Channel {
	TJBJPX("��򱣽�Ʒ��", 0, ""),
	NZPOST("NZPOST", 0, ""),
	CQCRNF("��������̷�", 7, "��"),
	CQNFX("�����̷���", 7, "��");
	
	private String name;
	private double defaultWeight;
	private String packDir;
	
	private Channel(String name, double weight, String packDir) {
		this.name = name;
		this.defaultWeight = weight;
		this.packDir = packDir;
	}
	
	public String getName() {
		return this.name;
	}
	
	public double getDefaultWeight() {
		return this.defaultWeight;
	}
	
	public String getPackDir() {
		return this.packDir;
	}
}
