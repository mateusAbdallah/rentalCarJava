import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		int option;
		int vehiclesCreated;
		int selection = 0;
		String fileName = null;
		
		Vehicle[] vehicles = null; 
					
		System.out.println("Please choose from the following options:");
		System.out.println("1 - Enter Vehicles");
		System.out.println("2 - Load Vehicles");			
		option = input.nextInt();
		
		boolean success = false;
		
		while(!success) {
			
			if(option == 1 || option == 2) {
				success = true;
			}
			else {
				System.out.println("Please enter 1 or 2");
				option = input.nextInt();
				
			}
				
							
		}
		
		try {
			if (option == 1) {
				System.out.println("How many vehicles do you want to create?");
				vehiclesCreated = input.nextInt();
				while(vehiclesCreated < 1) {
					System.out.println("Please enter a valid number greater than 0:");
					vehiclesCreated = input.nextInt();
					
				}
				
				vehicles = new Vehicle[vehiclesCreated];
				for(int i = 0; i < vehiclesCreated; i++) {
					Vehicle vehicle = inputVehicle();
					vehicles[i] = vehicle;
				}
				writeToFile("NewVehicles.txt", vehicles);
				System.out.println("Successfully written " + vehicles.length + " vehicles.");
				selection = availableVehicles(vehicles);

			}
			else if(option == 2) {
				boolean fileExistis = false;
				
				try {
					while(!fileExistis) {
						System.out.println("Please enter the file name: ");
						fileName = input.nextLine();
						fileName = input.nextLine();
						File file = new File("src\\" + fileName);
						
						if(file.exists() && file.isFile()) {
							fileExistis = true;
						} else {
							System.out.println("File does not exist. Please enter the correct file name");
						}
					}
					BufferedReader reader = new BufferedReader(new FileReader("src\\" + fileName));
					String line = reader.readLine();
					while(line != null) {
						line = reader.readLine();
					}
					reader.close();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
				
				vehicles = readFromFile(fileName);
				selection = availableVehicles(readFromFile(fileName));
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		
		
		
		
		
			
				
			
			
			/*
			
			if(selection == 1) {
				System.out.println("How many vehicles do you want to create?");
				vehiclesCreated = input.nextInt();	
				for(int i = 0; i < vehiclesCreated; i++) {
					System.out.println("Enter 1 for Car or 2 for Truck");
					options = input.nextInt();
					
					switch(options) {
						case 1: 
							createCar(vehiclesCreated, "Car");
							break;
						case 2:
							createTruck(vehiclesCreated, "Truck");
							break;							
					}
				}
			}
			if(selection == 2) {
				String fileName;
				System.out.println("Please enter file name:");
				fileName = input.nextLine();
				fileName = input.nextLine();
				if(fileName.equals("NewVehicles.txt")) {
					try {
						BufferedReader reader = new BufferedReader(new FileReader("NewVehicles.txt"));
						String line;
						while((line = reader.readLine()) != null)
							System.out.println(line);						
						reader.close();
					}
					catch(IOException e) {
						e.printStackTrace();
					}
					
				}
			}*/
	}
	
	
	
	public static void createCar() {
		
		
		System.out.println("How many vehicles do you want to create?");
		vehi
		
		try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter("NewVehicles.txt", true));
			
			
			Scanner input = new Scanner(System.in);
			String size[] = {"COMPACT", "MIDSIZE", "FULLSIZE", "PREMIUM"};
			String model;
			double rate;
			int sizeOfCar;
			
			System.out.println("Enter model:");
			model = input.nextLine();
			
			
			
			System.out.println("Enter rate:");
			rate = input.nextDouble();	
			
			
			System.out.println("What is the size of this car?");
			for(int j = 0; j < size.length; j++) {
				System.out.println((j+1) + " - " + size[j]);							
			}
			System.out.println("Please enter your choice");
			sizeOfCar = input.nextInt();
			
			String selectedCar = "";
			
			switch(sizeOfCar) {
			case 1:
				selectedCar = "COMPACT";
				break;
			case 2:
				selectedCar = "MIDSIZE";
				break;
			case 3:
				selectedCar = "FULLSIZE";
				break;
			case 4:
				selectedCar = "PREMIUM";
				break;
			}
			
			
			writer.write(String.valueOf(quantity));
			writer.newLine();
			writer.write(vehicle);
			writer.newLine();
			writer.write(model);
			writer.newLine();
			writer.write(String.valueOf(rate));
			writer.newLine();
			writer.write(selectedCar);
			writer.newLine();
			writer.flush();
			//writer.close();
			
			
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
		
		
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
