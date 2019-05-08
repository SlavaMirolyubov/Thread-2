package com.mirolyubov;

import java.util.concurrent.*;

public class Main {

    public static final int NUMBER_OF_TRANSACTIONS = 1000;
    public static final int NUMBER_OF_THREADS = 20;
    public static final int NUMBER_OF_ACCOUNTS = 10;

    public static void main(String[] args) {

        ZeroPosition zeroPosition = new ZeroPosition();
        zeroPosition.setAccountsZeroPosition();

        AccountOperations accountOperations = new AccountOperations();
        System.out.println(accountOperations.getBalanceOfAllAccounts());
        System.out.println(AccountRepository.getInstance().getAccountList());

        ExecutorService service = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

        for (int i=0; i < NUMBER_OF_TRANSACTIONS; i++){
            service.submit(new ThreadTransaction());
        }
        service.shutdown();
        try {
            service.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(AccountRepository.getInstance().getAccountList());
        System.out.println(accountOperations.getBalanceOfAllAccounts());
        System.out.println(AccountRepository.getCount());

    }
}
