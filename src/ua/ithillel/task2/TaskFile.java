package ua.ithillel.task2;

import org.jetbrains.annotations.NotNull;

import java.io.*;

public class TaskFile {
    public static void main(String @NotNull [] args) {
        try {
            if (args.length == 0) {
                throw new IndexOutOfBoundsException();
            }
            // VARIANT_1
            try (FileInputStream is = new FileInputStream(args[0])) {
                int symbol;
                while ((symbol = is.read()) != -1) {
                    System.out.print((char) symbol);
                }
            }
            // VARIANT_2
//          try (BufferedReader is = new BufferedReader(new FileReader(args[0]))) {
//                String line;
//                while ((line = is.readLine()) != null) {
//                    System.out.println(line);
//                }
//            }
        } catch (IOException | IndexOutOfBoundsException e) {
            System.out.println("File not found. Path not set.");
        }
    }
}
