package edu.coursera.parallel.week2;

import edu.coursera.TestExecutor;
import edu.coursera.TestResults;
import edu.coursera.Util;
import java.util.DoubleSummaryStatistics;
import java.util.Random;
import junit.framework.TestCase;

public class StudentAnalyticsTest extends TestCase {
  static final int REPEATS = 10;
  private static final String[] firstNames = {"Sanjay", "Yunming", "John", "Vivek", "Shams", "Max"};
  private static final String[] lastNames = {
    "Chatterjee", "Zhang", "Smith", "Sarkar", "Imam", "Grossman"
  };

  private static final double nCores = Util.getNCores();

  private static final StudentAnalytics analytics = new StudentAnalytics();

  private Student[] generateStudentData() {
    final int N_STUDENTS = 8000000;
    final int N_CURRENT_STUDENTS = 600000;

    Student[] students = new Student[N_STUDENTS];
    Random r = new Random(123);

    for (int s = 0; s < N_STUDENTS; s++) {
      final String firstName = firstNames[r.nextInt(firstNames.length)];
      final String lastName = lastNames[r.nextInt(lastNames.length)];
      final double age = r.nextDouble() * 100.0;
      final int grade = 1 + r.nextInt(100);
      final boolean current = (s < N_CURRENT_STUDENTS);

      students[s] = new Student(firstName, lastName, age, grade, current);
    }

    return students;
  }

  private double averageAgeOfEnrolledStudentsHelper(final int repeats) {
    final Student[] students = generateStudentData();

    final TestExecutor testExecutor = new TestExecutor(repeats);
    TestResults<Double> seq =
        testExecutor.execute(students, analytics::averageAgeOfEnrolledStudentsImperative);

    TestResults<Double> par =
        testExecutor.execute(students, analytics::averageAgeOfEnrolledStudentsParallelStream);

    DoubleSummaryStatistics parStat =
        par.getResults().stream().mapToDouble(d -> d).summaryStatistics();
    DoubleSummaryStatistics seqStat =
        seq.getResults().stream().mapToDouble(d -> d).summaryStatistics();

    final double eps = 1E-5;
    assertTrue(
        "Mismatch in calculated values", Math.abs(parStat.getMin() - seqStat.getMin()) < eps);
    assertTrue(
        "Mismatch in calculated values", Math.abs(parStat.getMax() - seqStat.getMax()) < eps);
    assertTrue(
        "Mismatch in calculated values",
        Math.abs(parStat.getAverage() - seqStat.getAverage()) < eps);
    assertTrue(
        "Mismatch in calculated values", Math.abs(parStat.getSum() - seqStat.getSum()) < 10 * eps);

    return seq.getExecutionTime() / par.getExecutionTime();
  }

  /*
   * Test correctness of averageAgeOfEnrolledStudentsParallelStream.
   */
  public void testAverageAgeOfEnrolledStudents() {
    averageAgeOfEnrolledStudentsHelper(1);
  }

  /*
   * Test performance of averageAgeOfEnrolledStudentsParallelStream.
   */
  public void testAverageAgeOfEnrolledStudentsPerf() {
    final double speedup = averageAgeOfEnrolledStudentsHelper(REPEATS);
    String msg = "Expected parallel version to run at least 1.2x faster but speedup was " + speedup;
    assertTrue(msg, speedup > 1.2);
  }

  private double mostCommonFirstNameOfInactiveStudentsHelper(final int repeats) {
    final Student[] students = generateStudentData();

    final TestExecutor testExecutor = new TestExecutor(repeats);
    TestResults<String> seq =
        testExecutor.execute(students, analytics::mostCommonFirstNameOfInactiveStudentsImperative);

    TestResults<String> par =
        testExecutor.execute(
            students, analytics::mostCommonFirstNameOfInactiveStudentsParallelStream);

    assertEquals("Mismatch in calculated values", par.getResults(), seq.getResults());

    return seq.getExecutionTime() / par.getExecutionTime();
  }

  /*
   * Test correctness of mostCommonFirstNameOfInactiveStudentsParallelStream.
   */
  public void testMostCommonFirstNameOfInactiveStudents() {
    mostCommonFirstNameOfInactiveStudentsHelper(1);
  }

  /*
   * Test performance of mostCommonFirstNameOfInactiveStudentsParallelStream.
   */
  public void testMostCommonFirstNameOfInactiveStudentsPerf() {
    final double speedup = mostCommonFirstNameOfInactiveStudentsHelper(REPEATS);
    final double expectedSpeedup = nCores * 0.5;
    String msg = "Expected speedup to be at least " + expectedSpeedup + " but was " + speedup;
    assertTrue(msg, speedup >= expectedSpeedup);
  }

  private double countNumberOfFailedStudentsOlderThan20Helper(final int repeats) {
    final Student[] students = generateStudentData();

    final TestExecutor testExecutor = new TestExecutor(repeats);
    TestResults<Integer> seq =
        testExecutor.execute(students, analytics::countNumberOfFailedStudentsOlderThan20Imperative);

    TestResults<Integer> par =
        testExecutor.execute(
            students, analytics::countNumberOfFailedStudentsOlderThan20ParallelStream);

    assertEquals("Mismatch in calculated values", seq.getResults(), par.getResults());

    return seq.getExecutionTime() / par.getExecutionTime();
  }

  /*
   * Test correctness of countNumberOfFailedStudentsOlderThan20ParallelStream.
   */
  public void testCountNumberOfFailedStudentsOlderThan20() {
    countNumberOfFailedStudentsOlderThan20Helper(1);
  }

  /*
   * Test performance of countNumberOfFailedStudentsOlderThan20ParallelStream.
   */
  public void testCountNumberOfFailedStudentsOlderThan20Perf() {
    final double speedup = countNumberOfFailedStudentsOlderThan20Helper(REPEATS);
    String msg = "Expected parallel version to run at least 1.2x faster but speedup was " + speedup;
    assertTrue(msg, speedup > 1.2);
  }
}
