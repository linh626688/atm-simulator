package atmsimulator.DAO;

import atmsimulator.model.Account;

public interface AccountDAO {

    void importAccount();

    Account findUserByAccountNumber(String accountNumber);

    Account findUserByAccountNumberAndPin(String accountNumber, String pin);
}
