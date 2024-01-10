import java.util.regex.*;

public class NumberFinder {
    public static void main(String[] args) {
        String string = "The price of  the product is $19.99, -201, 7";
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        Matcher matcher = pattern.matcher(string);
        boolean numbersFound = false;

        while (matcher.find()) {
            numbersFound = true;
            System.out.println(matcher.group());
        }

        if (!numbersFound) {
            System.out.println("В данном тексте не найдено чисел.");
        }
    }
}
