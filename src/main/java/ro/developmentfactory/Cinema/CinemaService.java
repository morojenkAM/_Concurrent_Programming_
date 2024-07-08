package ro.developmentfactory.Cinema;

public class CinemaService {
    private final OptimisticLocking optimisticLocking;
    private final PessimisticLocking pessimisticLocking;

    // Constructor to accept both locking strategies
    public CinemaService(OptimisticLocking optimisticLocking, PessimisticLocking pessimisticLocking) {
        this.optimisticLocking = optimisticLocking;
        this.pessimisticLocking = pessimisticLocking;
    }

    // Method to book seats using optimistic locking
    public boolean bookSeatsOptimistic(int numSeats) throws NotEnoughSeatsException, SeatsAlreadyReservedException {
        optimisticLocking.bookSeats(numSeats);
        return false;
    }

    // Method to book seats using pessimistic locking
    public boolean bookSeatsPessimistic(int numSeats) throws NotEnoughSeatsException, SeatsAlreadyReservedException {
        pessimisticLocking.bookSeats(numSeats);
        return false;
    }

    // Method to get available seats using optimistic locking
    public int getAvailableSeatsOptimistic() {
        return optimisticLocking.getAvailableSeats();
    }

    // Method to get available seats using pessimistic locking
    public int getAvailableSeatsPessimistic() {
        return pessimisticLocking.getAvailableSeats();
    }
}
