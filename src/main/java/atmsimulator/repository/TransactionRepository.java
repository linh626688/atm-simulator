package atmsimulator.repository;

import atmsimulator.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

    List<Transaction> findTransactionByAccountNumber(String accountNumber);

}
