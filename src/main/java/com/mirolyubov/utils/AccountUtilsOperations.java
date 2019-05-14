package com.mirolyubov.utils;

import com.mirolyubov.entity.Account;
import com.mirolyubov.repository.AccountRepository;
import com.mirolyubov.entity.Transaction;
import com.mirolyubov.exceptions.NotEnoughMoneyException;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class AccountUtilsOperations {

    private static Logger logger = Logger.getLogger(AccountUtilsOperations.class);

    public long getBalanceOfAllAccounts() {
        long balance = 0;
        List<Account> accountList = AccountRepository.getInstance().getAccountList();
        for (Account account : accountList) {
            balance += account.getBalance();
        }
        return balance;
    }

    public boolean checkEnoughBalanceForTransaction(Account firstAccount, long amount, ReentrantLock firstLock, ReentrantLock secondLock) {
        if (firstAccount.getBalance() < amount) {
            firstLock.unlock();
            secondLock.unlock();
            Counter.counter4.incrementAndGet();
            try {
                throw new NotEnoughMoneyException();
            } catch (NotEnoughMoneyException e) {
                logger.error("Not enough money for transaction: need " + amount + " have " + firstAccount.getBalance());
                return false;
            }
        }
        return true;
    }

    public Transaction getTransactionEntity() {
        Transaction transaction = new Transaction();
        Random rand = new Random();
        List<Account> accountList = AccountRepository.getInstance().getAccountList();
        int arraySize = accountList.size();
        int randomElement = rand.nextInt(arraySize);
        int nextRandomElement = rand.nextInt(arraySize);
        if (randomElement == nextRandomElement) {
            while (randomElement == nextRandomElement) {
                nextRandomElement = rand.nextInt(arraySize);
            }
        }
        Account firstAccount = accountList.get(randomElement);
        Account secondAccount = accountList.get(nextRandomElement);
        transaction.setFirstAccount(firstAccount);
        transaction.setSecondAccount(secondAccount);
        TransactionUtilGenerator transactionUtilGenerator = new TransactionUtilGenerator();
        long amount = transactionUtilGenerator.makeTransactionSum();
        transaction.setAmount(amount);
        return transaction;
    }
}
