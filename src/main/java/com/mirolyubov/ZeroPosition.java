package com.mirolyubov;

import com.mirolyubov.entity.Account;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.concurrent.ThreadLocalRandom;

public class ZeroPosition {

    public void setAccountsZeroPosition() {
        deleteAllAccounts();
        for (int i = 1; i < Main.NUMBER_OF_ACCOUNTS+1; i++) {

            long balance = ThreadLocalRandom.current().nextLong(2000);

            Account account = new Account(i, balance);

            String filename = String.format("accounts/file%s.bin", i);

            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    new FileOutputStream(filename))) {
                objectOutputStream.writeObject(account);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteAllAccounts() {
        try {
            FileUtils.cleanDirectory(new File("accounts"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
