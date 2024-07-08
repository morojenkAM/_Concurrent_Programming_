package ro.developmentfactory.arrayProblem;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class MainTest {

    @Mock
    IntermediateSuma mockIntermediateSuma;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Test total sum calculation")
    void testTotalSumCalculation_ExecutedTasks_CorrectTotalSum() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        int[] array = { 23, 45, 67, 89, 12, 34, 56, 78, 90, 1 };
        int numberOfTasks = 5;
        List<IntermediateSuma> tasks = new ArrayList<>();

        int chunkSize = array.length / numberOfTasks;

        when(mockIntermediateSuma.getTotalSum()).thenReturn(10);

        for (int i = 0; i < numberOfTasks; i++) {
            int start = i * chunkSize;
            int end = (i == numberOfTasks - 1) ? array.length : start + chunkSize;
            int[] subArray = Arrays.copyOfRange(array, start, end);
            IntermediateSuma problem = new IntermediateSuma(subArray);
            executor.submit(problem);
            tasks.add(problem);
        }

        executor.shutdown();
        assertTrue(executor.awaitTermination(10, TimeUnit.SECONDS));

        int totalSum = 0;
        for (IntermediateSuma task : tasks) {
            totalSum += task.getTotalSum();
        }

        assertEquals(495, totalSum);
    }
}
