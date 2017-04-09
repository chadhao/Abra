package Abra;

public enum Channel {
	TJBJPX("天津保健品线", 0),
	NZPOST("NZPOST", 0),
	CQCRNF("重庆成人奶粉", 7),
	CQNFX("重庆奶粉线", 7);
	
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
