package ticket.booking.service;

import ticket.booking.entities.Ticket;
import ticket.booking.entities.Train;
import ticket.booking.entities.User;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class BookingService {

    private final UserService userService;
    private final TrainService trainService;

    public BookingService(UserService userService, TrainService trainService) {
        this.userService = userService;
        this.trainService = trainService;
    }

    public boolean bookTicket(Train train,
                              String source,
                              String destination,
                              String dateOfTravel,
                              int row,
                              int col) {

        User currentUser = userService.getCurrentUser();

        if (currentUser == null) {
            System.out.println("Please login first.");
            return false;
        }

        try {

            boolean booked = trainService.updateSeats(
                    train.getTrainId(),
                    row,
                    col
            );

            if (!booked) {
                System.out.println("Seat already booked.");
                return false;
            }

            Ticket ticket = new Ticket();

            ticket.setTicketId(UUID.randomUUID().toString());
            ticket.setUserId(currentUser.getUserId());
            ticket.setSource(source);
            ticket.setDestination(destination);
            ticket.setDateOfTravel(dateOfTravel);
            ticket.setTicket(train);

            currentUser.getTicketBooked().add(ticket);

            userService.saveUsers();

            return true;

        } catch (IOException e) {
            return false;
        }
    }

    public List<Ticket> fetchBookings() {

        User currentUser = userService.getCurrentUser();

        if (currentUser == null) {
            return null;
        }

        return currentUser.getTicketBooked();
    }

    public boolean cancelBooking(String ticketId) {

        User currentUser = userService.getCurrentUser();

        if (currentUser == null)
            return false;

        List<Ticket> tickets = currentUser.getTicketBooked();

        Ticket removeTicket = null;

        for (Ticket ticket : tickets) {

            if (ticket.getTicketId().equalsIgnoreCase(ticketId)) {

                removeTicket = ticket;
                break;

            }
        }

        if (removeTicket != null) {

            tickets.remove(removeTicket);

            try {

                userService.saveUsers();

                return true;

            } catch (IOException e) {

                return false;

            }

        }

        return false;
    }

}