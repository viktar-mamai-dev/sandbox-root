package com.mamay.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Base64;
import java.util.Scanner;

public class ImageUtil {

    private static final int IMAGE_NUMBER = 1;

    public static byte[] retrieveImageFromFile() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("images.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int i = 1;
        while (i < IMAGE_NUMBER) {
            scanner.nextLine();
        }
        String str = scanner.nextLine();

        byte[] data = Base64.getDecoder().decode(str);
        return data;
    }
}
