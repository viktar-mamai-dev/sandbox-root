package part1.week4.iq;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import part1.week4.BinarySearchTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Task5Test {

    private Task5 tree;

    @Before
    public void init() {
        tree = createTree();
    }

    @Test
    public void testMorrisTraversal() {
        List<BinarySearchTree<Integer, String>.Node> preOrderList = new ArrayList<>();
        Iterator<BinarySearchTree<Integer, String>.Node> iterator = tree.iterator();
        while (iterator.hasNext()) {
            preOrderList.add(iterator.next());
        }

        Assert.assertEquals(preOrderList, tree.morrisTraversal());
    }

    private Task5 createTree() {
        List<Integer> keys = Arrays.asList(10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 10);
        List<String> values = Arrays.asList("Italy", "Germany", "France", "Spain", "Portugal",
                "Russia", "Belgium", "Denmark", "Sweden", "Norway", "Finland");
        Collections.shuffle(keys);
        Collections.shuffle(values);

        Task5 tree = new Task5();
        for (int i = 0; i < keys.size(); i++) {
            tree.put(keys.get(i), values.get(i));
        }
        return tree;
    }
}
