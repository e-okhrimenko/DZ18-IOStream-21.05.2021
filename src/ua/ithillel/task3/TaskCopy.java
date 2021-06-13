package ua.ithillel.task3;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.Scanner;

public class TaskCopy {
    public static void main(String[] args) {
        System.out.println("\nCopy files!");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter source filename:");
        String oldFile = scanURL(scanner);
        while (oldFile.length() < 1) {
            System.out.print("Don't entered. Please reenter source filename:");
            oldFile = scanURL(scanner);
        }
        System.out.print("Please enter new filename:");
        String newFile = scanner.nextLine().replace('\\', '/');
        while (newFile.length() < 1) {
            System.out.print("Don't entered. Please reenter new filename:");
            newFile = scanURL(scanner);
        }
        copyFiles(oldFile, newFile);
        System.out.println("\nReplace in file!");
        System.out.print("Please enter filename for replacement:");
        String repFile = scanURL(scanner);
        while (repFile.length() < 1) {
            System.out.print("Don't entered. Please reenter new filename:");
            repFile = scanURL(scanner);
        }
        replacementInFile(repFile);
    }

    @NotNull
    private static String scanURL(Scanner scanner) {
        return scanner.nextLine().trim().replace('\\', '/');
    }

    private static void replacementInFile(String file) {
        System.out.println("Replace 'public' on 'protected' in file '" + file);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line.replace("public", "protected")).append("\n");
            }
            reader.close();
            try (BufferedWriter writter = new BufferedWriter(new FileWriter(file))) {
                writter.write(String.valueOf(sb));
            }
        } catch (IOException e) {
            System.out.println("I/O ERROR");
        }
        System.out.println("Replacement complete.");
    }

    private static void copyFiles(String oldFile, String newFile) {
        System.out.println("Copy file '" + oldFile + "' to '" + newFile + "'");
        try (BufferedReader reader = new BufferedReader(new FileReader(oldFile))) {
            try (BufferedWriter writter = new BufferedWriter(new FileWriter(newFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    writter.write(line + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("I/O ERROR");
        }
        System.out.println("Copy complete.");
    }
}