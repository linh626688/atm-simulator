package atmsimulator.utils;


import static atmsimulator.Constant.*;

public class AccountUtils {

    public String validateAccountNumber(String accountNumber) {
        if (accountNumber.length() != NUMBER_LENGTH) {
            return "Account Number should have 6 digits length";
        }
        if (!accountNumber.matches(REGEX_MATCH_NUMBER)) {
            return "Account Number should only contains numbers";
        }
        return VALID_VALUE;
    }

    public String validatePinNumber(String pin) {
        if (pin.length() != NUMBER_LENGTH) {
            return "PIN should have 6 digits length";
        }
        if (!(pin.matches(REGEX_MATCH_NUMBER))) {
            return "PIN should only contains numbers";
        }
        return VALID_VALUE;
    }

    public String validateAmount(String amount, String balance) {
        if (!amount.matches(REGEX_MATCH_NUMBER)) {
            return OTHER_WITHDRAW_SCREEN;
        } else if (Integer.parseInt(amount) % 10 != 0) {
            return OTHER_WITHDRAW_SCREEN;

        } else if (Integer.parseInt(amount) > 1000) {
            return OTHER_WITHDRAW_SCREEN;

        } else if (Integer.parseInt(balance) - Integer.parseInt(amount) < 0) {
            return OTHER_WITHDRAW_SCREEN;
        }
        return VALID_VALUE;
    }
}
