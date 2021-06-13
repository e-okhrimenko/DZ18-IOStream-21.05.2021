package ua.ithillel.task4;

import java.io.IOException;
import java.util.Scanner;

public class TaskUser {
    public static void main(String[] args) throws IOException {
        AccountingUser accountingUser = new AccountingUser("./users.txt");
        System.out.println("\nHello.");
        accountingUser.printFile();
        String name;
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("\nEnter name: ");
            name = scanner.nextLine().trim();
        }
        if (!name.equals("")) {
            accountingUser.addUsers(name);
            accountingUser.printFile();
        }
        System.out.println("\nBye.");
    }
}
