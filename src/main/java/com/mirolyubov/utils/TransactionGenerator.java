package com.mirolyubov.utils;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class TransactionGenerator {
    public long makeTransactionSum(long balance) {
        return ThreadLocalRandom.current().nextLong(balance+1);
    }
}
