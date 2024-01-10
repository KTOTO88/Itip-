import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("---Task1---");
        System.out.println(nonRepeatable("abracadabra"));
        System.out.println(nonRepeatable("paparazzi"));

        System.out.println("---Task2---");
        System.out.println(generateBrackets(1));
        System.out.println(generateBrackets(2));
        System.out.println(generateBrackets(3));

        System.out.println("---Task3---");
        System.out.println(Arrays.toString(new List[]{binarySystem(3)}));
        System.out.println(Arrays.toString(new List[]{binarySystem(4)}));

        System.out.println("---Task4---");
        System.out.println(alphabeticRow("abcdjuwx"));
        System.out.println(alphabeticRow("klmabzyxw"));

        System.out.println("---Task5---");
        System.out.println(simbCounter("aaabbcdd"));
        System.out.println(simbCounter("vvvvaajaaaaa"));

        System.out.println("---Task6---");
        System.out.println(convertToNum("eight"));
        System.out.println(convertToNum("five hundred sixty seven"));
        System.out.println(convertToNum("thirty one"));

        System.out.println("---Task7---");
        System.out.println(uniqueSubstring("123412324"));
        System.out.println(uniqueSubstring("111111"));
        System.out.println(uniqueSubstring("77897898"));

        System.out.println("---Task8---");
        System.out.println(shortestWay(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}));
        System.out.println(shortestWay(new int[][]{{2, 7, 3}, {1, 4, 8}, {4, 5, 9}}));

        System.out.println("---Task9---");
        System.out.println(numericOrder("t3o the5m 1One all6 r4ule ri2ng"));
        System.out.println(numericOrder("re6sponsibility Wit1h gr5eat power3 4comes g2reat"));

        System.out.println("---Task10---");
        System.out.println(switchNums(519, 723));
        System.out.println(switchNums(491, 3912));
        System.out.println(switchNums(6274, 71259));
    }

// Задача 1: удаление повторяющихся символов в строке

    public static String nonRepeatable(String str) {
        // Если длина строки меньше или равна 1, возвращаем исходную строку
        if (str.length() <= 1) {
            return str;
        }
        // Если в остатке строки после первого символа содержится первый символ,
        // вызываем рекурсивно функцию nonRepeatable с остатком строки без первого символа
        if (str.substring(1).contains(str.substring(0, 1))) {
            return nonRepeatable(str.substring(1));
        } else {
            // Иначе, возвращаем первый символ конкатенированный с результатом вызова функции nonRepeatable
            // для остатка строки без первого символа
            return str.substring(0, 1) + nonRepeatable(str.substring(1));
        }
    }

    // Задача 2: генерация всех возможных правильных комбинаций пар скобок
    public static List<String> generateBrackets(int n) {
        // Создание списка для хранения результатов
        List<String> result = new ArrayList<>();
        // Вызов вспомогательной функции generateBracketsHelper с начальными значениями
        generateBracketsHelper(n, n, "", result);
        // Возвращение списка результатов
        return result;
    }

    private static void generateBracketsHelper(int open, int close, String current, List<String> result) {
        // Если количество открывающих и закрывающих скобок равно 0,
        // значит все скобки расставлены правильно, добавляем текущую комбинацию в список результатов и выходим из функции
        if (open == 0 && close == 0) {
            result.add(current);
            return;
        }

        // Если еще остались доступные открывающие скобки, рекурсивно вызываем функцию generateBracketsHelper
        // с уменьшенным количеством открывающих скобок, добавляя открывающую скобку к текущей комбинации
        if (open > 0) {
            generateBracketsHelper(open - 1, close, current + "(", result);
        }

        // Если количество закрывающих скобок превышает количество открывающих скобок,
        // рекурсивно вызываем функцию generateBracketsHelper с уменьшенным количеством закрывающих скобок,
        // добавляя закрывающую скобку к текущей комбинации
        if (close > open) {
            generateBracketsHelper(open, close - 1, current + ")", result);
        }
    }

    // Задача 3: генерация всех возможных бинарных комбинаций без соседствующих нулей
    public static List<String> binarySystem(int n) {
        // Создание списка для хранения результатов
        List<String> result = new ArrayList<>();
        // Вызов вспомогательной функции binarySystemHelper с начальными значениями
        binarySystemHelper(n, "", result);
        // Возвращение списка результатов
        return result;
    }

    private static void binarySystemHelper(int n, String current, List<String> result) {
        // Если длина текущей комбинации равна заданному значению n,
        // значит все символы уже добавлены, добавляем текущую комбинацию в список результатов и выходим из функции
        if (current.length() == n) {
            result.add(current);
            return;
        }

        // Если текущая комбинация содержит "10",
        // вызываем рекурсивно binarySystemHelper, добавляя символ "1" к текущей комбинации
        if (current.contains("10")) {
            binarySystemHelper(n, current + "1", result);
        } else {
            // Иначе, если текущая комбинация не содержит "10",
            // вызываем рекурсивно binarySystemHelper дважды:
            // с добавлением символа "1" к текущей комбинации
            binarySystemHelper(n, current + "1", result);
            // и с добавлением символа "0" к текущей комбинации
            binarySystemHelper(n, current + "0", result);
        }
    }

    // Задача 4: поиск самого длинного последовательного ряда в строке
    public static String alphabeticRow(String str) {
        // Переменная для хранения самого длинного ряда
        String longestRow = "";
        // Переменная для хранения текущего ряда
        String currentRow = "";
        // Предыдущий символ, инициализирован пустым символом '\0'
        char prevChar = '\0';

        // Проходим по каждому символу в строке
        for (char c : str.toCharArray()) {
            // Если предыдущий символ пустой ('\0') или разница между текущим и предыдущим символом равна 1,
            // или разница между предыдущим и текущим символом равна 1,
            // то текущий символ добавляется к текущему ряду
            if (prevChar == '\0' || c - prevChar == 1 || prevChar - c == 1) {
                currentRow += c;
            } else {
                // Иначе, если текущий ряд больше самого длинного ряда,
                // обновляем самый длинный ряд
                if (currentRow.length() > longestRow.length()) {
                    longestRow = currentRow;
                }
                // Сбрасываем текущий ряд на текущий символ
                currentRow = Character.toString(c);
            }
            // Обновляем предыдущий символ
            prevChar = c;
        }

        // Проверяем, если текущий ряд больше самого длинного ряда,
        // обновляем самый длинный ряд
        if (currentRow.length() > longestRow.length()) {
            longestRow = currentRow;
        }

        // Возвращаем самый длинный ряд
        return longestRow;
    }

    // Задача 5: подсчет количества идущих подряд символов (перебираем циклом и смотрим равен ли прошлый символ текущему)
    public static String simbCounter(String str) {
        // Создаем объект StringBuilder для построения результирующей строки
        StringBuilder result = new StringBuilder();
        // Переменная для подсчета количества идущих подряд символов
        int count = 1;

        // Идем по строке, начиная с индекса 1
        for (int i = 1; i < str.length(); i++) {
            // Если текущий символ равен предыдущему символу, увеличиваем счетчик
            if (str.charAt(i) == str.charAt(i - 1)) {
                count++;
            } else {
                // Иначе, если текущий символ отличается от предыдущего, добавляем предыдущий символ
                // и количество идущих подряд символов к результирующей строке,
                // а затем сбрасываем счетчик в 1
                result.append(str.charAt(i - 1)).append(count);
                count = 1;
            }
        }

        // Добавляем последний символ строки и количество идущих подряд символов к результирующей строке
        result.append(str.charAt(str.length() - 1)).append(count);

        // Преобразуем результирующую строку в массив символов,
        // сортируем массив символов и создаем новую строку из отсортированного массива символов
        char[] chars = result.toString().toCharArray();
        Arrays.sort(chars);

        // Возвращаем отсортированную строку
        return new String(chars);
    }

    // Задача 6: преобразование строки с числовыми словами в целое число - переделать
    public static int convertToNum(String numberString) {
        Map<String, Integer> numberMap = new HashMap<>(); //будет использоваться для сопоставления слов с их числовыми значениями
        numberMap.put("zero", 0); //добавляется запись в numberMap, где ключом является слово, а значением - числовое представление этого слова
        numberMap.put("one", 1);
        numberMap.put("two", 2);
        numberMap.put("three", 3);
        numberMap.put("four", 4);
        numberMap.put("five", 5);
        numberMap.put("six", 6);
        numberMap.put("seven", 7);
        numberMap.put("eight", 8);
        numberMap.put("nine", 9);
        numberMap.put("ten", 10);
        numberMap.put("eleven", 11);
        numberMap.put("twelve", 12);
        numberMap.put("thirteen", 13);
        numberMap.put("fourteen", 14);
        numberMap.put("fifteen", 15);
        numberMap.put("sixteen", 16);
        numberMap.put("seventeen", 17);
        numberMap.put("eighteen", 18);
        numberMap.put("nineteen", 19);
        numberMap.put("twenty", 20);
        numberMap.put("thirty", 30);
        numberMap.put("forty", 40);
        numberMap.put("fifty", 50);
        numberMap.put("sixty", 60);
        numberMap.put("seventy", 70);
        numberMap.put("eighty", 80);
        numberMap.put("ninety", 90);
        numberMap.put("hundred", 100);

        String[] words = numberString.split(" "); //разделяем слова и сохраняем в массив
        int current = 0;

        for (String word : words) {
            int value = numberMap.get(word); //извлекается соответствующее числовое значение
            if (value == 100) {
                current = current * value;
            } else {
                current += value;
            }
        }
        return current;
    }

    // Задача 7: поиск подстроки с максимальной длиной уникальных элементов
    public static String uniqueSubstring(String str) {
        // Переменная для хранения результирующей подстроки
        String result = "";
        // Переменная для хранения текущей подстроки
        String currentSubstring = "";

        // Итерируемся по каждому символу в строке
        for (char c : str.toCharArray()) {
            // Если текущая подстрока содержит символ c, значит нашли повторение
            if (currentSubstring.contains(Character.toString(c))) {
                // Если текущая подстрока длиннее результирующей, обновляем результат
                if (currentSubstring.length() > result.length()) {
                    result = currentSubstring;
                }
                // Обрезаем текущую подстроку, начиная с индекса повторяющегося символа
                currentSubstring = currentSubstring.substring(currentSubstring.indexOf(c) + 1);
            }
            // Добавляем символ c в текущую подстроку
            currentSubstring += c;
        }

        // Проверяем, есть ли более длинная уникальная подстрока в конце строки
        if (currentSubstring.length() > result.length()) {
            result = currentSubstring;
        }

        // Возвращаем результирующую подстроку с максимальной длиной уникальных элементов
        return result;
    }

    // Задача 8: наименьший матричный путь
    public static int shortestWay(int[][] matrix) {
        // Получаем размерность матрицы (количество строк)
        int n = matrix.length;
        // Создаем двумерный массив dp для хранения промежуточных результатов
        int[][] dp = new int[n][n];

        // Инициализируем значение dp[0][0] значением matrix[0][0]
        dp[0][0] = matrix[0][0];

        // Заполняем первую строку и первый столбец dp
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + matrix[i][0];
            dp[0][i] = dp[0][i - 1] + matrix[0][i];
        }

        // Заполняем остальные ячейки dp
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + matrix[i][j];
            }
        }

        // Возвращаем значение в нижней правой ячейке dp, которая содержит наименьший матричный путь
        return dp[n - 1][n - 1];
    }

    // Задача 9: перестройка строки с числами в правильном порядке
    public static String numericOrder(String str) {
        // Создается объект result класса StringBuilder, который будет использоваться
        // для построения итоговой строки результата.
        StringBuilder result = new StringBuilder();

        // Разделение строки на слова
        String[] string = str.split(" ");

        // Цикл для сортировки по числовому значению:
        for (int i = 1; i <= string.length; i++) {

            // Цикл для проверки и добавления слов
            for (String word : string) {

                //
                if (word.contains(String.valueOf(i))) {
                    result.append(word.replaceAll("\\d", "")).append(" ");
                }
            }
        }
        return result.toString();
    }


    // Задача 10
    public static int switchNums(int n1, int n2) {
        // Преобразование чисел в массив символов чтобы работать с отдельными символами чисел.
        char[] n1Char = String.valueOf(n1).toCharArray();
        char[] n2Char = String.valueOf(n2).toCharArray();

        // Сортировка символов в n1Char в порядке возрастания
        Arrays.sort(n1Char);

        // Создание нового массива символов newChar той же длины, что и n1Char.
        char[] newChar = new char[n1Char.length];
        for (int i = 0; i < n1Char.length; i++) {

            // Расположение символов в новом массиве newChar в обратном порядке
            newChar[n1Char.length - 1 - i] = n1Char[i];
        }

        // Замена символов в n2Char на символы из newChar
        for (char j : newChar) {
            for (int c = 0; c < n2Char.length; c++) {

                // Для каждого символа j в newChar проверяем каждый символ в n2Char
                // Если найден символ в n2Char, меньший, чем j, заменяем его на j и выходим из цикла
                if (j > n2Char[c]) {
                    n2Char[c] = j;
                    break;
                }
            }
        }
        // Конвертация результирующего массива символов n2Char в число и возврат значения
        return Integer.parseInt(String.valueOf(n2Char));
    }
}