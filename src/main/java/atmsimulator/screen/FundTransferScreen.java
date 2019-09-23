package atmsimulator.screen;


import atmsimulator.model.Account;
import atmsimulator.model.Transaction;
import atmsimulator.services.TransactionServices;
import atmsimulator.services.impl.TransactionServicesImpl;
import atmsimulator.utils.Utils;

import java.time.LocalDateTime;
import java.util.Scanner;

import static atmsimulator.Constant.*;
import static atmsimulator.MainApp.*;

public class FundTransferScreen implements BaseScreen {
    public static String destinationAcc;
    public static String transferAmt;
    public static String referenceNum;
    private TransactionServices transactionServices = new TransactionServicesImpl();

    public void show() {

        System.out.println("Transfer Screen");
        System.out.println("----------------");
        inputDestinationAccount();

    }

    public void inputDestinationAccount() {
        System.out.print("Please enter destination account: ");
        Scanner scan = new Scanner(System.in);
        FundTransferScreen.destinationAcc = scan.nextLine();

        if (FundTransferScreen.destinationAcc == null
                || !FundTransferScreen.destinationAcc.matches(REGEX_MATCH_NUMBER)) {
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

        if (FundTransferScreen.transferAmt == null ||
                !FundTransferScreen.transferAmt.matches(REGEX_INPUT_AMOUNT)) {
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

        if (FundTransferScreen.referenceNum == null || !FundTransferScreen.referenceNum.matches(REGEX_MATCH_NUMBER)) {
            System.out.println("Invalid Reference Number");
            inputReferenceNumber();
        } else {
            transferConfirm();
        }
    }

    public void transferConfirm() {

        System.out.println("Transfer Confirmation");
        System.out.println("Destination Account: " + FundTransferScreen.destinationAcc);
        System.out.println("Transfer Amount: $" + FundTransferScreen.transferAmt);
        System.out.println("Reference Number: " + FundTransferScreen.referenceNum);

        System.out.println("1. Confirm Trx");
        System.out.println("2. Cancel Trx");
        System.out.println("Choose option[2]:");

        Scanner scan = new Scanner(System.in);
        String opt = scan.nextLine();

        switch (opt) {
            case "1":
                System.out.println("Move to fund Transfer summary screen");
                WelcomeScreen.balance -= Integer.parseInt(FundTransferScreen.transferAmt);
                Transaction transaction = new Transaction();
                transaction.setAccountNumber(WelcomeScreen.accNumberStatic);
                transaction.setAmount(FundTransferScreen.transferAmt);
                transaction.setRef(FundTransferScreen.referenceNum);
                transaction.setTime(Utils.dateTimeFormat.format(LocalDateTime.now()));
                transaction.setType(TRANSACTION_FUND_TRANSFER);
                transactionServices.addTransaction(transaction);
                fundTransferSummaryScreen.show();
                break;
            case "2":
                transactionScreen.show();
                break;
            default:
                transactionScreen.show();
                break;
        }
    }
}
