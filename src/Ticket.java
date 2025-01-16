package src;
public class Ticket  extends BookingEngine{

	
	private	int bookingNumber;
	private static int bookingNumberGenerator = 0;
	private double fare;
	
	public double getFare() {
		return fare;
	}

	protected void setFare(Double double1) {
		this.fare = double1;
	}
	
	public int getBookingNumber() {
		return bookingNumber;
	}

	Ticket()
	{
		bookingNumberGenerator++;
		bookingNumber = bookingNumberGenerator;
	}
	
	public String getStartCity() {
		return super.getStartCity(); // Access startCity from Train via BookingEngine
	}
	
	public String getEndCity() {
		return super.getEndCity(); // Access endCity from Train via BookingEngine
	}
	
}
