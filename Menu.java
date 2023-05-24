/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Tan Hung
 */
class Menu {

    private Map<Integer, String> options;
    private Map<Integer, Runnable> actions;

    public Menu() {
        options = new HashMap<>();
        actions = new HashMap<>();
    }

    public void addOption(int choice, String description, Runnable action) {
        options.put(choice, description);
        actions.put(choice, action);
    }

    public void displayMenu() {
        System.out.println("\nMenu:");
        for (Map.Entry<Integer, String> entry : options.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue());
        }
    }

    public void handleUserInput() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            displayMenu();
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(scanner.nextLine());

            if (options.containsKey(choice)) {
                actions.get(choice).run();
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != options.size());
    }
}
