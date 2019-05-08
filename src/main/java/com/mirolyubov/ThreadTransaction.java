package com.mirolyubov;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTransaction implements Runnable {

    Lock lock = new ReentrantLock();

    @Override
    public void run() {
//        lock.lock();
        new TransactionOperations().makeTransaction();
//        lock.unlock();
    }
}