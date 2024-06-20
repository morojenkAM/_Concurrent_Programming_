package ro.developmentfactory.Cynema;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class MainCynema {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        AtomicInteger atomicInteger = new AtomicInteger(100);

        Cynema cynema = new Cynema(atomicInteger);

        Runnable task1 = () -> {
            try {
                System.out.println(cynema.RezervaLocuriDisponibile(10));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        };
        Runnable task2 = () -> {
            try {
                System.out.println(cynema.RezervaLocuriDisponibile(50));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        };

        Runnable task3 = () -> {
            try {
                System.out.println(cynema.RezervaLocuriDisponibile(30));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        };

        executorService.submit(task1);
        executorService.submit(task2);
        executorService.submit(task3);

        executorService.shutdown();
    };
}