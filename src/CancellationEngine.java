package src;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CancellationEngine {
	
	//attribute
	static Scanner in = new Scanner(System.in);
	PaymentEngine paymentEngine;
	
	// Main method to cancel ticket
    void cancelTicket(User user) {
        displayExistingBookings(user);
        int bookingNumber = getBookingNumberToCancel(user);
        
        if (isValidBooking(user, bookingNumber)) {
            processCancellation(user, bookingNumber);
        } else {
            System.out.println("Please Enter a valid booking Number");
        }
    }

    // Method to display existing bookings
    private void displayExistingBookings(User user) {
        System.out.println("******************EXISTING BOOKINGS*****************");
        System.out.println("The User has the following bookings\n");
        HashMap<Integer, Ticket> bookings = user.getBookings();
        
        for (Ticket ticket : bookings.values()) {
            System.out.println("Booking Number: " + ticket.getBookingNumber());
            System.out.println(" From : " + ticket.getStartCity());
            System.out.println(" To : " + ticket.getEndCity());
        }
        System.out.println("****************************************");
    }

    // Method to prompt user for booking number and validate 
    public int getBookingNumberToCancel() {
		System.out.println("Please Enter the booking Number you wish to cancel");
		return in.nextInt();
	}
	

    // Method to check if the booking number exists in user's bookings
    private boolean isValidBooking(User user, int bookingNumber) {
        HashMap<Integer, Ticket> bookings = user.getBookings();
        return bookings.containsKey(bookingNumber);
    }

    // Method to process the cancellation and refund
    private void processCancellation(User user, int bookingNumber) {
        Ticket ticket = user.getBookings().get(bookingNumber);
        System.out.println("Your booking has been cancelled");
        System.out.println("Amount of " + ticket.getFare() + " has been credited back to your account");

        if (paymentEngine == null) {
            paymentEngine = new PaymentEngine();
        }
        paymentEngine.processRefund(user, ticket);
        user.removeBooking(bookingNumber);
        System.out.println("*****************************************");
    }

}
