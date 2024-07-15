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
        ArraySumService arraySumService = new ArraySumService(numberOfTasks);
        int totalSum = arraySumService.calculateTotalSum(array, numberOfTasks);
        arraySumService.shutdownExecutor();

        System.out.println("Total sum" + totalSum);
    }
}