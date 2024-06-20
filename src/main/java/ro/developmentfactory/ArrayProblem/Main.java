package ro.developmentfactory.ArrayProblem;

import ro.developmentfactory.ArrayProblem.ArrayProblem;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        int numThreads = 5;
        int[] ab = { 23, 45, 67, 89, 12, 34, 56, 78, 90, 1 };
        int size = ab.length;
        int segmentationSize = size / numThreads;

        ArrayProblem problems[] = new ArrayProblem[numThreads];
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        for (int i = 0; i < numThreads; i++) {
            int start = i * segmentationSize;
            int end = (i == numThreads -1) ? size : start + segmentationSize;
            int [] subArray = new int[end - start];
            System.arraycopy(ab, start, subArray, 0, end - start);
            problems[i] = new ArrayProblem(subArray);
            executor.submit(problems[i]);
        }
        executor.shutdown();
        try {
            executor.awaitTermination(60, TimeUnit.SECONDS);

        }catch (InterruptedException e){
            e.printStackTrace();
        }
        int total = 0;
        for (ArrayProblem problem : problems) {
            total +=problem.getSum();
        }
        System.out.println("Sum = " +total);
    };
}