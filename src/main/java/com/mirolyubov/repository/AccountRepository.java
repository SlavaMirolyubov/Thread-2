package com.mirolyubov.repository;

import com.mirolyubov.entity.Account;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccountRepository {

    private static AccountRepository instance;

    private List<Account> accountList;

    public List<Account> getAccountList() {
        return accountList;
    }

    private AccountRepository() {
        File directory = new File("accounts");
        File[] arrayFiles = directory.listFiles();
        accountList = new ArrayList<>();

        if (arrayFiles != null) {
            Arrays.stream(arrayFiles)
                    .forEach(x -> {
                        try (ObjectInputStream objectInputStream = new ObjectInputStream(
                                new FileInputStream(x.getPath()))) {
                            Account account = (Account) objectInputStream.readObject();
                            accountList.add(account);
                        } catch (IOException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    });

        }
    }

    public static AccountRepository getInstance() {
        if(instance == null) {
            instance = new AccountRepository();
        }
        return instance;
    }

}
