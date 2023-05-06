import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Rental {
	private Vehicle vehicle;
	private LocalDate pickUpdate;
	private LocalDate dropOffDate;
	
	public Rental(Vehicle vehicle, LocalDate pickUpdate, LocalDate dropOffDate) {
		this.vehicle = vehicle;
		this.pickUpdate = pickUpdate;
		this.dropOffDate = dropOffDate;
	}
	
	public double calculateTotal() {
		double duration = ChronoUnit.DAYS.between(pickUpdate, dropOffDate);
		return duration * vehicle.getRate();
	}
	
	public String getDescription() {
		String result = "Rented vehicle:\n" + vehicle.getDescription() + 
				"\nPick up date: " + pickUpdate + "\nDrop off date " + dropOffDate +
				"\nRental rate: $" + calculateTotal();
		return result;
	}
	
	public String toString() {
		return getDescription();
	}
}
