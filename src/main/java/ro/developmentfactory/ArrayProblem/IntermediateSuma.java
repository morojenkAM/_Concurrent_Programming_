package ro.developmentfactory.ArrayProblem;

import lombok.Getter;

import java.util.Arrays;
import java.util.Scanner;

@Getter
public class IntermediateSuma implements Runnable {

    private int totalSum;

    @Override
    public void run() {
        arrayDivide();
    }

    private void arrayDivide() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of the array");
        int size = sc.nextInt();
        int[] array = new int[size];
        System.out.println("Enter the elements of the array");
        for (int i = 0; i < size; i++) {
            array[i] = sc.nextInt();
        }
        int[] arrayFirstPart = Arrays.copyOfRange(array, 0, array.length / 2);
        int[] arraySecondPart = Arrays.copyOfRange(array, array.length / 2, array.length);

        int sumFirstPart = totalSum(arrayFirstPart);
        int sumSecondPart = totalSum(arraySecondPart);
        this.totalSum = sumFirstPart + sumSecondPart;

    }

    private int totalSum(int[] array) {
        int sum = 0;
        for (int value : array) {
            sum += value;
        }
        return sum;
    }

    public int getTotalSum() {
        return totalSum;
    }
}
