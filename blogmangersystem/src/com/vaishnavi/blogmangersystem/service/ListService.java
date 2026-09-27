package com.vaishnavi.blogmangersystem.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.vaishnavi.blogmangersystem.model.CustomList;
import com.vaishnavi.blogmangersystem.repository.ListRepository;

public class ListService {
    private List<CustomList> lists;
    private ListRepository listRepository;

    public ListService(ListRepository listRepository) {
        this.listRepository = listRepository;
        this.lists = new ArrayList<>();
    }

    public void loadLists() throws IOException, ClassNotFoundException {
        lists = listRepository.loadLists();
    }

    public void saveLists() throws IOException {
        listRepository.saveList(lists);
    }

    public void createList(CustomList list) {
        // duplicate list name validation
        if (getListByName(list.getListName()) != null) {
            System.out.println("List name already exists.");
            return;
        }
        lists.add(list);
        System.out.println("List created successfully.");
    }

    public List<CustomList> getAllLists() {
        return lists;
    }

    public CustomList getListById(int listId) {
        for (CustomList customList : lists) {
            if (customList.getListId() == listId) {
                return customList;
            }
        }
        return null;
    }

    public CustomList getListByName(String listName) {
        for (CustomList customList : lists) {
            if (customList.getListName().equalsIgnoreCase(listName)) {
                return customList;
            }
        }
        return null;
    }

    /*
     * When we delete a list
     * CustomList → deleted
     * Blogs → NOT deleted
     */
    public void deleteList(int listId) {
        // delete list
        CustomList customList = getListById(listId);
        if (customList == null) {
            System.out.println("List not found.");
            return;
        }
        lists.remove(customList);
        System.out.println("List deleted successfully.");
    }
}
