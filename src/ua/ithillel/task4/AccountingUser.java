package ua.ithillel.task4;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AccountingUser {
    private RandomAccessFile file;

    private final String PATH;

    public AccountingUser(String path) throws IOException {
        this.PATH = path;
        if (!(Files.exists(Paths.get(PATH)))) {
            System.out.println("Created 'users.txt' file...");
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("./users.txt"), StandardCharsets.UTF_8))) {
                writer.write("Sidorov:2\n" + "Govga:1\n" + "Danilina:3\n");
                System.out.println("File 'users.txt', created!");
            }
        }
    }

    void addUsers(String nameUser) throws IOException {
        file = new RandomAccessFile(PATH, "rw");
        boolean add = true;
        String line;
        while ((line = file.readLine()) != null) {
            if (line.contains(nameUser)) {
                long posS = file.getFilePointer() - line.length();
                String numberStr;
                numberStr = line.substring(line.indexOf(":") + 1);
                int numberInt = Integer.parseInt(numberStr);
                if ((String.valueOf(Math.abs(numberInt)).length()) < (String.valueOf(Math.abs(++numberInt)).length())) {
                    file.seek(posS - 1);
                    String newString = line.replace(numberStr, String.valueOf(numberInt));
                    file.seek(posS - 1 + line.length());
                    StringBuilder res = new StringBuilder();
                    int b = file.read();
                    while (b != -1) {
                        res.append((char) b);
                        b = file.read();
                    }
                    file.seek(posS - 1);
                    file.writeBytes((newString));
                    file.writeBytes(String.valueOf(res));
                } else {
                    String newString = line.replace(numberStr, String.valueOf(numberInt));
                    file.seek(posS - 1);
                    file.writeBytes((newString + "\n"));
                }
                add = false;
            }
        }
        if (add) {
            file.writeBytes((nameUser + ":1" + "\n"));
        }
        file.close();
    }

    void printFile() throws IOException {
        System.out.print("\n");
        file = new RandomAccessFile(PATH, "r");
        String line;
        while ((line = file.readLine()) != null) {
            System.out.println(line);
        }
        file.close();
    }

    @Override
    protected void finalize() throws IOException {
        file.close();
    }
}