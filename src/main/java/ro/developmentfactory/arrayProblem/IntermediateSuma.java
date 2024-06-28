package ro.developmentfactory.arrayProblem;


import lombok.Getter;

import static java.util.Arrays.copyOfRange;

@Getter
public class IntermediateSuma implements Runnable {
    private static final int[] ab = { 23, 45, 67, 89, 12, 34, 56, 78, 90, 1 };
    private static final int size = ab.length;
    private  int totalSum ;

    @Override
    public void run() {
        arrayDivide();
    }

    private void arrayDivide() {
        int[] arrayFirstPart = copyOfRange(ab, 0, size / 2);
        int[] arraySecondPart = copyOfRange(ab, size / 2, size);

        int sumFirstPart = getElementSum(arrayFirstPart);
        int sumSecondPart = getElementSum(arraySecondPart);
        this.totalSum = sumFirstPart + sumSecondPart;
    }

    private int getElementSum(int[] array) {
        int sum = 0;
        for (int value : array) {
            sum += value;
        }
        return sum;
    }
}
