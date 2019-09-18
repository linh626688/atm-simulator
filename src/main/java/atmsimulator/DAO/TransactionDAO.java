package atmsimulator.DAO;

import atmsimulator.model.Transaction;

import java.util.List;

public interface TransactionDAO {
    void addTransaction(Transaction transaction);

    List<Transaction> getTransactionByAccountNumber(String accountNumber);
}
