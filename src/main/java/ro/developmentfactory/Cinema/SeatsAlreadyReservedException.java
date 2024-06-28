package ro.developmentfactory.Cinema;

public class SeatsAlreadyReservedException extends Exception{
    public SeatsAlreadyReservedException(String message){
        super(message);
    }
}

