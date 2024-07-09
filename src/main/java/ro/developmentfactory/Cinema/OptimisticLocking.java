package ro.developmentfactory.Cinema;

import java.util.concurrent.atomic.AtomicInteger;

public class OptimisticLocking {
    private final int totalSeats = 100;
    private final AtomicInteger occupiedSeats = new AtomicInteger(0);

    public boolean bookSeats(int numSeats) throws NotEnoughSeatsException,SeatsAlreadyReservedException {
        if (numSeats > totalSeats) {
            throw new NotEnoughSeatsException ("Error: Number of seats exceeds total seats");
        }

        int currentOccupiedSeats = occupiedSeats.get();
        if (currentOccupiedSeats + numSeats > totalSeats) {
            throw new SeatsAlreadyReservedException ("Error: There are no available seats to book.");
        }

        while(!occupiedSeats.compareAndSet(currentOccupiedSeats, currentOccupiedSeats + numSeats)) {
            currentOccupiedSeats = occupiedSeats.get();
        }
        return false;
    }
    public int getAvailableSeats() {
        return totalSeats - occupiedSeats.get();
    }
    }

