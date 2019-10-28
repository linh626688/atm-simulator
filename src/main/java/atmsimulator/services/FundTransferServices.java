package atmsimulator.services;

public interface FundTransferServices {

    String submitFundTransaction(String accountNumber, String pin, String destination, int amount, String ref);

}
