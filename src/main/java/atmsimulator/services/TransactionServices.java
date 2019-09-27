package atmsimulator.services;

import atmsimulator.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TransactionServices {

    List<Transaction> latest10TransactionByAccount(String accountNumber);

    Page<Transaction> findTransactionPagination(String accountNumber, Pageable pageable);

}
