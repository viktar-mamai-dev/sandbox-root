package part1.week4.iq;

import org.junit.Assert;
import org.junit.Test;
import part1.week4.BinarySearchTree;

public class Task6Test {

    @Test
    public void test1() {
        BinarySearchTree<Task6.Visit, Integer> tree = new BinarySearchTree<Task6.Visit, Integer>();
        Task6.User user1 = new Task6.User("John");
        Task6.User user2 = new Task6.User("Sam");
        Task6.User user3 = new Task6.User("Ronnie");
        Assert.assertEquals("Ronnie", user3.getName());
        Task6.Website site1 = new Task6.Website("google.com");
        Task6.Website site2 = new Task6.Website("bbc.com");
        Task6.Website site3 = new Task6.Website("tut.by");
        Assert.assertEquals("tut.by", site3.getUrl());
        tree.put(new Task6.Visit(user1, site1), 4);
        tree.put(new Task6.Visit(user1, site2), 2);
        tree.put(new Task6.Visit(user1, site3), 10);
        tree.put(new Task6.Visit(user2, site1), 12);
        tree.put(new Task6.Visit(user2, site2), 3);
        tree.put(new Task6.Visit(user3, site1), 7);

        Assert.assertEquals(6, tree.size());
        Assert.assertEquals((Integer) 2, tree.get(new Task6.Visit(user1, site2)));
        tree.delete(new Task6.Visit(user2, site2));
        Assert.assertNull(tree.get(new Task6.Visit(user2, site2)));
        Assert.assertEquals(5, tree.size());
    }
}