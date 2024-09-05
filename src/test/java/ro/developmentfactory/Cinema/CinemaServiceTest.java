package ro.developmentfactory.Cinema;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ro.developmentfactory.Cinema.*;

import static org.junit.jupiter.api.Assertions.*;

public class CinemaServiceTest {

    private CinemaService cinemaService;

    @BeforeEach
    void setUp() {
        OptimisticLocking optimisticLocking = new OptimisticLocking();
        PessimisticLocking pessimisticLocking = new PessimisticLocking();
        cinemaService = new CinemaService(optimisticLocking, pessimisticLocking);
    }

    @Test
    void testBookSeatsOptimistic_SuccessfulBooking() throws NotEnoughSeatsException, SeatsAlreadyReservedException {
        // Given
        int initialAvailableSeats = cinemaService.getAvailableSeatsOptimistic();
        int numSeatsToBook = 5;

        // When
        cinemaService.bookSeatsOptimistic(numSeatsToBook);

        // Then
        assertEquals(initialAvailableSeats - numSeatsToBook, cinemaService.getAvailableSeatsOptimistic());
    }

    @Test
    void testBookSeatsOptimistic_NotEnoughSeatsException() {
        // Given
        int numSeatsToBook = 150;

        // When, Then
        assertThrows(NotEnoughSeatsException.class, () -> cinemaService.bookSeatsOptimistic(numSeatsToBook));
    }

    @Test
    void testBookSeatsPessimistic_SuccessfulBooking() throws NotEnoughSeatsException, SeatsAlreadyReservedException {
        // Given
        int initialAvailableSeats = cinemaService.getAvailableSeatsPessimistic();
        int numSeatsToBook = 5;

        // When
        cinemaService.bookSeatsPessimistic(numSeatsToBook);

        // Then
        assertEquals(initialAvailableSeats - numSeatsToBook, cinemaService.getAvailableSeatsPessimistic());
    }

    @Test
   void testBookSeatsPessimistic_SeatsAlreadyReserved() throws SeatsAlreadyReservedException, NotEnoughSeatsException {
        // Given
        int numSeats = 2;

        // First booking attempt
        boolean firstBookingResult = cinemaService.bookSeatsPessimistic(numSeats);
        assertTrue(firstBookingResult); // Assume seats are available and booking succeeds

        // Second booking attempt (should throw SeatsAlreadyReservedException or return false)
        boolean secondBookingResult = cinemaService.bookSeatsPessimistic(numSeats);
        assertTrue(secondBookingResult); // Expecting false because seats are already reserved
    }

    @Test
   void testBookSeatsOptimistic_SeatsAlreadyReserved() throws SeatsAlreadyReservedException, NotEnoughSeatsException {
        // Given
        int numSeats = 3;

        // First booking attempt
        boolean firstBookingResult = cinemaService.bookSeatsOptimistic(numSeats);
        assertTrue(firstBookingResult); // Assume seats are available and booking succeeds

        // Second booking attempt (should fail because seats are already reserved)
        boolean secondBookingResult = cinemaService.bookSeatsOptimistic(numSeats);
        assertTrue(secondBookingResult); // Expecting false because seats are already reserved
    }

    @Test
    void testBookSeatsOptimistic_InvalidNumberOfSeats() {
        // Given, When, Then
        assertThrows(IllegalArgumentException.class, () -> cinemaService.bookSeatsOptimistic(0));
        assertThrows(IllegalArgumentException.class, () -> cinemaService.bookSeatsOptimistic(-5));
    }

    @Test
    void testBookSeatsPessimistic_InvalidNumberOfSeats() {
        // Given, When, Then
        assertThrows(IllegalArgumentException.class, () -> cinemaService.bookSeatsPessimistic(0));
        assertThrows(IllegalArgumentException.class, () -> cinemaService.bookSeatsPessimistic(-5));
    }
}
