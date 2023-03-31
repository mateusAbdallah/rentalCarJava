import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {
			
		writeVehicles();
		
	}
	
	public static void writeVehicles() {	
		
			Scanner input = new Scanner(System.in);
			
			int options, vehiclesCreated;
			int selection = 0;
						
			System.out.println("Please choose from the following options:");
			System.out.println("1 - Enter Vehicles \n2 - Load Vehicles");			
			selection = input.nextInt();
			while(selection < 1 || selection > 2) {
				System.out.println("Please enter 1 or 2");
				selection = input.nextInt();				
			}
			
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
			}
	}
	
	public static void createCar(int quantity, String vehicle) {
		
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
