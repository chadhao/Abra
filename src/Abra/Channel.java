package Abra;

public enum Channel {
	TJBJPX("��򱣽�Ʒ��", 0),
	NZPOST("NZPOST", 0),
	CQCRNF("��������̷�", 7),
	CQNFX("�����̷���", 7);
	
	private String name;
	private double defaultWeight;
	
	private Channel(String name, double weight) {
		this.name = name;
		this.defaultWeight = weight;
	}
	
	public String getName() {
		return this.name;
	}
	
	public double getDefaultWeight() {
		return this.defaultWeight;
	}
}
