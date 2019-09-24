package atmsimulator.services;

import org.springframework.stereotype.Service;

public interface FundTransferServices {

    public String submitFundTransaction(String accountNumber, String pin, String destination, int amount, String ref);

}
