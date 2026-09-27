package com.vaishnavi.blogmangersystem.ui;

import java.util.Scanner;

public class BlogListMenu {
    private Scanner sc;

    public BlogListMenu(Scanner sc) {
        this.sc = sc;
    }

    public int showMenu() {
        System.out.println("\n===== BLOG LIST OPERATIONS =====");
        System.out.println("1. Add Blog to List");
        System.out.println("2. Remove Blog from List");
        System.out.println("3. View Blogs in List");
        System.out.println("4. Back");

        System.out.print("Enter your choice: ");
        return sc.nextInt();
    }

}
