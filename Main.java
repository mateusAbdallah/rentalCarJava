import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {

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
		for(int i = 0, i < vehicles.length; i++) {
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
	}
	
	public static void createTruck(int quantity, String vehicle) {
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("NewVehicles.txt", true));
			
			Scanner input = new Scanner(System.in);
			String model;
			double rate;
			int capacity;
			
			System.out.println("Enter model:");
			model = input.nextLine();
			System.out.println("Enter rate:");
			rate = input.nextDouble();
			System.out.println("Please enter the cargo capacity:");
			capacity = input.nextInt();
			
			writer.write(String.valueOf(quantity));
			writer.newLine();
			writer.write(vehicle);
			writer.newLine();
			writer.write(model);
			writer.newLine();
			writer.write(String.valueOf(rate));
			writer.newLine();
			writer.write(String.valueOf(capacity));
			writer.flush();
			writer.close();
			
			

			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
				
	}

}
