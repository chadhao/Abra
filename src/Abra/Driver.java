package Abra;

public enum Driver {
	
	FENG("FENG"),
	DAMON("DAMON"),
	CARL("CARL"),
	TEDDY("TEDDY"),
	NEO("NEO"),
	LI("LI"),
	PING("PING");
	
	private String name;
	
	private Driver(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Driver getDriverByName(String name) {
		return null;
	}
}
