package com.mirolyubov.utils;

import com.mirolyubov.Account;

public class AccountUtilsOperations {
    public boolean isTransactionAvailable(Account account) {
        return account.getBalance() != 0;
    }
}
