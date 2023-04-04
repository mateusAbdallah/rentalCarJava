import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {

	//private static final OpenOption CREATE = null;

	public static void main(String[] args) {
		
		int selection = 0;
		Vehicle vehicles[] = null;
		
		Scanner input = new Scanner(System.in);
		System.out.println("Please choose from the following options:");
		System.out.println("1 - Enter Vehicles:");
		System.out.println("2 - Load Vehicles:");
		selection = input.nextInt();
		
		if(selection == 1) {
			vehicles = createVehicles();
			storeVehicles(vehicles, "NewVehicles.txt");
		}
		else {
			System.out.println("Please enter file name");
			input.nextLine();
			String fileName = input.nextLine();
			vehicles = loadVehicles(fileName);
		}
		
		System.out.println("Available vehicles:");
		for(int i = 0; i < vehicles.length; i++) {
			System.out.println((i+1 + " " + vehicles[i].getDescription()));
		}
		
		boolean success = false;
		
		while(!success) {
			try {
				System.out.println("For pick enter vehicle's number");
				selection = input.nextInt();
				if(selection >= 1 && selection <= vehicles.length) {
					success = true;
				} else {
					System.out.println("Enter a valid number: 1 or "+ vehicles.length);
				}
			} catch( Exception e) {
				System.out.println("Enter a valid number: 1 or \"+ vehicles.length");
				System.out.println(e);
			}
		}
		
		System.out.println("Enter pick up date:");
		LocalDate pickUpdate = inputDate();
		
		System.out.println("Enter a drop off date");
		LocalDate dropOffDate = inputDate();
		
		Rental rental = new Rental(vehicles[selection - 1], pickUpdate, dropOffDate);
		
		System.out.println(rental.getDescription());

	}
	
	public static Vehicle[] createVehicles() {
		
		Vehicle[] vehicles = null;
		
		Scanner input = new Scanner(System.in);
		System.out.println("How many vehicles do you want to create?");
		int numberVehicles = input.nextInt();
		
		vehicles = new Vehicle[numberVehicles];
		
		for(int i = 0; i < numberVehicles;i++) {
			System.out.println("Enter 1 for car or 2 for truck");
			int type = input.nextInt();
			input.nextLine();
			System.out.println("Enter model");
			String model = input.nextLine();
			System.out.println("Enter rate");
			double rate = input.nextDouble();
			input.nextLine();
			if(type==1) {
				System.out.println("What's the size of this car?");
				System.out.println("1 - COMPACT");
				System.out.println("2 - MIDSIZE");
				System.out.println("3 - FULLSIZE");
				System.out.println("4 - PREMIUM");
				System.out.println("Please enter your choice");
				int choiceSize = input.nextInt();
				Car.Size carSize = Car.Size.values()[choiceSize - 1];
				String size = carSize.toString();
				vehicles[i] = new Car(model, rate, Car.Size.valueOf(size));				
			} else {
				System.out.println("Please enter the cargo capacity: ");
				double cargoCapacity = input.nextDouble();
				vehicles[i] = new Truck(model, rate, cargoCapacity);
			}
				
		}
		return vehicles;
	}
	
	private static void storeVehicles(Vehicle[] vehicles, String fileName) {
		
		Path file = Paths.get("src\\"+fileName);
		int numberVehicles = vehicles.length;
		
		try {
			OpenOption CREATE = null;
			OutputStream output = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
			writer.write(Integer.toString(numberVehicles));
			writer.newLine();
			for(int i = 0; i<numberVehicles; i++) {
				if(vehicles[i] instanceof Car) {
					Car car = (Car) vehicles[i];
					writer.write("Car");
					writer.newLine();
					writer.write(car.getModel());
					writer.newLine();
					writer.write(Double.toString(car.getRate()));
					writer.newLine();
					writer.write(car.getSize().toString());
					writer.newLine();
				} else {
					Truck truck = (Truck) vehicles[i];
					writer.write("Truck");
					writer.newLine();
					writer.write(truck.getModel());
					writer.newLine();
					writer.write(Double.toString(truck.getRate()));
					writer.newLine();
					writer.write(Double.toString(truck.getCargoCapacity()));
					writer.newLine();
				}
			}
			writer.close();
			System.out.println("Successfully written " + numberVehicles + "vehicles.");
		}
		catch(Exception e) {
			System.out.println("Message " +e);
		}
	}
	
	private static Vehicle[] loadVehicles(String fileName) {
		Vehicle vehicles[] = null;
		
		Path file = Paths.get("src\\" + fileName);
		
		try {
			InputStream input = new BufferedInputStream(Files.newInputStream(file));
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			String nextLine = reader.readLine();
			int numberVehicles = Integer.parseInt(nextLine);
			vehicles = new Vehicle[numberVehicles];
			for(int i = 0; i< numberVehicles; i++) {
				String type = reader.readLine();
				String model = reader.readLine();
				String rateString = reader.readLine();
				double rate = Double.parseDouble(rateString);
				String special = reader.readLine();
				if("Car".equals(type)) {
					Car car = new Car(model, rate, Car.Size.valueOf(special));
					vehicles[i] = car;
				} else {
					double cargoCapacity = Double.parseDouble(special);
					Truck truck = new Truck(model, rate, cargoCapacity);
					vehicles[i] = truck;
				}
			}
			reader.close();
		}
		
		catch(Exception e) {
			System.out.println("Messagee"+e);
		}
					
		
		return vehicles;
	}
	
	private static LocalDate inputDate() {
		
		int year, month, day;
		LocalDate date = null;
		boolean success = false;
		
		while(!success) {
			try {
				Scanner input = new Scanner(System.in);
				System.out.println("Enter year");
				year = input.nextInt();
				System.out.println("Enter month");
				month = input.nextInt();
				System.out.println("Enter day");
				day = input.nextInt();
				date = LocalDate.of(year, month, day);
				success = true;
				
			}
			catch (Exception e) {
				System.out.println("Enter a valid date");
				System.out.println(e);
			}
		}
		return date;
	}
					
}


