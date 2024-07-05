package ro.developmentfactory.arrayProblem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class IntermediateSumaTest {

    @Test
    void testCalculateSumWithPositiveNumbers() {
        int[] array = {1, 2, 3, 4, 5};
        IntermediateSuma intermediateSuma = new IntermediateSuma(array);
        intermediateSuma.run();
        assertEquals(15, intermediateSuma.getTotalSum());
    }

    @Test
    void testCalculateSumWithNegativeNumbers() {
        int[] array = {-1, -2, -3, -4, -5};
        IntermediateSuma intermediateSuma = new IntermediateSuma(array);
        intermediateSuma.run();
        assertEquals(-15, intermediateSuma.getTotalSum());
    }

    @Test
    void testCalculateSumWithMixedNumbers() {
        int[] array = {-1, 0, 1, -2, 2};
        IntermediateSuma intermediateSuma = new IntermediateSuma(array);
        intermediateSuma.run();
        assertEquals(0, intermediateSuma.getTotalSum());
    }

    @Test
    void testCalculateSumWithEmptyArray() {
        int[] array = {};
        IntermediateSuma intermediateSuma = new IntermediateSuma(array);
        intermediateSuma.run();
        assertEquals(0, intermediateSuma.getTotalSum());
    }

    @Test
    void testCalculateSumWithZeroNumbers() {
        int[] array = {0, 0, 0, 0, 0, 0, 0};
        IntermediateSuma intermediateSuma = new IntermediateSuma(array);
        intermediateSuma.run();
        assertEquals(0, intermediateSuma.getTotalSum());
    }
}
