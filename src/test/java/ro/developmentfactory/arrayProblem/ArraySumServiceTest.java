package ro.developmentfactory.arrayProblem;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArraySumServiceTest {

    @Test
    public void testCalculate_EmptyArray() {
        ArraySumService arraySumService = new ArraySumService();
        int[] array = {};
        int result = arraySumService.calculate(array);
        assertEquals(0, result);
    }

    @Test
    public void testCalculate_SingleElementZero() {
        ArraySumService arraySumService = new ArraySumService();
        int[] array = {0};
        int result = arraySumService.calculate(array);
        assertEquals(0, result);
    }

    @Test
    public void testCalculate_NegativeNumbers() {
        ArraySumService arraySumService = new ArraySumService();
        int[] array = {-1, -2, -3};
        int result = arraySumService.calculate(array);
        assertEquals(-6, result);
    }

    @Test
    public void testCalculate_PositiveNumbers() {
        ArraySumService arraySumService = new ArraySumService();
        int[] array = {1, 2, 3};
        int result = arraySumService.calculate(array);
        assertEquals(6, result);
    }

    @Test
    public void testCalculate_MixedNumbers() {
        ArraySumService arraySumService = new ArraySumService();
        int[] array = {-1, 2, 0, 5, -3};
        int result = arraySumService.calculate(array);
        assertEquals(3, result);
    }

    @Test
    public void testCalculate_VeryLargeArray() {
        ArraySumService arraySumService = new ArraySumService();
        int[] array = new int[1_000_000];
        // We initialize the array with various values(for example, all elements can be 1)
        Arrays.fill(array, 1);

        int expectedSum = 1_000_000; // The sum of 1_000_000 elements with value 1 is 1_000_000

        int result = arraySumService.calculate(array);
        assertEquals(expectedSum, result);
    }


}
