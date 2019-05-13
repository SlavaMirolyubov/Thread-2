package com.mirolyubov.services;

import com.mirolyubov.entity.Account;
import com.mirolyubov.AccountRepository;
import com.mirolyubov.Counter;
import com.mirolyubov.entity.Transaction;
import com.mirolyubov.utils.AccountUtilsOperations;
import com.mirolyubov.utils.TransactionUtilGenerator;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class AccountOperations {

    private static Logger logger = Logger.getLogger(AccountOperations.class);

    public void makeTransaction(Account firstAccount, Account secondAccount, long amount) {

        ReentrantLock firstLock = firstAccount.getId() < secondAccount.getId() ?
                firstAccount.getLock() : secondAccount.getLock();
        firstLock.lock();

        ReentrantLock secondLock = firstAccount.getId() > secondAccount.getId() ?
                firstAccount.getLock() : secondAccount.getLock();
        secondLock.lock();

        Counter.counter2.incrementAndGet();

        AccountUtilsOperations accountUtilsOperations = new AccountUtilsOperations();

        boolean isEnoughMoney = accountUtilsOperations.checkEnoughBalanceForTransaction(firstAccount, amount, firstLock, secondLock);

        if (isEnoughMoney) {
            firstAccount.setBalance(firstAccount.getBalance() - amount);
            secondAccount.setBalance(secondAccount.getBalance() + amount);
            logger.info("Account ID: " + firstAccount.getId() + " send money to Account ID: " + secondAccount.getId());
            Counter.counter.incrementAndGet();
            firstLock.unlock();
            secondLock.unlock();
        }
    }
}
