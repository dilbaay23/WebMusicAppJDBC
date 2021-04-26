package utility;

public abstract class MenuUtility {
    private final static int FULL_WIDTH = 50;

    public static String thickLine() {
        return generateCharNTimes('*', FULL_WIDTH);
    }

    public static String tildaLine() {
        return generateCharNTimes('~', FULL_WIDTH);
    }

    public static String thinLine() {
        return generateCharNTimes('-', FULL_WIDTH);
    }

    private static String generateCharNTimes(char c, int amount) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < amount; i++) {
            text.append(c);
        }
        return text.toString();
    }

    public static String center(String text) {
        return String.format("%" + (FULL_WIDTH / 2 + text.length() / 2) + "s", text);
    }
}