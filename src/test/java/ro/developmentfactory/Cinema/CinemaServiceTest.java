package ro.developmentfactory.Cinema;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CinemaServiceTest {

    private CinemaService cinemaService;
    private OptimisticLocking optimisticLocking;
    private PessimisticLocking pessimisticLocking;

    @BeforeEach
    public void setup() {
        optimisticLocking = new OptimisticLocking();
        pessimisticLocking = new PessimisticLocking();
        cinemaService = new CinemaService(optimisticLocking, pessimisticLocking);
    }

    @Test
    public void testBookSeatsOptimistic() throws NotEnoughSeatsException, SeatsAlreadyReservedException {
        // Given
        int initialAvailableSeats = 100;
        cinemaService = new CinemaService(new OptimisticLocking(), new PessimisticLocking());

        // When
        cinemaService.bookSeatsOptimistic(3);

        // Then
        assertEquals(97, cinemaService.getAvailableSeatsOptimistic(), "Available seats should be 97 after booking 3 seats optimistically.");
    }

    @Test
    public void testBookSeatsPessimistic() throws NotEnoughSeatsException, SeatsAlreadyReservedException {
        // Given
        int initialAvailableSeats = 100;
        cinemaService = new CinemaService(new OptimisticLocking(), new PessimisticLocking());

        // When
        cinemaService.bookSeatsPessimistic(5);

        // Then
        assertEquals(95, cinemaService.getAvailableSeatsPessimistic(), "Available seats should be 95 after booking 5 seats pessimistically.");
    }

    @Test
    public void testBookSeatsMixed() throws NotEnoughSeatsException, SeatsAlreadyReservedException {
        // Given
        int initialAvailableSeatsOptimistic = 100;
        int initialAvailableSeatsPessimistic = 100;
        cinemaService = new CinemaService(new OptimisticLocking(), new PessimisticLocking());

        // When
        cinemaService.bookSeatsOptimistic(2);
        cinemaService.bookSeatsPessimistic(3);

        // Then
        assertEquals(98, cinemaService.getAvailableSeatsOptimistic(), "Available seats (optimistic) should be 98 after mixed bookings.");
        assertEquals(97, cinemaService.getAvailableSeatsPessimistic(), "Available seats (pessimistic) should be 97 after mixed bookings.");
    }

    @Test
    public void testBookSeatsThrowsNotEnoughSeatsException() {
        // Given
        int initialAvailableSeats = 100;
        cinemaService = new CinemaService(new OptimisticLocking(), new PessimisticLocking());

        // Then
        assertThrows(NotEnoughSeatsException.class, () -> cinemaService.bookSeatsOptimistic(101), "Exception should be thrown when booking more seats than available (optimistic).");
        assertThrows(NotEnoughSeatsException.class, () -> cinemaService.bookSeatsPessimistic(101), "Exception should be thrown when booking more seats than available (pessimistic).");
    }

    @Test
    public void testBookSeatsThrowsSeatsAlreadyReservedException() throws NotEnoughSeatsException, SeatsAlreadyReservedException {
        // Given
        int initialAvailableSeats = 100;
        cinemaService = new CinemaService(new OptimisticLocking(), new PessimisticLocking());

        // When
        cinemaService.bookSeatsOptimistic(90);
        cinemaService.bookSeatsPessimistic(90);

        // Then
        assertThrows(SeatsAlreadyReservedException.class, () -> cinemaService.bookSeatsOptimistic(11), "Exception should be thrown when booking more seats than available (optimistic).");
        assertThrows(SeatsAlreadyReservedException.class, () -> cinemaService.bookSeatsPessimistic(11), "Exception should be thrown when booking more seats than available (pessimistic).");
    }
}
