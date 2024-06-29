package ro.developmentfactory.Cinema;

public class Main {
    public static void main(String[] args) {
        PessimisticLocking pessimisticLocking = new PessimisticLocking();
        OptimisticLocking optimisticLocking = new OptimisticLocking();

        // Testing pessimistic lock
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

        // Testing optimistic lock
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

        // Display available seats after bookings
        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Available seats after bookings:");
        System.out.println("Pessimistic Locking: " + pessimisticLocking.getAvailableSeats());
        System.out.println("Optimistic Locking: " + optimisticLocking.getAvailableSeats());
    }
}
