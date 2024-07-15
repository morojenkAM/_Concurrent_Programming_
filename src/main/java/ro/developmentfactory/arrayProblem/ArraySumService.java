package ro.developmentfactory.arrayProblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ArraySumService {

    private ExecutorService executor;

    public ArraySumService(int numberOfThreads) {
        this.executor = Executors.newFixedThreadPool(numberOfThreads);
    }

    public int calculate(int[] array) {
        return getElementSum(array);
    }

    private int getElementSum(int[] array) {
        int sum = 0;
        for (int value : array) {
            sum += value;
        }
        return sum;
    }

    public int calculateTotalSum(int[] array, int numberOfTasks) {
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

        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
            System.err.println("Main thread interrupted");
        }

        int totalSum = 0;
        for (IntermediateSuma task : tasks) {
            totalSum += task.getTotalSum();
        }
        return totalSum;
    }

    public void shutdownExecutor(){
        executor.shutdown();
    }

    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return executor.awaitTermination(timeout, unit);
    }
}

