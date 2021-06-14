package ua.ithillel.task4;

import java.io.IOException;
import java.util.Scanner;

public class TaskUser {
    public static void main(String[] args) throws IOException {
        AccountingUser accountingUser = new AccountingUser("./users.txt");
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nHello.");
        accountingUser.printFile();
        boolean repeat = true;
        while (repeat) {
            System.out.print("Enter name, for adding to base, or 'Exit': ");
            String name = scanner.nextLine().trim();
            if ("exit".equalsIgnoreCase(name)) {
                System.out.println("\nBye.");
                repeat = false;
            } else {
                if (!name.equals("")) {
                    accountingUser.addUsers(name);
                    accountingUser.printFile();
                    continue;
                }
                System.out.println("Command or name not entered.");
            }

        }
    }
}