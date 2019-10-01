package atmsimulator.utils;


import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Utils {
    private static final Random generator = new Random();

    public static int generateRandomRef() {
        return 100000 + generator.nextInt(900000);
    }

    public static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm a");

}
