import static java.nio.file.StandardOpenOption.CREATE;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Main extends JFrame implements ActionListener {
	
	private JLabel vehicleLabel;
	private JLabel pickupDateDayLabel;
	private JLabel pickupDateMonthLabel;
	private JLabel pickupDateYearLabel;
	private JLabel rentalDurationLabel;
	private JLabel emptyLabel;
	private JComboBox<Vehicle> vehicleComboBox;
	private JLabel pickUpDateLabel;
	private JComboBox<Integer> dayComboBox;
	private JComboBox<String> monthComboBox;
	private JComboBox<Integer> yearComboBox;
	private JComboBox<Integer> durationComboBox;
	private JButton createButton;
	
	public Main() {
		super("Car Rentals");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(0, 2));
		vehicleLabel = new JLabel("Select a vehicle");
		pickupDateDayLabel = new JLabel("Select a pick up date - day");
		pickupDateMonthLabel = new JLabel("Select a pick up date - month");
		pickupDateYearLabel = new JLabel("Select a pick up date - year");
		rentalDurationLabel = new JLabel("Select a rental duration (days)");
		emptyLabel = new JLabel("");
		Vehicle[] vehicles = loadVehicles("NewVehicles.txt");
		vehicleComboBox = new JComboBox<Vehicle>();
	    for(int i = 0; i < vehicles.length; i++) {
	    	vehicleComboBox.addItem(vehicles[i]);
	    }
	    dayComboBox = new JComboBox<Integer>();
	    for(int i = 1; i <= 31; i++) {
	    	dayComboBox.addItem(i);
	    }
	    monthComboBox = new JComboBox<String>();
	    String[] months = {"January", "February", "March", "April", "May", "June", "July",
	    		"August", "September", "October", "November", "Dezember" };
	    for(String month: months) {
	    	monthComboBox.addItem(month);
	    }
	    yearComboBox = new JComboBox<Integer>();
	    for(int i = 2023; i <= 2033; i++) {
	    	yearComboBox.addItem(i);
	    }
	    durationComboBox = new JComboBox<Integer>();
	    for(int i = 1; i <= 100; i++) {
	    	durationComboBox.addItem(i);
	    }
	    
	    createButton = new JButton("Create rental");
	    createButton.addActionListener(this);
	    
	    // add all the components
	    add(vehicleLabel);
	    add(vehicleComboBox);
	    add(pickupDateDayLabel);
	    add(dayComboBox);
	    add(pickupDateMonthLabel);
	    add(monthComboBox);
	    add(pickupDateYearLabel);
	    add(yearComboBox);
	    add(rentalDurationLabel);
	    add(durationComboBox);
	    add(emptyLabel);
	    add(createButton);

	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Vehicle vehicle = (Vehicle) vehicleComboBox.getSelectedItem();
		int day = (Integer) dayComboBox.getSelectedItem();
		int month = monthComboBox.getSelectedIndex() + 1;
		int year = (Integer) yearComboBox.getSelectedItem();
		int duration = (Integer) durationComboBox.getSelectedItem();
		LocalDate pickupDate;
		try {
			pickupDate = LocalDate.of(year, month, day);
			
		}catch(DateTimeException exception) {
			JOptionPane.showConfirmDialog(this, "Please enter a valid!");
			return;
			
		}
		LocalDate dropOffDate = pickupDate.plusDays(duration);
		
		Rental rental = new Rental (vehicle, pickupDate, dropOffDate);
		String output = "Successfully created the following rental:\n" + rental;
		JOptionPane.showConfirmDialog(this, output);
		
	}
	
	public static void main(String args[]) {
	      final int FRAME_WIDTH = 800;
	      final int FRAME_HEIGHT = 300;
	      Main rentalProgram = new Main();
	      rentalProgram.setSize(FRAME_WIDTH, FRAME_HEIGHT);
	      rentalProgram.setVisible(true);		
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
			System.out.println("Message: "+e);
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


