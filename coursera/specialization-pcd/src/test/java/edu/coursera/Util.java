package edu.coursera;

public class Util {

    public static int getNCores() {
        String ncoresStr = System.getenv("COURSERA_GRADER_NCORES");
        if (ncoresStr == null) {
            ncoresStr = System.getProperty("COURSERA_GRADER_NCORES");
        }

        if (ncoresStr == null) {
            return Runtime.getRuntime().availableProcessors();
        } else {
            return Integer.parseInt(ncoresStr);
        }
    }
}
