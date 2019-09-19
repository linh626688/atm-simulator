package atmsimulator.services.impl;

import atmsimulator.model.Transaction;
import atmsimulator.repository.TransactionRepository;
import atmsimulator.services.TransactionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServicesImpl implements TransactionServices {

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public List<Transaction> latest10TransactionByAccount(String accountNumber) {
        List<Transaction> transactionList = (List<Transaction>) transactionRepository.findTransactionByAccountNumber(accountNumber);
        return transactionList.size() >= 10
                ? transactionList.subList(transactionList.size() - 10, transactionList.size())
                : transactionList;
    }
}
