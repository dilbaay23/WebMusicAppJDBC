package utility;


public abstract class TextUtil {
    public static void printTitleTop(String title) {
        String line = generateLine('*', (title.length()));
        String line2 = "*"+generateLine(' ', title.length()-2) +"*";
        String titleNice = String.format("%s%n%s%n%s%n%s", line,line2,title,line2);
        System.out.println(titleNice);
    }
    public static void printTitle(String title) {
        String line = generateLine('*', title.length());
        String titleNice = String.format("%s%n%s%n%s", line, title, line);
        System.out.println(titleNice);
    }

    public static void printSubheading(String title) {
        String line = generateLine('-', title.length());
        String titleNice = String.format("%s%n%s", title, line);
        System.out.println(titleNice);
    }

    public static String generateLine(char c, int length) {
        StringBuilder lineAsSB = new StringBuilder();
        for (int i = 0; i < length; i++) {
            lineAsSB.append(c);
        }
        return lineAsSB.toString();
    }

}