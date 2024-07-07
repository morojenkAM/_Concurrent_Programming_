package ro.developmentfactory.arrayProblem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class IntermediateSumaTest {

    @Test
    @DisplayName("Calculate sum with positive numbers")
    void calculateSumWithPositiveNumbers_ValidInput_CorrectSum() {
        int[] array = {1, 2, 3, 4, 5};
        IntermediateSuma intermediateSuma = new IntermediateSuma(array);
        intermediateSuma.run();
        assertEquals(15, intermediateSuma.getTotalSum());
    }

    @Test
    @DisplayName("Calculate sum with positive numbers")
    void calculateSumWithNegativeNumbers_ValidInput_CorrectSum() {
        int[] array = {-1, -2, -3, -4, -5};
        IntermediateSuma intermediateSuma = new IntermediateSuma(array);
        intermediateSuma.run();
        assertEquals(-15, intermediateSuma.getTotalSum());
    }

    @Test
    @DisplayName("Calculate sum with mixed numbers")
    void calculateSumWithMixedNumbers_ValidInput_CorrectSum() {
        int[] array = {-1, 0, 1, -2, 2};
        IntermediateSuma intermediateSuma = new IntermediateSuma(array);
        intermediateSuma.run();
        assertEquals(0, intermediateSuma.getTotalSum());
    }

    @Test
    @DisplayName("Calculate sum with empty array")
    void calculateSumWithEmptyArray_EmptyArray_ZeroSum() {
        int[] array = {};
        IntermediateSuma intermediateSuma = new IntermediateSuma(array);
        intermediateSuma.run();
        assertEquals(0, intermediateSuma.getTotalSum());
    }

    @Test
    @DisplayName("Calculate sum with zero numbers")
    void calculateSumWithZeroNumbers_ZeroValues_ZeroSum() {
        int[] array = {0, 0, 0, 0, 0, 0, 0};
        IntermediateSuma intermediateSuma = new IntermediateSuma(array);
        intermediateSuma.run();
        assertEquals(0, intermediateSuma.getTotalSum());
    }
}
