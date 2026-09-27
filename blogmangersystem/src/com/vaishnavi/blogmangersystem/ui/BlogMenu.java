package com.vaishnavi.blogmangersystem.ui;

import java.util.Scanner;

public class BlogMenu {
    private Scanner sc;

    public BlogMenu(Scanner sc) {
        this.sc = sc;
    }

    public int showMenu() {
        System.out.println("\n===== BLOG MANAGEMENT =====");
        System.out.println("1. Add Blog");
        System.out.println("2. Display All Blogs");
        System.out.println("3. View Blog Details");
        System.out.println("4. Edit Blog");
        System.out.println("5. Delete Blog");
        System.out.println("6. Search Blog");
        System.out.println("7. Back");

        System.out.print("Enter your choice: ");

        return sc.nextInt();
    }
}
