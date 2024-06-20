package ro.developmentfactory.ArrayProblem;

import lombok.Data;

@Data
public class ArrayProblem implements Runnable {

    public static String sum;
    private static int[] ab = { 23, 45, 67, 89, 12, 34, 56, 78, 90, 1 };
    private int[] subArray;
    private int sumPartial;

    public ArrayProblem(int[] subArray) {
        this.subArray = subArray;
    }

    public void run(){
        calculateSum();
    }

    private void  calculateSum() {
        sumPartial = 0;
        for (int i = 0; i < subArray.length; i++) {
            sumPartial += subArray[i];
        }
    }

    public int getSum() {
        return sumPartial;
    }
}
