package com.vaishnavi.blogmangersystem.repository;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileDataStore {
    public static void saveObject(Object obj, String fileName) throws IOException {
        // serialization logic
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));

        oos.writeObject(obj);
        oos.close();
    }

    public static Object loadObject(String fileName) throws IOException, ClassNotFoundException {
        // deserialization logic
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));

        Object obj = ois.readObject();
        ois.close();

        return obj;
    }

}
