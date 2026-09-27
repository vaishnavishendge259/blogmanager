package com.vaishnavi.blogmangersystem.repository;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.vaishnavi.blogmangersystem.model.CustomList;

public class ListRepository {
    private String fileName;

    public ListRepository(String fileName) {
        this.fileName = fileName;
    }

    public void saveList(List<CustomList> lists) throws IOException {
        FileDataStore.saveObject(lists, fileName);
    }

    @SuppressWarnings("unchecked")
    public List<CustomList> loadLists() throws IOException, ClassNotFoundException {
        try {
            return (List<CustomList>) FileDataStore.loadObject(fileName);
        } catch (FileNotFoundException | EOFException e) {
            return new ArrayList<>();
        }
    }
}
