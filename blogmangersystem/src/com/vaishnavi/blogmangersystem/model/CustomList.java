package com.vaishnavi.blogmangersystem.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class CustomList implements Serializable {

    private static final long serialVersionUID = 1L;
    private int listId;
    private String listName;
    private Set<Integer> blogIds;

    // Default constructor
    public CustomList() {
        blogIds = new HashSet<>();
    }

    // Constructor for creating a list
    public CustomList(int listId, String listName) {
        this.listId = listId;
        this.listName = listName;
        this.blogIds = new HashSet<>();
    }

    // Constructor useful for searching by ID
    public CustomList(int listId) {
        this.listId = listId;
        this.blogIds = new HashSet<>();
    }

    // Constructor useful for searching by name
    public CustomList(String listName) {
        this.listName = listName;
        this.blogIds = new HashSet<>();
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public Set<Integer> getBlogIds() {
        return blogIds;
    }

    public void setBlogIds(Set<Integer> blogIds) {
        this.blogIds = blogIds;
    }

    // Add Blog ID to this list
    public boolean addBlogId(int blogId) {
        return blogIds.add(blogId);
    }

    // Remove Blog ID from this list
    public boolean removeBlogId(int blogId) {
        return blogIds.remove(blogId);
    }

    @Override
    public String toString() {
        return "CustomList [listId=" + listId + ", listName=" + listName + ", blogIds=" + blogIds + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + listId;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CustomList other = (CustomList) obj;
        if (listId != other.listId)
            return false;
        return true;
    }

}
