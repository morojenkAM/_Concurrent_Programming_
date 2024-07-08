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
    void testBookSeatsOptimistic_SeatsAlreadyReserved() throws NotEnoughSeatsException, SeatsAlreadyReservedException {
        // Given
        int numSeatsToBook = 10;
        cinemaService.bookSeatsOptimistic(numSeatsToBook);

        // When
        boolean bookingResult = cinemaService.bookSeatsOptimistic(numSeatsToBook);

        // Then
        assertFalse(bookingResult);
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
    void testBookSeatsPessimistic_NotEnoughSeatsException() {
        // Given
        int numSeatsToBook = 150;

        // When, Then
        assertThrows(NotEnoughSeatsException.class, () -> cinemaService.bookSeatsPessimistic(numSeatsToBook));
    }

    @Test
    void testBookSeatsPessimistic_SeatsAlreadyReserved() throws NotEnoughSeatsException, SeatsAlreadyReservedException {
        // Given
        int numSeatsToBook = 10;
        cinemaService.bookSeatsPessimistic(numSeatsToBook);

        // When
        boolean bookingResult = cinemaService.bookSeatsPessimistic(numSeatsToBook);

        // Then
        assertFalse(bookingResult);
    }
}
