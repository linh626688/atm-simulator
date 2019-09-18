package atmsimulator.services.impl;

import atmsimulator.DAO.TransactionDAO;
import atmsimulator.DAO.impl.TransactionDAOImpl;
import atmsimulator.model.Transaction;
import atmsimulator.services.TransactionServices;

import java.util.List;

public class TransactionServicesImpl implements TransactionServices {
    private TransactionDAO transactionDAO = new TransactionDAOImpl();

    @Override
    public void addTransaction(Transaction transaction) {
        transactionDAO.addTransaction(transaction);
    }

    @Override
    public List<Transaction> latest10TransactionByAccount(String accountNumber) {
        List<Transaction> transactionList = transactionDAO.getTransactionByAccountNumber(accountNumber);
        return transactionList.size() >= 10
                ? transactionList.subList(transactionList.size() - 10, transactionList.size())
                : transactionList;
    }
}
