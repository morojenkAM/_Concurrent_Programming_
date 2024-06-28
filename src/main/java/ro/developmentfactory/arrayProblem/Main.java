package ro.developmentfactory.arrayProblem;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(5);
        int numberOfTasks = 5;
        List<IntermediateSuma> tasks = new ArrayList<>();

        for (int i = 0; i < numberOfTasks; i++) {
            IntermediateSuma problem = new IntermediateSuma();
            executor.submit(problem);
            tasks.add(problem);
        }

        try {
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }

        }catch (InterruptedException e){
            executor.shutdownNow();
            Thread.currentThread().interrupt();
            System.err.println("Main thread interrupted");
        }

        int totalSum = 0;
        for (IntermediateSuma task : tasks) {
            totalSum = task.getTotalSum();
        }
        System.out.println("Total sum :" +totalSum);
    }
}