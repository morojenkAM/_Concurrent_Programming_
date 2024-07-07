package ro.developmentfactory.Cinema;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SeatsAlreadyReservedExceptionTest {
        @Test
        @DisplayName("Test Seats Already Reserved Exception - Pessimistic Locking")
        void testSeatsAlreadyReservedException_PessimisticLocking_NotEnoughSeats() {
            PessimisticLocking bookingService = new PessimisticLocking(); // Instantiate the booking service
            int requestSeats = 105;

            assertThrows(NotEnoughSeatsException.class, () -> {
                bookingService.bookSeats(requestSeats);
            });

            // After the exception is thrown, ensure that the state of the booking service is unchanged
            assertEquals(100, bookingService.getAvailableSeats(), "Available seats should not change after failed booking");

            try {
                bookingService.bookSeats(50); // Book 50 seats
            } catch (NotEnoughSeatsException | SeatsAlreadyReservedException e) {
                assertEquals("Error: Number of seats exceeds total seats", e.getMessage());
            }

            // Check the number of available seats after successful booking
            assertEquals(50, bookingService.getAvailableSeats(), "Available seats should be updated correctly after successful booking");
        }

        @Test
        @DisplayName("Test Seats Already Reserved Exception - Optimistic Locking")
        void testSeatsAlreadyReservedException_PessimisticLocking_SeatsAlreadyReserved() {
            OptimisticLocking bookingService = new OptimisticLocking(); // Instantiate the booking service
            int requestSeats = 105;

            assertThrows(NotEnoughSeatsException.class, () -> {
                bookingService.bookSeats(requestSeats);
            });

            // After the exception is thrown, ensure that the state of the booking service is unchanged
            assertEquals(100, bookingService.getAvailableSeats(), "Available seats should not change after failed booking");

            try {
                bookingService.bookSeats(50); // Book 50 seats
            } catch (NotEnoughSeatsException | SeatsAlreadyReservedException e) {
                assertEquals("Error: Number of seats exceeds total seats", e.getMessage());
            }

            // Check the number of available seats after successful booking
            assertEquals(50, bookingService.getAvailableSeats(), "Available seats should be updated correctly after successful booking");
        }
    }
