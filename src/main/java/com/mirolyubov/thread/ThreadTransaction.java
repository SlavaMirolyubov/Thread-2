package com.mirolyubov.thread;

import com.mirolyubov.entity.Transaction;
import com.mirolyubov.services.AccountOperations;
import com.mirolyubov.utils.AccountUtilsOperations;

public class ThreadTransaction implements Runnable {

    @Override
    public void run() {
        AccountUtilsOperations accountUtilsOperations = new AccountUtilsOperations();
        Transaction transaction = accountUtilsOperations.getTransactionEntity();
        new AccountOperations().makeTransaction(transaction.getFirstAccount(), transaction.getSecondAccount(), transaction.getAmount());
    }
}