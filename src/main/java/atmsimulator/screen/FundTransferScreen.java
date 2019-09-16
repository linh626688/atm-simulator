package atmsimulator.screen;


import atmsimulator.model.Account;

import java.util.Scanner;

import static atmsimulator.Constant.*;
import static atmsimulator.MainApp.*;

public class FundTransferScreen implements BaseScreen {
    public static String destinationAcc;
    public static String transferAmt;
    public static String referenceNum;

    public void show() {

        System.out.println("Transfer Screen");
        System.out.println("----------------");
        inputDestinationAccount();

    }
    public void inputDestinationAccount() {
        System.out.print("Please enter destination account: ");
        Scanner scan = new Scanner(System.in);
        FundTransferScreen.destinationAcc = scan.nextLine();

        if (!FundTransferScreen.destinationAcc.matches(REGEX_MATCH_NUMBER)) {
            System.out.println("Invalid account");
            inputDestinationAccount();
        }
        Account account =
                users.stream()
                        .filter(user ->
                                FundTransferScreen.destinationAcc.equals(user.getAccountNumber())
                                        && !WelcomeScreen.accNumberStatic.equals(user.getAccountNumber()))
                        .findAny()
                        .orElse(null);
        if (account != null) {
            inputAmount();

        } else {
            System.out.println("Invalid account");
            inputDestinationAccount();
        }
    }

    public void inputAmount() {
        System.out.print("Please enter transfer amount: ");
        Scanner scan = new Scanner(System.in);
        FundTransferScreen.transferAmt = scan.nextLine();

        if (!FundTransferScreen.transferAmt.matches(REGEX_INPUT_AMOUNT)) {
            System.out.println("Invalid amount");
            inputAmount();
        } else if (Integer.parseInt(FundTransferScreen.transferAmt) > MAX_VALUE_TRANSFER) {
            System.out.println("Maximum amount to transfer is $1000");
            inputAmount();
        } else if (WelcomeScreen.balance - Integer.parseInt(FundTransferScreen.transferAmt) < 0) {
            System.out.println("Insufficient balance $" + FundTransferScreen.transferAmt);
            inputAmount();
        } else if (Integer.parseInt(FundTransferScreen.transferAmt) < 1) {
            System.out.println("Minimum amount to transfer is $1");
        } else {
            inputReferenceNumber();
        }
    }

    public void inputReferenceNumber() {
        System.out.print("Please enter reference number: ");
        Scanner scan = new Scanner(System.in);
        FundTransferScreen.referenceNum = scan.nextLine();

        if (!FundTransferScreen.referenceNum.matches(REGEX_MATCH_NUMBER) && !FundTransferScreen.referenceNum.isEmpty()) {
            System.out.println("Invalid Reference Number");
            inputReferenceNumber();
        } else {
            transferConfirm(fundTransferScreen);
        }
    }

    public void transferConfirm(BaseScreen fundTransferScreen) {

        System.out.println("Transfer Confirmation");
        System.out.println("Destination Account: " + FundTransferScreen.destinationAcc);
        System.out.println("Transfer Amount: $" + FundTransferScreen.transferAmt);
        System.out.println("Reference Number: " + FundTransferScreen.referenceNum);

        System.out.println("1. Confirm Trx");
        System.out.println("2. Cancel Trx");

        Scanner scan = new Scanner(System.in);
        String opt = scan.nextLine();

        switch (opt) {
            case "1":
                System.out.println("Move to fund Transfer summary screen");
                WelcomeScreen.balance -= Integer.parseInt(FundTransferScreen.transferAmt);
                fundTransferSummaryScreen.show();
                break;
            case "2":
                transactionScreen.show();
                break;
            case "":
                fundTransferScreen.show();
                break;
            default:
                break;
        }
    }
}
