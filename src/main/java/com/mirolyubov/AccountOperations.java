package com.mirolyubov;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
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

    public List<Account> getPairAccount() {

        Random rand = new Random();
        List<Account> accountList = AccountRepository.getInstance().getAccountList();
        List<Account> pair = new ArrayList<>();

        int arraySize = accountList.size();
        int randomElement = rand.nextInt(arraySize);

        Account firstAccount = accountList.get(randomElement);

        firstAccount.getLock().lock();

        if (firstAccount.isLock()) {
            while (firstAccount.isLock()) {
                try {
                    wait(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            firstAccount.setLock(true);
            pair.add(firstAccount);
        } else {
            firstAccount.setLock(true);
            pair.add(firstAccount);
        }


        int nextRandomElement = rand.nextInt(arraySize);

        if (randomElement == nextRandomElement) {
            while (randomElement == nextRandomElement) {
                nextRandomElement = rand.nextInt(arraySize);
            }
        }

        Account secondAccount = accountList.get(nextRandomElement);

        if (secondAccount.isLock()) {
            while (secondAccount.isLock()) {
                try {
                    wait(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            secondAccount.setLock(true);
            pair.add(secondAccount);
        } else {
            secondAccount.setLock(true);
            pair.add(secondAccount);
        }

        return pair;
    }
}
