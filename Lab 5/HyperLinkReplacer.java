import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HyperLinkReplacer {
    public static void main(String[] args) {
        String text = "Пример текста с ссылками: " +
                "Ссылка 1: https://abcd.com, " +
                "Ссылка 2: http://abcd.com, " +
                "Ссылка 3: www.abcd";

        String replacedText = replaceLinks(text);

        System.out.println("Исходные ссылки:");
        System.out.println(text);
        System.out.println("\nРезультат замены:");
        System.out.println(replacedText);
    }

    public static String replaceLinks(String text) {
        String regex = "\\b(?:https?://|www\\.)\\S+\\b";

        String replacement = "<a href=\"$0\">$0</a>";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(text);

        String replacedText = matcher.replaceAll(replacement);

        return replacedText;
    }
}

