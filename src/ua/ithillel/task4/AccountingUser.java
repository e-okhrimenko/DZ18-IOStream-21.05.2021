package ua.ithillel.task4;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AccountingUser {
    private RandomAccessFile file;

    private final String path;

    public AccountingUser(String path) throws IOException {
        this.path = path;
        if (!(Files.exists(Paths.get(this.path)))) {
            System.out.println("Created 'users.txt' file...");
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("./users.txt"), StandardCharsets.UTF_8))) {
                writer.write("Sidorov:2\n" + "Govga:1\n" + "Danilina:3\n");
                System.out.println("File 'users.txt', created!");
            }
        }
    }

    void addUsers(String nameUser) throws IOException {
        file = new RandomAccessFile(path, "rw");
        boolean add = true;
        String line;
        while ((line = file.readLine()) != null) {
            String[] nameAndNum = line.split(":");
            if (nameAndNum[0].equals(nameUser)) {
                long posS = file.getFilePointer() - line.length();
                String numberStr = nameAndNum[1];
                String newNumberStr = String.valueOf(Integer.parseInt(numberStr) + 1);
                String newString = line.replace(numberStr, newNumberStr);
                if (numberStr.length() < newNumberStr.length()) {
                    StringBuilder nextNames = readForExpand(line, posS);
                    file.seek(posS - 1);
                    file.writeBytes((newString));
                    file.writeBytes(String.valueOf(nextNames));
                } else {
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

    @NotNull
    private StringBuilder readForExpand(String line, long posS) throws IOException {
        file.seek(posS - 1 + line.length());
        StringBuilder res = new StringBuilder();
        int b = file.read();
        while (b != -1) {
            res.append((char) b);
            b = file.read();
        }
        return res;
    }

    void printFile() throws IOException {
        System.out.print("\n");
        file = new RandomAccessFile(path, "r");
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