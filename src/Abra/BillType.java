package Abra;

public enum BillType {
	BJP("±£½¡Æ·", ""),
	CC("CC", "CC"),
	AA("AA", "AA"),
	NAI("ÄÌ·Û", "ÄÌ");
	
	private String name;
	private String packDir;
	
	private BillType(String name, String ackDir) {
		this.name = name;
		this.packDir = packDir;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getPackDir() {
		return this.packDir;
	}
}
