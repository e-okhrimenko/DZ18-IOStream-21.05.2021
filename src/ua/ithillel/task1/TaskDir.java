package ua.ithillel.task1;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TaskDir {
    public static void main(String[] args) {
        Path path;
        if (args.length == 0) {
            path = Paths.get(".");
        } else {
            path = Paths.get(args[0]);
        }

        String[] files = new File(String.valueOf(path)).list();

        if (files != null) {
            System.out.println("Path " + path.toAbsolutePath() + " :");
            if (files.length != 0) {
                for (String file : files) {
                    System.out.println("    " + file);
                }
            } else {
                System.out.println("Path without files.");
            }
        } else {
            System.out.println("Path not found.");
        }
    }
}
