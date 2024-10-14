package part1.week4;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
public class BinarySearchTreeTest {

  private BinarySearchTree<Integer, String> tree;

  @BeforeEach
  public void init() {
    tree = createTree();
  }

  @Test
  public void testSize() {
    Assertions.assertEquals(10, tree.size());
  }

  @Test
  public void testGet() {
    Assertions.assertNotNull(tree.get(20));
    Assertions.assertNull(tree.get(33));
  }

  @Test
  public void testDelete() {
    Assertions.assertNotNull(tree.get(40));
    Assertions.assertNotNull(tree.get(50));
    Assertions.assertNotNull(tree.get(60));
    tree.delete(40);
    tree.delete(50);
    tree.delete(60);
    Assertions.assertNull(tree.get(40));
    Assertions.assertNull(tree.get(50));
    Assertions.assertNull(tree.get(60));
    tree.delete(10);
    tree.delete(20);
    tree.delete(80);
    Assertions.assertEquals(4, tree.size());
  }

  @Test
  public void testPut() {
    Assertions.assertNull(tree.get(25));
    tree.put(25, "Austria");
    Assertions.assertNotNull(tree.get(25));
  }

  @Test
  public void testFloor() {
    Assertions.assertEquals(50, (int) tree.floor(55));
    Assertions.assertEquals(70, (int) tree.floor(70));
    Assertions.assertEquals(10, (int) tree.floor(14));
    Assertions.assertNull(tree.floor(4));
  }

  @Test
  public void testRank() {
    tree = createTree();
    Assertions.assertEquals(10, (int) tree.rank(110));
    Assertions.assertEquals(4, (int) tree.rank(50));
    Assertions.assertEquals(7, (int) tree.rank(77));
    Assertions.assertEquals(1, (int) tree.rank(14));
    Assertions.assertEquals(0, (int) tree.rank(4));
  }

  @Test
  public void testIterator() {
    Iterator<BinarySearchTree<Integer, String>.Node> iterator = tree.iterator();
    while (iterator.hasNext()) {
      BinarySearchTree<Integer, String>.Node node = iterator.next();
      Assertions.assertNotNull(node);
      System.out.println(node);
    }
  }

  @Test
  public void testKeys() {
    List<Integer> keys = new ArrayList<>();
    tree.keys().forEach(keys::add);
    Assertions.assertTrue(
        CollectionUtils.isEqualCollection(
            Arrays.asList(10, 20, 30, 40, 50, 60, 70, 80, 90, 100), keys));
  }

  private BinarySearchTree<Integer, String> createTree() {
    List<Integer> keys = Arrays.asList(10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 10);
    List<String> values =
        Arrays.asList(
            "Italy",
            "Germany",
            "France",
            "Spain",
            "Portugal",
            "Russia",
            "Belgium",
            "Denmark",
            "Sweden",
            "Norway",
            "Finland");
    Collections.shuffle(keys);
    Collections.shuffle(values);

    BinarySearchTree<Integer, String> tree = new BinarySearchTree<Integer, String>();
    for (int i = 0; i < keys.size(); i++) {
      tree.put(keys.get(i), values.get(i));
    }
    return tree;
  }
}
