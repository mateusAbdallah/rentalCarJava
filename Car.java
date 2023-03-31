

public class Car extends Vehicle {
	
	private Size size;
	
	enum Size { COMPACT, MIDSIZE, FULLSIZE, PREMIUM };
	
	
	public Car(String model, double rate, Size size) {
		super(model, rate);
		this.size = size;
	}
	
	public Size getSize() {
		return size;
	}
	
	public void setSize(Size size) {
		this.size = size;
	}
	
	@Override
	public String getDescription() {
		return size + " " + super.getDescription();
	}
	

}
