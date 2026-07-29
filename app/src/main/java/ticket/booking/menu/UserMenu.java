package ticket.booking.menu;

import ticket.booking.entities.Ticket;
import ticket.booking.entities.Train;
import ticket.booking.service.BookingService;
import ticket.booking.service.TrainService;

import java.util.List;
import java.util.Scanner;

public class UserMenu {
    private final Scanner sc=new Scanner(System.in);
    private final BookingService bookingService;
    private final TrainService trainService;
    public UserMenu(BookingService bookingService,
                    TrainService trainService) {

        this.bookingService = bookingService;
        this.trainService = trainService;
    }

    public void start() {

        while (true) {

            System.out.println("\n USER MENU ");

            System.out.println("1. Search Train");
            System.out.println("2. Book Ticket");
            System.out.println("3. Fetch My Bookings");
            System.out.println("4. Cancel Booking");
            System.out.println("5. Logout");

            int choice = sc.nextInt();

            switch (choice) {

                case 1 -> searchTrain();

                case 2 -> bookTicket();

                case 3 -> fetchBookings();

                case 4 -> cancelBooking();

                case 5 -> {
                    return;
                }

                default -> System.out.println("Invalid Choice");
            }

        }

    }

    private void searchTrain() {

        System.out.print("Source : ");
        String source = sc.next();

        System.out.print("Destination : ");
        String destination = sc.next();

        List<Train> trains =
                trainService.searchTrain(source, destination);

        if (trains.isEmpty()) {

            System.out.println("No Train Found");
            return;
        }

        for (Train train : trains) {

            System.out.println(train.getTrainInfo());

        }

    }

    private void bookTicket() {

        System.out.print("Source : ");
        String source = sc.next();

        System.out.print("Destination : ");
        String destination = sc.next();

        List<Train> trains =
                trainService.searchTrain(source, destination);

        if (trains.isEmpty()) {

            System.out.println("No Train Found");
            return;
        }

        for (int i = 0; i < trains.size(); i++) {

            System.out.println((i + 1) + ". " +
                    trains.get(i).getTrainInfo());

        }

        System.out.print("Choose Train : ");

        int index = sc.nextInt() - 1;

        Train selectedTrain = trains.get(index);

        System.out.print("Travel Date : ");

        String date = sc.next();

        System.out.print("Row : ");

        int row = sc.nextInt();

        System.out.print("Column : ");

        int col = sc.nextInt();

        boolean success = bookingService.bookTicket(
                selectedTrain,
                source,
                destination,
                date,
                row,
                col
        );

        if (success)

            System.out.println("Ticket Booked Successfully");

        else

            System.out.println("Booking Failed");

    }

    private void fetchBookings() {

        List<Ticket> tickets =
                bookingService.fetchBookings();

        if (tickets == null || tickets.isEmpty()) {

            System.out.println("No Bookings");

            return;
        }

        for (Ticket ticket : tickets) {

            System.out.println(ticket.getTicketInfo());

        }

    }

    private void cancelBooking() {

        System.out.print("Enter Ticket Id : ");

        String ticketId = sc.next();

        boolean success =
                bookingService.cancelBooking(ticketId);

        if (success)

            System.out.println("Cancelled Successfully");

        else

            System.out.println("Ticket Not Found");

    }

}