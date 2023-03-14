import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Car extends Vehicle {
	
	private Size size;
	
	enum Size { COMPACT, MIDSIZE, FULLSIZE, PREMIUM };
	
	
	public Car(String model, double rate, Size size) {
		super(model, rate);
		this.size = size;
	}
	
	@Override
	public String getDescription() {
		return size + " " + super.getDescription();
	}
	

}
