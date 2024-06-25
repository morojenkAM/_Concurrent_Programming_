package ro.developmentfactory.Cinema;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PessimisticLocking {
    private final int totalSeats = 100;
    private int occupiedSeats = 0;
    private Lock lock = new ReentrantLock();

    public boolean bookSeats(int numSeats) {
        lock.lock(); // Pessimistic Locking starts here
        try {
            if (numSeats > totalSeats) {
                System.out.println("Error: Number of seats exceeds total seats");
                return false;
            }
            if (numSeats + occupiedSeats <= totalSeats) {
                occupiedSeats += numSeats;
                System.out.println(numSeats + " seats reserved. Seats occupied now: " + occupiedSeats);
                return true;
            } else {
                System.out.println("Error: Not enough seats available.");
                return false;
            }
        } finally {
            lock.unlock();
        }
    }

    public int getAvailableSeats() {
        return totalSeats - occupiedSeats;
    }
}
