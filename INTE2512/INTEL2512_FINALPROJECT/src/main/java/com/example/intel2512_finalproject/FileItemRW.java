package com.example.intel2512_finalproject;

import java.io.*;
import java.util.ArrayList;

public class FileItemRW extends FileReadWrite {
    ArrayList<Item> itemlist;

    public FileItemRW(String filename) {
        super(filename);
    }

    void readItemList() {

        // Read the content from file
        try {
            // Creating an object input stream
            InputStream newInputStream = new FileInputStream(filename);
            ObjectInputStream objStream = new ObjectInputStream(newInputStream);

            ArrayList<Item> currItemArray = new ArrayList<>();

            boolean cond = true;
            // Using the readObject() method
            while (cond){
                try {
                    Item localItem = (Item) objStream.readObject();

                    if (localItem == null) {
                        cond = false ;
                    } else {
                        currItemArray.add(localItem);
                        System.out.println("Item " + localItem.getTitle() + " imported from db");
                    }
                } catch (EOFException e) {
                    break;
                }
            }

            this.setItemList(currItemArray);

//            for (Item c : itemlist){
//                System.out.println(c);
//                System.out.println();
//            }
                objStream.close();

        } catch (IOException e) {
            // Exception handling
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        if (this.getItemList() == null) {
            this.setItemList((new ArrayList<>()));
        }
    }

    void addItem(ArrayList<Item> itemList) {

        // update the local item database of the FileItemRW object
        this.setItemList(itemList);

        try {
            OutputStream newOutputStream = new FileOutputStream(filename);
            ObjectOutputStream objOutStream = new ObjectOutputStream(newOutputStream);
            for (Item itx: itemList) {
                System.out.println("Adding item: ");
                System.out.println(itx);
                objOutStream.writeObject(itx);
            }
            objOutStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    ArrayList<Item> getItemList() {
        return this.itemlist;
    }

    void setItemList(ArrayList<Item> itemList) {
        this.itemlist = itemList;
    }

}