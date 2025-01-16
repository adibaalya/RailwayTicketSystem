package src;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;

class BookingSystem
{
	EnquiryEngine enquiryEngine;
	BookingEngine bookingEngine;
	PaymentEngine paymentEngine;
	CancellationEngine cancellationEngine;
	static Scanner in = new Scanner(System.in);
	public int booking(User user) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("*******************************************");
		System.out.println("1) Enquire");
		System.out.println("2) Book Ticket");
		System.out.println("3) Cancel Ticket");
		System.out.println("4) Return to previous menu"); 
		System.out.print("Enter your choice: ");
		int choice = Integer.parseInt(br.readLine());
		if(choice == 1)
		{
			HashMap<Integer,Train> tt = enquiry();
			if (!tt.isEmpty()) {
			    System.out.println("Select train number to book");
			    int n;
			    try {
			        n = Integer.parseInt(in.nextLine());
			    } catch (NumberFormatException e) {
			        System.out.println("Invalid input. Please enter a valid train number.");
			        return 0;
			    }

			    if (tt.containsKey(n)) {
			        Train t = tt.get(n);
			        if (t.getNumberOfSeats() > 0) {
			            System.out.println("Train has " + t.getNumberOfSeats() + " seats");
			            System.out.println("Booking");
			            if (bookingEngine == null) bookingEngine = new BookingEngine();
			            if (paymentEngine == null) paymentEngine = new PaymentEngine();


			            if (paymentEngine.deductBalance(t, user)) {
			                bookingEngine.bookTicket(n, user);
			            } else {
			                System.out.println("Booking failed. User has insufficient balance.");
			            }
			        } else {
			            System.out.println("Train doesn't have any vacant seats.");
			        }
			    } else {
			        System.out.println("Please enter a valid train number.");
			    }
			} else {
			    System.out.println("No trains available for the given information.");
			}

			return 0;	
		}
		else if(choice == 3)
		{
			if(cancellationEngine == null)
				cancellationEngine = new CancellationEngine();
			
			cancellationEngine.cancelTicket(user);
		}
		return 1;
	}

	private HashMap<Integer,Train> enquiry() throws Exception  {
		if(enquiryEngine == null)
			enquiryEngine = new EnquiryEngine();
		
		 HashMap<Integer,Train> t = enquiryEngine.enquire();
		 
		 return t;
		 
		
	}
	
	
}