package ro.developmentfactory.Cinema;

import java.util.concurrent.atomic.AtomicInteger;

public class OptimisticLocking {
    private final int totalSeats = 100;
    private final AtomicInteger occupiedSeats = new AtomicInteger(0);

    public void bookSeats(int numSeats) {
        if (numSeats > totalSeats) {
            System.out.println("Error: Number of seats exceeds total seats");
            return;
        }

        int currentOccupiedSeats = occupiedSeats.get();
        if (currentOccupiedSeats + numSeats <= totalSeats) {
            // Atomic verification and update using optimistic locking
            if (occupiedSeats.compareAndSet(currentOccupiedSeats, currentOccupiedSeats + numSeats)) {
                System.out.println(numSeats + " seats reserved. Seats occupied now: " + occupiedSeats.get());
            } else {
                System.out.println("Error: Seats already reserved by another thread.");
            }
        } else {
            System.out.println("Error: Not enough seats available.");
        }
    }

    public int getAvailableSeats() {
        return totalSeats - occupiedSeats.get();
    }
}
