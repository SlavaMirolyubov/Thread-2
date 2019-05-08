package com.mirolyubov;

import com.mirolyubov.utils.AccountUtilsOperations;
import com.mirolyubov.utils.TransactionGenerator;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AccountOperations {

    public List<Account> fillAccountList(){
        File directory = new File("accounts");
        File[] arrayFiles = directory.listFiles();
        List<Account> accountList = new ArrayList<>();
        for (File file : arrayFiles) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(
                new FileInputStream(file.getPath()))) {
            Account account = (Account) objectInputStream.readObject();
            accountList.add(account);
        } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
    }
        return accountList;
    }

    public long getBalanceOfAllAccounts() {
        long balance = 0;
        List<Account> accountList = AccountRepository.getInstance().getAccountList();
        for (Account account : accountList) {
            balance += account.getBalance();
        }
        return balance;
    }

    public void getPairAccount() {



        Random rand = new Random();
        List<Account> accountList = AccountRepository.getInstance().getAccountList();
        List<Account> pair = new ArrayList<>();

        int arraySize = accountList.size();
        int randomElement = rand.nextInt(arraySize);

        Account firstAccount = accountList.get(randomElement);

        int nextRandomElement = rand.nextInt(arraySize);

        if (randomElement == nextRandomElement) {
            while (randomElement == nextRandomElement) {
                nextRandomElement = rand.nextInt(arraySize);
            }
        }

        Account secondAccount = accountList.get(nextRandomElement);

        Counter.counter3++;

        ReentrantLock firstLock = firstAccount.getId() < secondAccount.getId() ?
                firstAccount.getLock() : secondAccount.getLock();
        firstLock.lock();

        ReentrantLock secondLock = firstAccount.getId() > secondAccount.getId() ?
                firstAccount.getLock() : secondAccount.getLock();
        secondLock.lock();

        Counter.counter2.incrementAndGet();

        AccountUtilsOperations accountUtilsOperations = new AccountUtilsOperations();

        boolean isTransactionAvailable = accountUtilsOperations.isTransactionAvailable(firstAccount);

        if (isTransactionAvailable) {
            long amount = ThreadLocalRandom.current().nextLong(2000);
//            transferMoney(firstAccount, secondAccount, amount);
            if (firstAccount.getBalance()< amount) {
                firstLock.unlock();
                secondLock.unlock();
                Counter.counter4.incrementAndGet();
                return;
            }
            firstAccount.setBalance(firstAccount.getBalance() - amount);
            secondAccount.setBalance(secondAccount.getBalance() + amount);
            Counter.counter.incrementAndGet();
            firstLock.unlock();
            secondLock.unlock();

        } else {
            firstLock.unlock();
            secondLock.unlock();
            System.out.println("sdgfsdgsh");//Допилить исключение
        }
    }
}
