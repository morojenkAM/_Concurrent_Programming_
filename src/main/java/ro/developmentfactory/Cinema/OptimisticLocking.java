package ro.developmentfactory.Cinema;

import java.util.concurrent.atomic.AtomicInteger;

public class OptimisticLocking {
    private final int totalSeats = 100;
    private final AtomicInteger occupiedSeats = new AtomicInteger(0);

    public void bookSeats(int numSeats) throws NotEnoughSeatsException,SeatsAlreadyReservedException {
        if (numSeats > totalSeats) {
            throw new NotEnoughSeatsException ("Error: Number of seats exceeds total seats");
        }

        int currentOccupiedSeats = occupiedSeats.get();
        if (currentOccupiedSeats + numSeats <= totalSeats) {
            // Atomic verification and update using optimistic locking
            boolean reservationSuccessful = false;
            while (!reservationSuccessful) {
                if (occupiedSeats.compareAndSet(currentOccupiedSeats, currentOccupiedSeats + numSeats)) {
                    System.out.println(numSeats + " seats reserved. Seats occupied now: " + occupiedSeats.get());
                    reservationSuccessful = true; // Break the loop if successful
                } else {
                    throw new SeatsAlreadyReservedException ("Error: Seats already reserved by another thread.");
                }
            }
        } else {
            throw new NotEnoughSeatsException("Error: Not enough seats available.");
        }
    }

    public int getAvailableSeats() {
        return totalSeats - occupiedSeats.get();
    }
}
