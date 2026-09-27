package com.vaishnavi.blogmangersystem.ui;

import java.util.Scanner;

public class ListMenu {

    private Scanner sc;

    public ListMenu(Scanner sc) {
        this.sc = sc;
    }

    public int showMenu() {
        System.out.println("\n===== CUSTOM LIST MANAGEMENT =====");
        System.out.println("1. Create List");
        System.out.println("2. Display Lists");
        System.out.println("3. Delete List");
        System.out.println("4. Back");

        System.out.print("Enter your choice: ");
        return sc.nextInt();
    }
}
