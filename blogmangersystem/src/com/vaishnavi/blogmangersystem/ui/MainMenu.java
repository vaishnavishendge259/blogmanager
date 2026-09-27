package com.vaishnavi.blogmangersystem.ui;

import java.util.Scanner;

public class MainMenu {
    private Scanner sc;

    public MainMenu(Scanner sc) {
        this.sc = sc;
    }

    public int showMenu() {
        System.out.println("\n===== TECHNICAL BLOG MANAGER =====");
        System.out.println("1. Blog Management");
        System.out.println("2. Custom List Management");
        System.out.println("3. Blog List Operations");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
        return sc.nextInt();
    }
}
