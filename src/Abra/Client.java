package Abra;

import java.util.ArrayList;

public class Client {
	
	private String code;
	private Driver driver;
	private ArrayList<Bill> bills;
	private boolean truckData;
	
	public Client(String code, Driver driver, boolean truckData) {
		this.code = code;
		this.driver = driver;
		this.truckData = truckData;
		bills = new ArrayList<>();
	}
	
}
