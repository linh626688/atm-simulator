package atmsimulator.repository;

import atmsimulator.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> findTransactionByAccountNumber(String accountNumber);

    Page<Transaction> findAllByAccountNumber(String accountNumber, Pageable pageable);

}
