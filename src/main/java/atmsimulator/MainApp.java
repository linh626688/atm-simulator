package atmsimulator;

import atmsimulator.model.Account;
import atmsimulator.screen.WelcomeScreen;

import java.util.Arrays;
import java.util.List;

public class MainApp {
    public static List<Account> users = Arrays.asList(
            new Account("John Doe", "012108", 100, "112233"),
            new Account("Jane Doe", "932012", 30, "112244"));

    public static void main(String[] args) {
        WelcomeScreen welcomeScreen = new WelcomeScreen();
        welcomeScreen.show();
    }
}
