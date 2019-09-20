package atmsimulator.services;

import org.springframework.stereotype.Service;

public interface FundTransferServices {

    public boolean submitFundTransaction(String accountNumber, String pin,String destination, int amount) throws Exception;

}
