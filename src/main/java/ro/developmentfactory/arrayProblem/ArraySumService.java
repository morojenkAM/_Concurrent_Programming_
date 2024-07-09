package ro.developmentfactory.arrayProblem;

public class ArraySumService {
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
    }

