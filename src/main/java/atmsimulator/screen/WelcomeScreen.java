package atmsimulator.screen;

import atmsimulator.services.UserServices;
import atmsimulator.services.impl.UserServicesImpl;

import java.util.Scanner;

import static atmsimulator.Constant.NUMBER_LENGTH;
import static atmsimulator.Constant.REGEX_MATCH_NUMBER;
import static atmsimulator.MainApp.transactionScreen;
import static atmsimulator.MainApp.welcomeScreen;

public class WelcomeScreen implements BaseScreen {

    public static String accNumberStatic;
    public static String pinStatic;
    public static int balance;

    public void show() {

        UserServices userServices = new UserServicesImpl();
        accNumberStatic = "";
        pinStatic = "";
        balance = 0;
        Scanner scan = new Scanner(System.in);

        System.out.println("Welcome Screen");
        System.out.println("---------------");

        System.out.print("Enter Account Number: ");
        accNumberStatic = scan.nextLine();

        if (!validateAccountNumber(accNumberStatic)) {
            welcomeScreen.show();
        }


        System.out.print("Enter PIN: ");
        pinStatic = scan.nextLine();

        if (!validatePinNumber(pinStatic)) {
            welcomeScreen.show();
        }

        if (userServices.validateUser(accNumberStatic, pinStatic)) {
            transactionScreen.show();
        }

    }

    public boolean validatePinNumber(String pin) {
        if (pin.length() != NUMBER_LENGTH) {
            System.out.println("PIN should have 6 digits length");
            return false;
        }
        if (!(pin.matches(REGEX_MATCH_NUMBER))) {
            System.out.println("PIN should only contains numbers");
            return false;
        }
        return true;
    }

    public boolean validateAccountNumber(String accountNumber) {
        if (accountNumber.length() != NUMBER_LENGTH) {
            System.out.println("Account Number should have 6 digits length");
            return false;
        }
        if (!accountNumber.matches(REGEX_MATCH_NUMBER)) {
            System.out.println("Account Number should only contains numbers");
        }
        return true;
    }

}
