package ro.developmentfactory.Cinema;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Main {
    @Test
    void testPessimisticLock() throws InterruptedException {
        PessimisticLocking pessimisticLocking =  new PessimisticLocking();
        Thread thread1 = new Thread(() -> {
            try {
                pessimisticLocking.bookSeats(3);
            } catch (NotEnoughSeatsException | SeatsAlreadyReservedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                pessimisticLocking.bookSeats(5);
            } catch (NotEnoughSeatsException | SeatsAlreadyReservedException e) {
                throw new RuntimeException(e);
            }
        });
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        assertEquals(92,pessimisticLocking.getAvailableSeats(),"Available seats should be 93 after bookings");
    }

    @Test
    void testOptimisticLock() throws InterruptedException {
        OptimisticLocking optimisticLocking =  new OptimisticLocking();
        Thread thread3 = new Thread(() -> {
            try {
                optimisticLocking.bookSeats(4);
            } catch (NotEnoughSeatsException | SeatsAlreadyReservedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread thread4 = new Thread(() -> {
            try {
                optimisticLocking.bookSeats(6);
            } catch (NotEnoughSeatsException | SeatsAlreadyReservedException e) {
                throw new RuntimeException(e);
            }
        });
        thread3.start();
        thread4.start();

        thread3.join();
        thread4.join();
        assertEquals(90,optimisticLocking.getAvailableSeats(),"Available seats should be 94 after bookings");

    }

}
