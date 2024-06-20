package ro.developmentfactory.Cynema;

import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;

@Data
public class Cynema {
    private AtomicInteger locuriDisponibile;


    public Cynema(AtomicInteger locuriDisponibile) {
        this.locuriDisponibile = locuriDisponibile;
    }

    public synchronized String RezervaLocuriDisponibile(int nrLocuriSolicitate) throws Exception {
        int locuriCurenteDisponibile = locuriDisponibile.get();
        if (nrLocuriSolicitate > 100) {
            throw new Exception("Error: Locuri solicitate more than 100");
        }
        if (locuriCurenteDisponibile < nrLocuriSolicitate) {
            throw new Exception("error: Locuri solicitate more than nrLocuriSolicitate");
        }
        locuriDisponibile.set(locuriCurenteDisponibile - nrLocuriSolicitate);
        return "Rezervare cu succes" + nrLocuriSolicitate + "locuri";
    }

    public String optimistRezervareLocuri(int nrLocuriSolicitate) throws Exception {
        int locuriCurenteDisponibile;
        do {
            locuriCurenteDisponibile = locuriDisponibile.get();
            if (nrLocuriSolicitate > 100) {
                throw new Exception("error: Locuri solicitate more than 100");
            }

            if (locuriCurenteDisponibile < nrLocuriSolicitate) {
                throw new Exception("error: Locuri solicitate more than nrLocuriSolicitate");
            }
        } while (!locuriDisponibile.compareAndSet(locuriCurenteDisponibile, locuriCurenteDisponibile - nrLocuriSolicitate));
        return "Rezervare cu succes pentru " + nrLocuriSolicitate + "locuri";
    }

    public int getLocuriDisponibile() {
        return locuriDisponibile.get();
    }


}

