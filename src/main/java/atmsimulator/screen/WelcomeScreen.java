package atmsimulator.screen;

import atmsimulator.services.UserServices;
import atmsimulator.services.impl.UserServicesImpl;

import java.util.Scanner;

public class WelcomeScreen implements BaseScreen {

    public static String accNumberStatic;
    public static String pinStatic;
    public static int balance;

    public void show() {

        UserServices userServices = new UserServicesImpl();

        WelcomeScreen welcomeScreen = new WelcomeScreen();
        Scanner scan = new Scanner(System.in);

        System.out.println("Welcome Screen");
        System.out.println("---------------");

        System.out.print("Enter Account Number: ");
        accNumberStatic = scan.nextLine();

        if (!userServices.validateAccountNumber(accNumberStatic)) {
            welcomeScreen.show();
        }


        System.out.print("Enter PIN: ");
        pinStatic = scan.nextLine();

        if (!userServices.validatePinNumber(pinStatic)) {
            welcomeScreen.show();
        }

        if (userServices.validate(accNumberStatic, pinStatic)) {
            TransactionScreen transactionScreen = new TransactionScreen();
            transactionScreen.show();
        }

    }

}
