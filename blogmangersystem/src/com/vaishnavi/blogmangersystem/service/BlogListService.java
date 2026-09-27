package com.vaishnavi.blogmangersystem.service;

import java.util.ArrayList;
import java.util.List;

import com.vaishnavi.blogmangersystem.model.Blog;
import com.vaishnavi.blogmangersystem.model.CustomList;

public class BlogListService {
    private BlogService blogService;
    private ListService listService;

    public BlogListService(BlogService blogService, ListService listService) {
        this.blogService = blogService;
        this.listService = listService;
    }

    /*
     * Because blogIds is a Set<Integer>, duplicate IDs are naturally prevented too.
     * The explicit check gives the user a clear message.
     * 
     * Why pass both IDs?
     * We don't pass complete objects
     * This matches project requirement where the relationship is maintained
     * using Blog IDs.
     */

    public void addBlogToList(int blogId, int listId) {
        // add blog ID to selected list
        Blog blog = blogService.getBlogById(blogId);

        if (blog == null) {
            System.out.println("Blog not found.");
            return;
        }
        CustomList customList = listService.getListById(listId);

        if (customList == null) {
            System.out.println("List not found.");
            return;
        }
        if (customList.getBlogIds().contains(blogId)) {
            System.out.println("Blog is already in this list.");
            return;
        }
        customList.addBlogId(blogId);
        System.out.println("Blog added to list successfully.");
    }

    public void removeBlogFromList(int blogId, int listId) {
        // remove blog ID from selected list
        CustomList list = listService.getListById(listId);

        if (list == null) {
            System.out.println("List not found.");
            return;
        }

        if (!list.getBlogIds().contains(blogId)) {
            System.out.println("Blog is not present in this list.");
            return;
        }

        list.removeBlogId(blogId);

        System.out.println("Blog removed from list successfully.");
    }

    public List<Blog> getBlogsFromList(int listId) {
        CustomList customList = listService.getListById(listId);
        if (customList == null) {
            System.out.println("List not found.");
            return new ArrayList<>();
        }

        List<Blog> result = new ArrayList<>();
        for (Integer blogId : customList.getBlogIds()) {
            Blog blog = blogService.getBlogById(blogId);
            if (blog != null) {
                result.add(blog);
            }
        }
        return result;
    }

    public void removeBlogFromAllLists(int blogId) {
        // remove blog ID from every list
        for (CustomList customList : listService.getAllLists()) {
            customList.removeBlogId(blogId);
        }
    }
}
