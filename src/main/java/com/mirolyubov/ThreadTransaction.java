package com.mirolyubov;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTransaction implements Runnable {

    @Override
    public void run() {

        new AccountOperations().getPairAccount();

    }
}