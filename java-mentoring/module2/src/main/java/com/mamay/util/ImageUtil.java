/*
 * Copyright (c) 2023
 */
package com.mamay.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Base64;
import java.util.Scanner;

public class ImageUtil {

  public static byte[] retrieveImageFromFile() {
    try (Scanner scanner = new Scanner(new File("images.txt"))) {
      return Base64.getDecoder().decode(scanner.nextLine());
    } catch (FileNotFoundException e) {
      return new byte[0];
    }
  }
}
