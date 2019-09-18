package atmsimulator.services;

import atmsimulator.model.Transaction;

import java.util.List;

public interface TransactionServices {

    void addTransaction(Transaction transaction);

    List<Transaction> latest10TransactionByAccount(String accountNumber);
}
