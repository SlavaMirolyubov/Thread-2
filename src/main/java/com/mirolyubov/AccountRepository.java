package com.mirolyubov;

import java.util.ArrayList;
import java.util.List;

public class AccountRepository {

    private static AccountRepository instance;

    volatile static int count;

    public static int getCount() {
        return count;
    }

    private List<Account> accountList;

    public List<Account> getAccountList() {
        return accountList;
    }

    private AccountRepository() {
        accountList = new AccountOperations().fillAccountList();
    }

    public static AccountRepository getInstance() {
        if(instance == null) {
            instance = new AccountRepository();
        }

        count++;
        return instance;
    }

}
