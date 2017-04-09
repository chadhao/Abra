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
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	
	public void setTruckData(boolean isTruckData) {
		this.truckData = isTruckData;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public Driver getDriver() {
		return this.driver;
	}
	
	public boolean isTruckData() {
		return this.truckData;
	}
	
}