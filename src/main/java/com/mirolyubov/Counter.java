package com.mirolyubov;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
    static AtomicInteger counter = new AtomicInteger(0);
    static AtomicInteger counter2 = new AtomicInteger(0);
    static volatile int counter3;
    static AtomicInteger counter4 = new AtomicInteger(0);


}
