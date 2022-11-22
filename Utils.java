public class Utils {
    public static boolean toPrint = false;

    public static void show(String text) {
        if (toPrint) {
            System.out.println(text);
        }
    }
}
