package ro.developmentfactory.Cinema;

public class NotEnoughSeatsException extends Exception {
    public NotEnoughSeatsException(String message) {
        super(message);
    }

}
