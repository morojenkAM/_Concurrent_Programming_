package ro.developmentfactory.Cinema;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OptimisticLockingTest {

    @Test
     void testBookSeats_NotEnoughSeatsException() {
        OptimisticLocking bookingService = new OptimisticLocking();
        int requestSeats = 105;

        assertThrows(NotEnoughSeatsException.class, () -> {
            bookingService.bookSeats(requestSeats);
        });

        assertEquals(100, bookingService.getAvailableSeats(),"Available seats should not change after failed booking");
    }

    @Test
    void testBookSeats_SeatsAlreadyReservedException(){
        OptimisticLocking bookingService = new OptimisticLocking();
        int initialSeats = 50;
        try {
            bookingService.bookSeats(initialSeats);
        } catch (NotEnoughSeatsException | SeatsAlreadyReservedException e) {
            // Handle exceptions, if necessary (though in this case, we don't expect exceptions)
        }

        // Try to book more seats than available, which should throw SeatsAlreadyReservedException
        assertThrows(SeatsAlreadyReservedException.class, () -> {
            bookingService.bookSeats(51); // Trying to book 51 seats when only 50 are available
        });

        // After the exception is thrown, ensure that the state of the booking service is unchanged
        assertEquals(50, bookingService.getAvailableSeats(), "Available seats should not change after failed booking");
    }


}
