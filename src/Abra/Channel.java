package Abra;

public enum Channel {
	TJBJPX("天津保健品线", 0, ""),
	NZPOST("NZPOST", 0, ""),
	CQCRNF("重庆成人奶粉", 7, "奶"),
	CQNFX("重庆奶粉线", 7, "奶");
	
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
