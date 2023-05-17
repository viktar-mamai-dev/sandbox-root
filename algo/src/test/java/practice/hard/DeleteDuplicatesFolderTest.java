package practice.hard;

import com.mamay.leetcode.practice.hard.DeleteDuplicateFolders;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

class DeleteDuplicatesFolderTest {

  private static final ListComparator comparator = new ListComparator();

  private static final DeleteDuplicateFolders solution = new DeleteDuplicateFolders();

  @Test
  public void test1() {
    List<List<String>> path =
        Arrays.asList(
            Arrays.asList("a"),
            Arrays.asList("c"),
            Arrays.asList("d"),
            Arrays.asList("a", "b"),
            Arrays.asList("c", "b"),
            Arrays.asList("d", "a"));
    List<List<String>> expected = Arrays.asList(Arrays.asList("d"), Arrays.asList("d", "a"));
    Assertions.assertEquals(expected, solution.deleteDuplicateFolder(path));
  }

  @Test
  public void test2() {
    List<List<String>> path =
        Arrays.asList(
            Arrays.asList("a"),
            Arrays.asList("c"),
            Arrays.asList("a", "b"),
            Arrays.asList("c", "b"),
            Arrays.asList("a", "b", "x"),
            Arrays.asList("a", "b", "x", "y"),
            Arrays.asList("w"),
            Arrays.asList("w", "y"));
    List<List<String>> expected =
        Arrays.asList(
            Arrays.asList("c"),
            Arrays.asList("c", "b"),
            Arrays.asList("a"),
            Arrays.asList("a", "b"));
    List<List<String>> actual = solution.deleteDuplicateFolder(path);
    Collections.sort(expected, comparator);
    Collections.sort(actual, comparator);
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void test3() {
    List<List<String>> path =
        Arrays.asList(
            Arrays.asList("a", "b"),
            Arrays.asList("c", "d"),
            Arrays.asList("c"),
            Arrays.asList("a"));
    List<List<String>> expected =
        Arrays.asList(
            Arrays.asList("c"),
            Arrays.asList("c", "d"),
            Arrays.asList("a"),
            Arrays.asList("a", "b"));
    List<List<String>> actual = solution.deleteDuplicateFolder(path);
    expected.sort(comparator);
    actual.sort(comparator);
    Assertions.assertEquals(expected, actual);
  }

  private static class ListComparator implements Comparator<List<String>> {
    @Override
    public int compare(List<String> obj1, List<String> obj2) {
      for (int i = 0; i < Math.min(obj1.size(), obj2.size()); i++) {
        int comp = obj1.get(i).compareTo(obj2.get(i));
        if (comp != 0) {
          return comp;
        }
      }
      return Integer.compare(obj1.size(), obj2.size());
    }
  }
}
