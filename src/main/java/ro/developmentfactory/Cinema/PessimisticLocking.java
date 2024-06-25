package ro.developmentfactory.Cinema;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PessimisticLocking {
    private final int totalSeats = 100;
    private int occupiedSeats = 0;
    private final Lock lock = new ReentrantLock();

    public void bookSeats(int numSeats) {
        lock.lock(); // Pessimistic Locking starts here
        try {
            if (numSeats > totalSeats) {
                System.out.println("Error: Number of seats exceeds total seats");
                return;
            }
            if (numSeats + occupiedSeats <= totalSeats) {
                occupiedSeats += numSeats;
                System.out.println(numSeats + " seats reserved. Seats occupied now: " + occupiedSeats);
            } else {
                System.out.println("Error: Not enough seats available.");
            }
        } finally {
            lock.unlock();
        }
    }

    public int getAvailableSeats() {
        return totalSeats - occupiedSeats;
    }
}
