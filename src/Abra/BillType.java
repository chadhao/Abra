package Abra;

public enum BillType {
	BJP("����Ʒ", ""),
	CC("CC", "CC"),
	AA("AA", "AA"),
	NAI("�̷�", "��");
	
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
