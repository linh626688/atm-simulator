package atmsimulator.services.impl;

import atmsimulator.model.Transaction;
import atmsimulator.repository.TransactionRepository;
import atmsimulator.services.TransactionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TransactionServicesImpl implements TransactionServices {

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public List<Transaction> latest10TransactionByAccount(String accountNumber) {
        List<Transaction> transactionList = transactionRepository.findTransactionByAccountNumber(accountNumber);
        return transactionList.size() >= 10
                ? transactionList.subList(transactionList.size() - 10, transactionList.size())
                : transactionList;
    }

    @Override
    public Page<Transaction> findTransactionPagination(String accountNumber, Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        Sort sort = new Sort(new Sort.Order(Direction.DESC, "id"));
        PageRequest pageRequest = new PageRequest(currentPage, pageSize, sort);
        return transactionRepository.findAllByAccountNumber(accountNumber, pageRequest);
    }
}
