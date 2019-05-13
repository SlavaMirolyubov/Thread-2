package com.mirolyubov.utils;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class TransactionUtilGenerator {
    public long makeTransactionSum() {
        return ThreadLocalRandom.current().nextLong(2000);
    }
}
