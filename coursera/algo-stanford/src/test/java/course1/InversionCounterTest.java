package course1;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InversionCounterTest {

    @Test
    public void test1() {
        InversionCounter main = new InversionCounter();
        Assert.assertEquals(4, main.inversionCount(new int[] { 20, 12, 18, 14 }));
        Assert.assertEquals(10, main.inversionCount(new int[] { 12, 16, 20, 18, 25, 22, 14, 19 }));
        Assert.assertEquals(19, main.inversionCount(new int[] { 22, 32, 15, 28, 12, 24, 10, 20 }));

        try(Scanner in = new Scanner(new File("src\\main\\resources\\inversionsArray.txt"))) {
            int[] arr = new int[100000];
            int i = 0;
            while (in.hasNextLine()) {
                arr[i] = Integer.parseInt(in.nextLine().trim());
                i++;
            }

            System.out.println(main.inversionCount(arr));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {

        }

    }
}