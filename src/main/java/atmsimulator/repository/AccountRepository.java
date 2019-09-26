package atmsimulator.repository;

import atmsimulator.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    Optional<Account> findAccountByAccountNumber(String accountNumber);

    Account findAccountByAccountNumberAndPin(String accountNumber, String pin);
}
