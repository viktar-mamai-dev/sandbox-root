package book2.ch9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.UserPrincipal;

import static java.lang.System.out;

public class FileAttributesMain {
    private static final String FILE_111 = "src\\main\\resources\\file111.txt";
    private static final String FILE_222 = "src\\main\\resources\\file222.txt";

    private static final String FILE_333 = "src\\main\\resources\\file333.txt";
    private static final String FILE_444 = "src\\main\\resources\\file444.txt";

    public static void main(String[] args) {
        Path path1 = Paths.get(FILE_111);
        Path path2 = Paths.get(FILE_222);
        Path path3 = Paths.get(FILE_333);
        Path path4 = Paths.get(FILE_444);

        // isDirectory, isRegularFile, isSymbolicLink
        out.println("Is directory : " + Files.isDirectory(path1));
        out.println("Is file : " + Files.isRegularFile(path1));
        out.println("Is symbolic : " + Files.isSymbolicLink(path1));

        try {
            out.println("Is hidden : " + Files.isHidden(path1)); // may throw exception
        } catch (IOException e) {
            e.printStackTrace();
        }

        out.println(Files.isReadable(path1));
        out.println(Files.isExecutable(path1));
        try {
            out.println("Size : " + Files.size(path1)); // throws Exception
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            out.println(Files.getLastModifiedTime(path1));
            Files.setLastModifiedTime(path1, FileTime.fromMillis(System.currentTimeMillis()));
            out.println(Files.getLastModifiedTime(path1));

            out.println(Files.getOwner(path1).getName());
            UserPrincipal owner = path1.getFileSystem().getUserPrincipalLookupService().lookupPrincipalByName("Viktar");
            Files.setOwner(path1, owner);
            out.println(Files.getOwner(path1).getName());
        } catch (IOException e) {
            out.println(e.getMessage());
        }

        try {
            BasicFileAttributes attr1 = Files.readAttributes(path1, BasicFileAttributes.class);

            BasicFileAttributeView view1 = Files.getFileAttributeView(path1, BasicFileAttributeView.class);
            BasicFileAttributes attr2 = view1.readAttributes();
            //attr2.
        } catch (IOException e) {
            e.printStackTrace();
        }


        // streams: walk(), find(), list() methods
        Path path5 = Paths.get("src\\main\\resources");
        try {
            // does not walk through symbolic links, FOLLOW_LINK - to enable
            out.println("Walking...");
            Files.walk(path5).forEach(out::println);

            out.println("Another way to traverse with depth=2");
            Files.find(path5, 2, (p, a) -> a.isRegularFile()).forEach(out::println);

            out.println("Listing all directories");
            Files.list(path5).filter(Files::isDirectory).
                    map(Path::toAbsolutePath).forEach(out::println); // get all directories

            Files.lines(path1).forEach(out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
