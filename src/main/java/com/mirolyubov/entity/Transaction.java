package com.mirolyubov.entity;

public class Transaction {
    private Account firstAccount;
    private Account secondAccount;
    private long amount;

    public Account getFirstAccount() {
        return firstAccount;
    }

    public void setFirstAccount(Account firstAccount) {
        this.firstAccount = firstAccount;
    }

    public Account getSecondAccount() {
        return secondAccount;
    }

    public void setSecondAccount(Account secondAccount) {
        this.secondAccount = secondAccount;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
