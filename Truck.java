
public class Truck extends Vehicle {
	
	private double cargoCapacity;
	
	public Truck(String model, double rate, double cargoCapacity) {
		super(model, rate);
		this.cargoCapacity = cargoCapacity;
	}

	@Override
	public String getDescription() {
		return "TRUCK with cargo capacity: " + cargoCapacity + "kg. " + super.getDescription();
	}

		
}
