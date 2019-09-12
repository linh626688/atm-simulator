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
        String accountNumber = scan.nextLine();
        String acc = accountNumber;
        accNumberStatic = acc;

        userServices.validateAccountNumber(accNumberStatic, welcomeScreen);

        System.out.print("Enter PIN: ");
        String pin = scan.nextLine();
        pinStatic = pin;

        userServices.validatePinNumber(pinStatic, welcomeScreen);

        userServices.validate( accNumberStatic, pinStatic);

    }

}
