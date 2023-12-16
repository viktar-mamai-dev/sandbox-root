/*
 * Copyright (c) 2023
 */
package book2.ch9;

import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathFileMain {
  private static final String FILE_1_ABSOLUTE =
      "D:\\workspace\\OracleCertifiedJava\\src\\main\\resources\\file111.txt";
  private static final String FILE_2_RELATIVE = "src\\main\\resources\\file222.txt";

  private static final String FILE_333 = "src\\main\\resources\\file333.txt";
  private static final String FILE__NOTEXIST = "src\\main\\resources\\file555.txt";
  private static final String FILE__NOTEXIST2 = "src\\main\\resources\\file666.txt";

  public static void main(String[] args) {
    // creating path from Paths
    Path path1 = Paths.get(FILE_1_ABSOLUTE);
    Path path12 = Paths.get(FILE_2_RELATIVE);

    Path path2 =
        Paths.get("D:\\workspace", "OracleCertifiedJava\\src", "main\\resources", "file222.txt");
    out.println("Absolute path : " + path1);
    out.println("Relative path : " + path12);
    out.println("Path from several pieces: " + path2 + "\n\n");

    URI uri1 = path2.toUri();
    out.println("URI: " + uri1);

    // path from URI
    Path path3 = Paths.get(uri1);
    out.println("Path from uri : " + path3);

    // path from file system
    Path path4 = FileSystems.getDefault().getPath(FILE_2_RELATIVE);
    out.println("Path from file system default : " + path4);

    try {
      // should work but it does not
      // FileSystem fileSystem = FileSystems.getFileSystem(URI.create("D:\\"));
    } catch (Exception e) {
      e.printStackTrace();
    }

    // path to file, file to path
    File file1 = path4.toFile();
    Path path5 = file1.toPath();
    out.println("File : " + file1 + "\n");

    // getNameCount, getName
    for (int i = 0; i < path5.getNameCount(); i++) {
      out.println("Element " + i + " is: " + path5.getName(i));
    }

    // fileName, getRoot(), getParent()
    out.println("\nFilename is : " + path5.getFileName());
    out.println("Root for : " + path5 + " is : " + path5.getRoot());
    Path parent = path5;
    while ((parent = parent.getParent()) != null) {
      out.println("Parent is : " + parent);
    }
    out.println();

    out.println("Root for : " + path1 + " is : " + path1.getRoot());
    parent = path1;
    while ((parent = parent.getParent()) != null) {
      out.println("Parent is : " + parent);
    }
    out.println();

    // isAbsolute(), toAbsolutePath(), subPath()
    out.println("Is absolute: " + path5 + " : " + path5.isAbsolute());
    out.println("Absolute : " + path5.toAbsolutePath());
    out.println("Subpath : " + path5.subpath(2, 4));

    final String folder = "src\\main\\resources\\bird";
    final String file333 = "file333.txt";

    Path path6 = Paths.get(folder + File.separator + file333);
    // relativize(), resolve(), normalize
    Path path7;
    out.println(
        "Relativize : "
            + path1
            + " and "
            + path2
            + " ::: "
            + (path7 =
                path1.relativize(path2))); // both should be on the same disk, otherwise - exception
    out.println(
        "Relativize : "
            + path12
            + " and "
            + path6
            + " ::: "
            + (path7 =
                path12.relativize(
                    path6))); // outputs path2 if path2 is absolute, otherwise - appends
    out.println(
        "Resolve : "
            + path12
            + " and "
            + path7
            + " ::: "
            + (path7 =
                path12.resolve(path7))); // outputs path2 if path2 is absolute, otherwise - appends
    out.println("Normalize : " + path7 + " ::: " + (path7 = path7.normalize())); // removes ../

    out.println("Exists : " + Files.exists(path7));
    out.println();

    Path path8 = Paths.get(FILE_2_RELATIVE);
    Path path9 = Paths.get(FILE__NOTEXIST);
    Path path13 = Paths.get(FILE__NOTEXIST2);
    try {
      // Files.delete(path9); NosuchFileException
      Files.deleteIfExists(path9); // OK
      Files.copy(
          path8,
          path9); // FileAlreadyExistsException if path9 exists, otherwise create and copy content

      out.println(Files.isSameFile(path8, path9)); // false, as it compares only paths
      Files.move(path9, path13); // FileAlreadyExistsException if path13 exists, otherwise move
      out.println(path9 + " exists: " + Files.exists(path9));
      Files.deleteIfExists(path9); // DirectoryNotEmptyException if path9 is not empty directory
      Files.delete(path13);

      Path directoryPath = Paths.get("src\\main\\resources\\bison");
      Files.createDirectories(directoryPath);
      out.println(directoryPath + " exists: " + Files.exists(directoryPath));

    } catch (IOException e) {
      e.printStackTrace();
    }

    path9 = Paths.get(FILE__NOTEXIST);
    try (BufferedReader reader = Files.newBufferedReader(path8, Charset.forName("US-ASCII"));
        BufferedWriter writer = Files.newBufferedWriter(path9, Charset.forName("UTF-16"))) {
      String line = null;
      while ((line = reader.readLine()) != null) {
        writer.write(line);
        writer.newLine();
      }
      for (String line2 : Files.readAllLines(path9)) {
        out.println(line2);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    try {
      for (String line2 : Files.readAllLines(path9, Charset.forName("windows-1251"))) {
        out.println(line2);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
