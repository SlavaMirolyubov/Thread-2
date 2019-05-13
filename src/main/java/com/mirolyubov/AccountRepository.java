package com.mirolyubov;

import com.mirolyubov.entity.Account;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository {

    private static AccountRepository instance;

    public volatile static int count;

    public static int getCount() {
        return count;
    }

    private List<Account> accountList;

    public List<Account> getAccountList() {
        return accountList;
    }

    private AccountRepository() {
        File directory = new File("accounts");
        File[] arrayFiles = directory.listFiles();
        accountList = new ArrayList<>();
        if (arrayFiles != null) {
            for (File file : arrayFiles) {
                try (ObjectInputStream objectInputStream = new ObjectInputStream(
                        new FileInputStream(file.getPath()))) {
                    Account account = (Account) objectInputStream.readObject();
                    accountList.add(account);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

        public static AccountRepository getInstance() {
        if(instance == null) {
            instance = new AccountRepository();
        }

        count++;
        return instance;
    }

}
