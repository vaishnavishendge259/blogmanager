package com.vaishnavi.blogmangersystem.repository;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.vaishnavi.blogmangersystem.model.Blog;

public class BlogRepository {

    private String fileName;

    public BlogRepository(String fileName) {
        this.fileName = fileName;
    }

    public void saveBlogs(List<Blog> blogs) throws IOException {
        FileDataStore.saveObject(blogs, fileName);
    }

    @SuppressWarnings("unchecked")
    public List<Blog> loadBlogs() throws IOException, ClassNotFoundException {
        try {
            return (List<Blog>) FileDataStore.loadObject(fileName);
        } catch (FileNotFoundException | EOFException e) { // EOFException is for file is empty and contained 0 bytes
            return new ArrayList<>();
        }
    }

}
