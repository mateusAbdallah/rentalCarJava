
public abstract class Vehicle {
	
	private String model;
	private double rate;
	
	public Vehicle(String model, double rate) {
		super();
		this.model = model;
		this.rate = rate;
	}	
	
	public double getRate() {
		return rate;
	}
	
	public void setRate(double rate) {
		this.rate = rate;
	}
	
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public String getDescription() {
		return model + " - Daily rate: $" + rate; 
	}
	
	
	
}
