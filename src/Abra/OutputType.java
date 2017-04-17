package Abra;

public enum OutputType {
	BJP("±£½¡Æ·", ""),
	EMS("EMS", "EMS"),
	CC("CC", "CC"),
	AA("AA", "AA"),
	NAI("ÄÌ·Û", "ÄÌ");
	
	private String name;
	private String packDir;
	
	private OutputType(String name, String packDir) {
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
