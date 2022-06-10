package practice;

import com.mamay.leetcode.practice.EpmPrjInterviewTask;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EpmPrjInterviewTaskTest {

    @Test
    public void test1() {
        System.out.println(EpmPrjInterviewTask.calculateDayOfYear(EpmPrjInterviewTask.Month.AUG, 1, 2022));
        Assertions.assertThrows(RuntimeException.class,
                () -> EpmPrjInterviewTask.calculateDayOfYear(EpmPrjInterviewTask.Month.JAN, 32, 2022));
        Assertions.assertThrows(RuntimeException.class,
                () -> EpmPrjInterviewTask.calculateDayOfYear(EpmPrjInterviewTask.Month.JAN, -2, 2022));
    }
}