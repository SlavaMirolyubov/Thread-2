package com.mirolyubov;

import com.mirolyubov.repository.AccountRepository;
import com.mirolyubov.services.AccountOperations;
import com.mirolyubov.thread.ThreadTransaction;
import com.mirolyubov.utils.Counter;
import com.mirolyubov.services.ZeroPosition;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainTest {

    public static final int NUMBER_OF_TRANSACTIONS = 1000;
    public static final int NUMBER_OF_THREADS = 20;
    public static final int NUMBER_OF_ACCOUNTS = 10;

    @Test
    public void main() {
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
        System.out.println("Overall balance " + accountOperations.getBalanceOfAllAccounts());
        System.out.println("Success operations " + Counter.getCounter());
        System.out.println("Number of tries " + Counter.counter2);
    }
}