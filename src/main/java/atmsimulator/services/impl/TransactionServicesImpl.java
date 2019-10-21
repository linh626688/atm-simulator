package atmsimulator.services.impl;

import atmsimulator.model.Transaction;
import atmsimulator.repository.TransactionRepository;
import atmsimulator.services.TransactionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static atmsimulator.Constant.LIST_TRANSACTION_SIZE;

@Service
public class TransactionServicesImpl implements TransactionServices {

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public List<Transaction> latest10TransactionByAccount(String accountNumber) {
        List<Transaction> transactionList = transactionRepository.findTransactionByAccountNumber(accountNumber);
        return transactionList.size() >= LIST_TRANSACTION_SIZE
                ? transactionList.subList(transactionList.size() - LIST_TRANSACTION_SIZE, transactionList.size())
                : transactionList;
    }
}
