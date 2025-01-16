package src;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class User {

	String username;
	String password;
	
	double debitBalance;
	double creditBalance;
	BookingSystem bookingSystem;
	HashMap<Integer,Ticket> bookings;
	
	public HashMap<Integer,Ticket> getBookings() {
		if (bookings == null) {
            bookings = new HashMap<Integer, Ticket>();
        }
		return bookings;
	}

	public void setBookings(Ticket ticket) {
		if (ticket != null && ticket.getBookingNumber() > 0) {
            bookings.put(ticket.getBookingNumber(), ticket);
        } else {
            System.out.println("Invalid ticket provided for booking.");
        }
	}
	
	public void removeBooking(int bookingNumber)
	{
		if (bookings.containsKey(bookingNumber)) {
            bookings.remove(bookingNumber);
        } else {
            System.out.println("Booking number not found.");
        }
	}

	User()
	{
		bookingSystem = new BookingSystem();
		bookings = new HashMap<Integer,Ticket>();
	}
	
	double generateRandom(double rangeMin, double rangeMax)
	{
		Random r = new Random();
		double balance = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
		
		return balance;
		
	}
}