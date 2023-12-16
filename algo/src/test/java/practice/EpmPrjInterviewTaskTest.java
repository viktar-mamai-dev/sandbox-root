package practice;

import org.junit.jupiter.api.Test;

import static com.mamay.EpmPrjInterviewTask.Month;
import static com.mamay.EpmPrjInterviewTask.calculateDayOfYear;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EpmPrjInterviewTaskTest {

    @Test
    public void test1() {
        System.out.println(calculateDayOfYear(Month.AUG, 1, 2022));
        assertThrows(RuntimeException.class,
                () -> calculateDayOfYear(Month.JAN, 32, 2022));
        assertThrows(RuntimeException.class,
                () -> calculateDayOfYear(Month.JAN, -2, 2022));
    }
}