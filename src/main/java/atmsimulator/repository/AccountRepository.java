package atmsimulator.repository;

import atmsimulator.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

    Account findAccountByAccountNumber(String accountNumber);

    Account findAccountByAccountNumberAndPin(String accountNumber, String pin);
}
