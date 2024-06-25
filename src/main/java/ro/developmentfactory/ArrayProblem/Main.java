package ro.developmentfactory.ArrayProblem;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
        public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(5);
        IntermediateSuma problem = new IntermediateSuma();

        executor.submit(problem);

        try {
            if (!executor.awaitTermination(30, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }

        }catch (InterruptedException e){
            executor.shutdownNow();
            Thread.currentThread().interrupt();
            System.err.println("Main thread interrupted");
        }
        System.out.println("Sum = " + problem.getTotalSum());
    }
}