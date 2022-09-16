package com.example.intel2512_finalproject;

import java.io.*;
import java.util.ArrayList;

public class FileAccRW extends FileReadWrite {
    ArrayList<Account> accountList;

    public FileAccRW(String filename) {
        super(filename);
    }

    void readAccList() {

        // Read the content from file
        try {

            InputStream newInputStream = new FileInputStream(filename);

            ObjectInputStream objStream = new ObjectInputStream(newInputStream);

            ArrayList<Account> currAccArray = new ArrayList<>();

            boolean cond = true;
            // Using the readObject() method
            while (cond) {
                try {
                    Account localAcc = (Account) objStream.readObject();
                    if (localAcc == null) {
                        cond = false;
                    } else {
                        currAccArray.add(localAcc);
                        System.out.println("account retrieved is " + localAcc.getUsername());
                    }
                } catch (EOFException e) {
                    break;
                }
            }

            this.setAccountList(currAccArray);
            for (Account c : accountList) {
                System.out.println(c.toString());
                System.out.println();
            }
//            this.setAccountList((ArrayList<Account>) objStream.readObject());
            objStream.close();
        } catch (IOException e) {
            // Exception handling
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        if (this.getAccountList() == null) {
            this.setAccountList(new ArrayList<>());
        }
    }

    void addNewAcc(ArrayList<Account> accList) {

        // update the local account database of the FileAccRW object
        this.setAccountList(accList);

        try {
            OutputStream newOutputStream = new FileOutputStream(filename);
            ObjectOutputStream objOutStream = new ObjectOutputStream(newOutputStream);
            for (Account acc : accList) {
                objOutStream.writeObject(acc);
            }
            objOutStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    ArrayList<Account> getAccountList() {
        return this.accountList;
    }

    void setAccountList(ArrayList<Account> accList) {
        this.accountList = accList;
    }
  }