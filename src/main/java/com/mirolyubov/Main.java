package com.mirolyubov;

import com.mirolyubov.thread.ThreadTransaction;
import com.mirolyubov.utils.AccountUtilsOperations;
import com.mirolyubov.utils.Counter;
import com.mirolyubov.services.ZeroPosition;
import org.apache.log4j.Logger;

import java.util.concurrent.*;
import java.util.stream.IntStream;


public class Main {

    public static final int NUMBER_OF_TRANSACTIONS = 10000;
    public static final int NUMBER_OF_THREADS = 30;
    public static final int NUMBER_OF_ACCOUNTS = 10;

    private static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        ZeroPosition zeroPosition = new ZeroPosition();
        zeroPosition.setAccountsZeroPosition();
        AccountUtilsOperations accountUtilsOperations = new AccountUtilsOperations();
        ExecutorService service = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        IntStream.range(0, NUMBER_OF_TRANSACTIONS)
                .forEach(x -> service.submit(new ThreadTransaction()));
        service.shutdown();
        try {
            service.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("Initial balance: " + accountUtilsOperations.getBalanceOfAllAccounts());
        logger.info("Overall balance: " + accountUtilsOperations.getBalanceOfAllAccounts());
        logger.info("Successful operations: " + Counter.counter.get());
        logger.info("Unsuccessful operations: " + Counter.counter4.get());
        logger.info("Number of tries: " + Counter.counter2.get());

    }
}
