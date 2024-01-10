import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {

        System.out.println("-----Task1-----");
        System.out.println(replaceVovels("apple"));
        System.out.println(replaceVovels("Even if you did this task not by yourself, you have to understand every single line of code."));
        System.out.println();

        System.out.println("-----Task2-----");
        System.out.println(stringTransform("hello"));
        System.out.println(stringTransform("bookkeper"));
        System.out.println();

        System.out.println("-----Task3-----");
        System.out.println(doesBlockFit(1,3,5,4,5));
        System.out.println(doesBlockFit(1,8,1,1,1));
        System.out.println(doesBlockFit(1,2,2,1,1));
        System.out.println();

        System.out.println("-----Task4-----");
        System.out.println(numCheck(243));
        System.out.println(numCheck(52));
        System.out.println();

        System.out.println("-----Task5-----");
        System.out.println(countRoots(new int[] {1,-3,2}));
        System.out.println(countRoots(new int[] {2,5,2}));
        System.out.println(countRoots(new int[] {1,-6,9}));
        System.out.println();

        System.out.println("-----Task6-----");
        System.out.println(salesData(new String[][] {{"Apple","Shop1","Shop2","Shop3","Shop4"},
                {"Banana","Shop2","Shop3","Shop4"},
                {"Orange","Shop1","Shop3","Shop4"},
                {"Pear", "Shop2", "Shop4"}}));
        System.out.println(salesData(new String[][] {{"Fridge","Shop2","Shop3"},
                {"Microwave","Shop1","Shop2","Shop3","Shop4"},
                {"Laptop","Shop3","Shop4"},
                {"Phone", "Shop1","Shop2","Shop3","Shop4"}}));
        System.out.println();

        System.out.println("-----Task7-----");
        System.out.println(validSplit("apple eagle egg goat"));
        System.out.println(validSplit("cat tog goose fish"));
        System.out.println();

        System.out.println("-----Task8-----");
        System.out.println(waveForm(new int[] {3, 1, 4, 2, 7, 5}));
        System.out.println(waveForm(new int[] {1, 2, 3, 4, 5}));
        System.out.println(waveForm(new int[] {1, 2, -6, 10, 3}));
        System.out.println();

        System.out.println("-----Task9-----");
        System.out.println(commonVowel("Hello world"));
        System.out.println(commonVowel("Actions speak louder than words."));
        System.out.println();

        System.out.println("-----Task10-----");
        int[][] firstx = {{1,2,3,4,5},{6,7,8,9,10},{5,5,5,5,35},{7,4,3,14,2},{1,0,11,10,1}};
        int[][] secondx = {{6,4,19,0,0},{81,25,3,1,17},{48,12,60,32,14},{91,47,16,65,217},{5,73,0,4,21}};
        System.out.println(Arrays.deepToString(dataScience(firstx)));
        System.out.println(Arrays.deepToString(dataScience(secondx)));
    }
    public static String replaceVovels(String x){ //+
        x = x.replaceAll("[AEIOUYaeiouy]","*");
        return x;
    }
    public static String stringTransform(String x){ //+
        StringBuilder output = new StringBuilder();
        for(int i = 0; i < x.length();i++){
            String str = String.valueOf(x.charAt(i));
            if (i < x.length() - 1 && x.charAt(i) == x.charAt(i + 1)){
                output.append("Double").append(str.toUpperCase());
                i++; // Значение i увеличивается на 1, чтобы пропустить следующую проверяемую букву в паре и перейти к следующему символу в строке x.
            }else{
                output.append(str);
            }
        }
        return output.toString();
    }
    public static boolean doesBlockFit(int a, int b, int c, int w, int h){ //+
        if ((a * b <= w * h) || (a * c <= w * h) || (b * c <= w * h)){
            return true;
        }
        return false;
    }
    public static boolean numCheck(int x){ // +
        int sum = 0;
        int c = x;
        while (x > 0){
            sum = sum + (x % 10) * (x % 10);
            x = x / 10;
        }
        if ((c % 2 != 0) && (sum % 2 != 0)){
            return true;
        }else{
            return false;
        }
    }
    public static int countRoots(int[] x){ // +
        int y = x[0];
        int c = x[1];
        int z = x[2];
        int sum = c * c - 4 * y * z;
        if(sum > 0){
            return 2;
        } else if (sum == 0) {
            return 1;
        }else {
            return 0;
        }
    }
    public static ArrayList<String> salesData(String[][] magazine) {
        int maxLength = Arrays.stream(magazine).mapToInt(innerArray -> innerArray.length).max().orElse(0);
        ArrayList<String> freqPurchasedProduct = new ArrayList<>();
        for (String[] i : magazine) {
            if (i.length == maxLength) {
                freqPurchasedProduct.add(i[0]);
            }
        }
        return freqPurchasedProduct;
    }
    public static boolean validSplit(String s) { // +
        String[] words = s.split(" ");
        for (int i = 0; i < words.length - 1; i++) {
            if (words[i].charAt(words[i].length() - 1) != words[i + 1].charAt(0)) {
                return false;
            }
        }
        return true;
    }
    public static boolean waveForm(int[] array) { // Переделано
        for (int i = 1; i < array.length - 1; i++) {
            if (array[i - 1] > array[i] && array[i] < array[i + 1] || array[i - 1] < array[i] && array[i] > array[i + 1]) {
                return true;
            }
        }
        return false;
    }
    public static char commonVowel(String s) { // переделано
        String vow = "aeuyio";
        int max = 0;
        char fin = 'a';
        for (char ch : vow.toCharArray()) {
            int count = 0;
            for (char sim : s.toLowerCase().toCharArray()) {
                if (sim == ch) {
                    count++;
                }
            }
            if (count > max) {
                max = count;
                fin = ch;
            }
        }
        return fin;
    }
    public static int[][] dataScience(int[][] x){ // +
        int n = x[0].length;
        for(int i = 0; i < n; i++){
            int element = 0;
            for(int j = 0; j < n; j++){
                if(j != i){
                    element += x[j][i];
                }
            }
            x[i][i] = (int) Math.round((double) element / (n - 1));

        }

        return x;
    }
}
