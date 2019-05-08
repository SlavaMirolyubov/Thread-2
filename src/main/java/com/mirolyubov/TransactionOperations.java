package com.mirolyubov;

import com.mirolyubov.utils.AccountUtilsOperations;
import com.mirolyubov.utils.TransactionGenerator;

import java.util.List;

public class TransactionOperations {

    public void makeTransaction() {

//        AccountOperations accountOperations = new AccountOperations();
//        TransactionGenerator transactionGenerator = new TransactionGenerator();
//        AccountUtilsOperations accountUtilsOperations = new AccountUtilsOperations();
//
//        List<Account> accountList = accountOperations.getPairAccount();
//        Account firstAccount = accountList.get(0);
//        Account secondAccount = accountList.get(1);
//
//        boolean isTransactionAvailable = accountUtilsOperations.isTransactionAvailable(firstAccount);
//
//        if (isTransactionAvailable) {
//            long amount = transactionGenerator.makeTransactionSum(firstAccount.getBalance());
////            transferMoney(firstAccount, secondAccount, amount);
//            firstAccount.setBalance(firstAccount.getBalance() - amount);
//            secondAccount.setBalance(secondAccount.getBalance() + amount);
//            Counter.setCounter();
//            accountList.get(0).getLock().unlock();
//            accountList.get(1).getLock().unlock();
//
//        } else {
//            System.out.println("sdgfsdgsh");//Допилить исключение
//        }
    }

    private synchronized void transferMoney(Account firstAccount, Account secondAccount, long amount) {
        firstAccount.setBalance(firstAccount.getBalance() - amount);
        secondAccount.setBalance(secondAccount.getBalance() + amount);
    }
}
