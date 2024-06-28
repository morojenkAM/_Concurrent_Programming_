package ro.developmentfactory.arrayProblem;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class IntermediateSuma implements Runnable {
    private final int[] array;
    private  int totalSum ;

    @Override
    public void run() {
        calculateSum();
    }

    private void calculateSum() {
        this.totalSum = getElementSum(array);
    }

    private int getElementSum(int[] array) {
        int sum = 0;
        for (int value : array) {
            sum += value;
        }
        return sum;
    }
}
