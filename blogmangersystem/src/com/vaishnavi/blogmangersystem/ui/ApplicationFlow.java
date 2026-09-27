package com.vaishnavi.blogmangersystem.ui;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.vaishnavi.blogmangersystem.model.Blog;
import com.vaishnavi.blogmangersystem.model.CustomList;
import com.vaishnavi.blogmangersystem.repository.BlogRepository;
import com.vaishnavi.blogmangersystem.repository.ListRepository;
import com.vaishnavi.blogmangersystem.service.BlogListService;
import com.vaishnavi.blogmangersystem.service.BlogService;
import com.vaishnavi.blogmangersystem.service.ListService;

public class ApplicationFlow {
    private Scanner sc;
    private BlogService blogService;
    private ListService listService;
    private BlogListService blogListService;
    private MainMenu mainMenu;

    public ApplicationFlow() {

        sc = new Scanner(System.in);

        BlogRepository blogRepository = new BlogRepository("src/data/blogs.dat");

        ListRepository listRepository = new ListRepository("src/data/lists.dat");

        blogService = new BlogService(blogRepository);

        listService = new ListService(listRepository);

        blogListService = new BlogListService(blogService, listService);

        mainMenu = new MainMenu(sc);
    }

    public void start() {

        loadData();

        int choice;

        do {
            choice = mainMenu.showMenu();

            switch (choice) {

                case 1:
                    blogManagement();
                    break;

                case 2:
                    listManagement();
                    break;

                case 3:
                    blogListOperations();
                    break;

                case 4:
                    saveData();
                    System.out.println("Application closed.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 4);

        sc.close();
    }

    private void loadData() {
        // load blogs
        // load lists
        try {
            blogService.loadBlogs();
            listService.loadLists();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error while loading data.");
            e.printStackTrace();
        }
    }

    private void saveData() {
        // save blogs
        // save lists
        try {
            blogService.saveBlogs();
            listService.saveLists();

        } catch (IOException e) {
            System.out.println("Error while saving data.");
            e.printStackTrace();
        }

    }

    private void blogManagement() {
        // BlogMenu loop
        BlogMenu blogMenu = new BlogMenu(sc);

        int choice;

        do {
            choice = blogMenu.showMenu();

            switch (choice) {

                case 1:
                    addBlog();
                    break;

                case 2:
                    displayAllBlogs();
                    break;

                case 3:
                    viewBlogDetails();
                    break;

                case 4:
                    updateBlog();
                    break;

                case 5:
                    deleteBlog();
                    break;

                case 6:
                    searchBlog();
                    break;

                case 7:
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 7);

    }

    private void listManagement() {
        // ListMenu loop
        ListMenu listMenu = new ListMenu(sc);

        int choice;

        do {
            choice = listMenu.showMenu();

            switch (choice) {

                case 1:
                    createList();
                    break;

                case 2:
                    displayLists();
                    break;

                case 3:
                    deleteList();
                    break;

                case 4:
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 4);
    }

    private void blogListOperations() {
        // BlogListMenu loop
        BlogListMenu blogListMenu = new BlogListMenu(sc);

        int choice;

        do {
            choice = blogListMenu.showMenu();

            switch (choice) {

                case 1:
                    addBlogToList();
                    break;

                case 2:
                    removeBlogFromList();
                    break;

                case 3:
                    viewBlogsInList();
                    break;

                case 4:
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 4);

    }

    private void addBlog() {

        System.out.print("Enter Blog ID: ");
        int blogId = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Blog Title: ");
        String title = sc.nextLine();

        System.out.print("Enter Blog Content: ");
        String content = sc.nextLine();

        LocalDate date = LocalDate.now();

        Blog blog = new Blog(blogId, title, date, content);

        blogService.addBlog(blog);
    }

    private void displayAllBlogs() {

        List<Blog> blogs = blogService.getAllBlogs();

        if (blogs.isEmpty()) {
            System.out.println("No blogs available.");
            return;
        }

        for (Blog blog : blogs) {
            System.out.println(blog);
        }
    }

    private void viewBlogDetails() {

        System.out.print("Enter Blog ID: ");
        int blogId = sc.nextInt();

        Blog blog = blogService.getBlogById(blogId);

        if (blog == null) {
            System.out.println("Blog not found.");
            return;
        }

        System.out.println("\n===== BLOG DETAILS =====");
        System.out.println("Blog ID : " + blog.getBlogId());
        System.out.println("Title   : " + blog.getTitle());
        System.out.println("Date    : " + blog.getDate());
        System.out.println("Content : " + blog.getContent());
    }

    private void updateBlog() {

        System.out.print("Enter Blog ID to update: ");
        int blogId = sc.nextInt();
        sc.nextLine();

        Blog existingBlog = blogService.getBlogById(blogId);

        if (existingBlog == null) {
            System.out.println("Blog not found.");
            return;
        }

        System.out.print("Enter new Title: ");
        String title = sc.nextLine();

        System.out.print("Enter new Content: ");
        String content = sc.nextLine();

        LocalDate date = LocalDate.now();

        Blog updatedBlog = new Blog(blogId, title, date, content);

        blogService.updateBlog(updatedBlog);
    }

    private void deleteBlog() {

        System.out.print("Enter Blog ID to delete: ");
        int blogId = sc.nextInt();

        Blog blog = blogService.getBlogById(blogId);

        if (blog == null) {
            System.out.println("Blog not found.");
            return;
        }

        blogService.deleteBlog(blogId);
        blogListService.removeBlogFromAllLists(blogId);

        System.out.println("Blog deleted successfully.");
    }

    private void searchBlog() {

        System.out.println("\n===== SEARCH BLOG =====");
        System.out.println("1. Search by Blog ID");
        System.out.println("2. Search by Title");

        System.out.print("Enter choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {

            case 1:
                System.out.print("Enter Blog ID: ");
                int blogId = sc.nextInt();

                Blog blog = blogService.getBlogById(blogId);

                if (blog == null) {
                    System.out.println("Blog not found.");
                } else {
                    System.out.println(blog);
                }
                break;

            case 2:
                System.out.print("Enter Title: ");
                String title = sc.nextLine();

                List<Blog> result = blogService.searchByTitle(title);

                if (result.isEmpty()) {
                    System.out.println("No blogs found.");
                } else {
                    for (Blog b : result) {
                        System.out.println(b);
                    }
                }
                break;

            default:
                System.out.println("Invalid choice.");
        }
    }

    private void createList() {

        System.out.print("Enter List ID: ");
        int listId = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter List Name: ");
        String listName = sc.nextLine();

        CustomList list = new CustomList(listId, listName);

        listService.createList(list);
    }

    private void displayLists() {

        List<CustomList> lists = listService.getAllLists();

        if (lists.isEmpty()) {
            System.out.println("No custom lists available.");
            return;
        }

        for (CustomList list : lists) {
            System.out.println(list);
        }
    }

    private void deleteList() {

        System.out.print("Enter List ID to delete: ");
        int listId = sc.nextInt();

        CustomList list = listService.getListById(listId);

        if (list == null) {
            System.out.println("List not found.");
            return;
        }

        listService.deleteList(listId);

        System.out.println("List deleted successfully.");
    }

    private void addBlogToList() {

        System.out.print("Enter Blog ID: ");
        int blogId = sc.nextInt();

        System.out.print("Enter List ID: ");
        int listId = sc.nextInt();

        blogListService.addBlogToList(blogId, listId);
    }

    private void removeBlogFromList() {

        System.out.print("Enter Blog ID: ");
        int blogId = sc.nextInt();

        System.out.print("Enter List ID: ");
        int listId = sc.nextInt();

        blogListService.removeBlogFromList(blogId, listId);
    }

    private void viewBlogsInList() {

        System.out.print("Enter List ID: ");
        int listId = sc.nextInt();

        List<Blog> blogs = blogListService.getBlogsFromList(listId);

        if (blogs.isEmpty()) {
            System.out.println("No blogs found in this list.");
            return;
        }

        System.out.println("\n===== BLOGS IN LIST =====");

        for (Blog blog : blogs) {
            System.out.println(blog);
        }
    }

}
