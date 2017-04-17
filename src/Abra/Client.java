package Abra;

import java.util.ArrayList;

public class Client {
	
	private String code;
	private Driver driver;
	private Channel defaultChannel;
	private ArrayList<Bill> bills;
	private boolean truckData;
	
	public Client(String code, Driver driver, boolean truckData, Channel channel) {
		this.code = code.toUpperCase();
		this.driver = driver;
		this.defaultChannel = channel;
		this.truckData = truckData;
		bills = new ArrayList<>();
	}
	
	public void setCode(String code) {
		this.code = code.toUpperCase();
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
	
	public Channel getDefaultChannel() {
		return this.defaultChannel;
	}
	
	public boolean isTruckData() {
		return this.truckData;
	}
	
	public void addBill(Bill bill) {
		this.bills.add(bill);
	}
	
	public Bill getBill(int index) {
		return this.bills.get(index);
	}
	
	public ArrayList<Bill> getAllBills() {
		return this.bills;
	}
	
}