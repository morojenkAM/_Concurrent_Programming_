package ro.developmentfactory.arrayProblem;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        int[] array = { 23, 45, 67, 89, 12, 34, 56, 78, 90, 1 };
        int numberOfTasks = 5;
        List<IntermediateSuma> tasks = new ArrayList<>();

        int chunkSize = array.length / numberOfTasks;

        for (int i = 0; i < numberOfTasks; i++) {
            int start = i * chunkSize;
            int end = (i == numberOfTasks - 1) ? array.length : start + chunkSize;
            int[] subArray = Arrays.copyOfRange(array, start, end);
            IntermediateSuma problem = new IntermediateSuma(subArray);
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
            totalSum += task.getTotalSum();
        }
        System.out.println("Total sum :" +totalSum);
    }
}