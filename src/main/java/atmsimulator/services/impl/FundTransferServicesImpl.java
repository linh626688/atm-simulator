package atmsimulator.services.impl;

import atmsimulator.model.Account;
import atmsimulator.screen.*;
import atmsimulator.services.FundTransferServices;

import java.util.Scanner;

import static atmsimulator.MainApp.users;
import static atmsimulator.Constant.REGEX_MATCH_NUMBER;

public class FundTransferServicesImpl implements FundTransferServices {

    @Override
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
                        .filter(user -> FundTransferScreen.destinationAcc.equals(user.getAccountNumber()))
                        .findAny()
                        .orElse(null);
        if (account != null) {
            inputAmount();

        } else {
            System.out.println("Invalid account");
            inputDestinationAccount();
        }
    }

    @Override
    public void inputAmount() {
        String regex = "\\d+";
        System.out.print("Please enter transfer amount: ");
        Scanner scan = new Scanner(System.in);
        FundTransferScreen.transferAmt = scan.nextLine();

        if (!FundTransferScreen.transferAmt.matches(regex)) {
            System.out.println("Invalid amount");
            inputAmount();
        } else if (Integer.parseInt(FundTransferScreen.transferAmt) > 1000) {
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

    @Override
    public void inputReferenceNumber() {
        FundTransferScreen fundTransferScreen = new FundTransferScreen();
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

    @Override
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
                FundTransferSummaryScreen fundTransferSummaryScreen = new FundTransferSummaryScreen();
                System.out.println("Move to fund Transfer summary screen");
                WelcomeScreen.balance -= Integer.parseInt(FundTransferScreen.transferAmt);
                fundTransferSummaryScreen.show();
                break;
            case "2":
                TransactionScreen transactionScreen = new TransactionScreen();
                transactionScreen.show();
                break;
            case "":
                fundTransferScreen.show();
                break;
            default:
                break;
        }
    }

    @Override
    public void transaction() {
        Account user = users.stream()
                .filter(account -> account.getAccountNumber().equals(WelcomeScreen.accNumberStatic) && account.getPIN().equals(WelcomeScreen.pinStatic))
                .findAny().orElse(null);
        if (user != null) {
            user.setBalance(WelcomeScreen.balance);
            System.out.println("Transaction Succeed");
        }
        TransactionScreen transactionScreen = new TransactionScreen();
        transactionScreen.show();
    }
}
