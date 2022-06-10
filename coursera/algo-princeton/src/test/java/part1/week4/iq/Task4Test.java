package part1.week4.iq;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Task4Test {

    @Test
    public void test1() {
        for (int i = 0; i < 10; i++) {
            Task4 main = createTree();
            Assert.assertTrue(main.checkBST());
            // most likely Values won't be in order
            Assert.assertFalse(main.checkBSTByValue());
        }
    }

    private Task4 createTree() {
        List<Integer> keys = Arrays.asList(10, 20, 30, 40, 50, 60, 10, 30, 60);
        List<String> values = Arrays.asList("Italy", "Germany", "France", "Spain", "Portugal", "Russia", "Belgium", "Denmark", "Sweden");
        Collections.shuffle(keys);
        Collections.shuffle(values);

        Task4 tree = new Task4();
        for (int i = 0; i < keys.size(); i++) {
            tree.put(keys.get(i), values.get(i));
        }
        return tree;
    }
}