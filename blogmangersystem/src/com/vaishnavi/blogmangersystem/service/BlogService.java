package com.vaishnavi.blogmangersystem.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.vaishnavi.blogmangersystem.model.Blog;
import com.vaishnavi.blogmangersystem.repository.BlogRepository;

public class BlogService {
    private List<Blog> blogs;
    private BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
        this.blogs = new ArrayList<>();
    }

    public void loadBlogs() throws IOException, ClassNotFoundException {
        blogs = blogRepository.loadBlogs();
    }

    public void saveBlogs() throws IOException {
        blogRepository.saveBlogs(blogs);
    }

    // no scanner use in this layer we will add this in ui
    public void addBlog(Blog blog) {
        // duplicate ID validation
        /*
         * Check whether the blogId already exists.
         * If it exists → don't add the blog.
         * If it doesn't exist → add it to the blogs list.
         */
        if (getBlogById(blog.getBlogId()) != null) {
            System.out.println("Blog ID already exists.");
            return;
        }
        blogs.add(blog);
        System.out.println("Blog added successfully.");

    }

    public List<Blog> getAllBlogs() {
        return blogs;
    }

    public Blog getBlogById(int blogId) {
        for (Blog blog : blogs) {
            if (blog.getBlogId() == blogId) {
                return blog;
            }
        }
        return null;
    }

    /*
     * Why don't we replace the whole object?
     * The Blog ID remains unchanged, because it identifies the blog.
     */
    public void updateBlog(Blog updatedBlog) {
        // update logic
        Blog existingBlog = getBlogById(updatedBlog.getBlogId());
        if (existingBlog == null) {
            System.out.println("Blog not found.");
            return;
        }
        existingBlog.setTitle(updatedBlog.getTitle());
        existingBlog.setDate(updatedBlog.getDate());
        existingBlog.setContent(updatedBlog.getContent());

        System.out.println("Blog updated successfully.");

    }

    /*
     * this only deletes the blog from
     * 
     * project requirement says that when a blog is deleted, its Blog ID
     * must also be removed from every custom list.
     * not put that logic inside BlogService, because custom-list
     * relationships will be handled by BlogListService.
     */
    public void deleteBlog(int blogId) {
        // delete logic
        Blog blog = getBlogById(blogId);
        if (blog == null) {
            System.out.println("Blog not found.");
            return;
        }
        blogs.remove(blog);
        System.out.println("Blog deleted successfully.");
    }

    /*
     * search blogs by title,returns a List<Blog> because multiple blogs
     * could have matching titles.
     */
    public List<Blog> searchByTitle(String title) {
        List<Blog> result = new ArrayList<>();
        for (Blog blog : blogs) {
            if (blog.getTitle().equalsIgnoreCase(title)) {
                result.add(blog);
            }
        }
        return result;
    }

}
