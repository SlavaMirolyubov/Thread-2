package com.mirolyubov.entity;

import java.io.Serializable;
import java.util.concurrent.locks.ReentrantLock;

public class Account implements Serializable {
    private long id;
    private ReentrantLock lock = new ReentrantLock();
    private volatile long balance;

    public Account(long id, long balance) {
        this.id = id;
        this.balance = balance;
    }

    public ReentrantLock getLock() {
        return lock;
    }

    public long getId() {
        return id;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", balance=" + balance +
                '}';
    }
}
