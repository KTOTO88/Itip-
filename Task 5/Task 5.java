import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {
        System.out.println("-----Task 1-----");
        System.out.println(sameLetterPattern("ABAB", "CDCD"));
        System.out.println(sameLetterPattern("ABCBA", "BCDCB"));
        System.out.println(sameLetterPattern("FFGG", "CDCD"));
        System.out.println(sameLetterPattern("FFFF", "ABCD"));
        System.out.println("----------------");
        System.out.println(" ");
        System.out.println("-----Task 2-----");
        System.out.println(spiderVsFly("H3", "E2"));
        System.out.println(spiderVsFly("A4", "B2"));
        System.out.println(spiderVsFly("A4", "C2"));
        System.out.println("----------------");
        System.out.println(" ");
        System.out.println("-----Task 3-----");
        System.out.println(digitsCount(4666L));
        System.out.println(digitsCount(544L));
        System.out.println(digitsCount(121317L));
        System.out.println(digitsCount(0L));
        System.out.println(digitsCount(12345L));
        System.out.println(digitsCount(1289396387328L));
        System.out.println("----------------");
        System.out.println(" ");
        System.out.println("-----Task 4-----");
        System.out.println(totalPoints(new String[]{"cat", "create", "sat"}, "caster"));
        System.out.println(totalPoints(new String[]{"trance", "recant"}, "recant"));
        System.out.println(totalPoints(new String[]{"dote", "dotes", "toes", "set", "dot", "dots", "sted"}, "tossed"));
        System.out.println("----------------");
        System.out.println(" ");
        System.out.println("-----Task 5-----");
        System.out.println(sumsUp(new int[]{1, 2, 3, 4, 5}));
        System.out.println(sumsUp(new int[]{1, 2, 3, 7, 9}));
        System.out.println(sumsUp(new int[]{10, 9, 7, 2, 8}));
        System.out.println(sumsUp(new int[]{1, 6, 5, 4, 8, 2, 3, 7}));
        System.out.println("----------------");
        System.out.println(" ");
        System.out.println("-----Task 6-----");
        System.out.println(takeDownAverage(new String[]{"95%", "83%", "90%", "87%", "88%", "93%"}));
        System.out.println(takeDownAverage(new String[]{"10%"}));
        System.out.println(takeDownAverage(new String[]{"53%", "79%"}));
        System.out.println("----------------");
        System.out.println(" ");
        System.out.println("-----Task 7-----");
        System.out.println(caesarCipher("encode", "hello world!", 3));
        System.out.println(caesarCipher("decode", "almost last task!", 4));
        System.out.println("----------------");
        System.out.println(" ");
        System.out.println("-----Task 8-----");
        System.out.println(setSetup(5, 3));
        System.out.println(setSetup(7, 3));
        System.out.println("----------------");
        System.out.println(" ");
        System.out.println("-----Task 9-----");
        System.out.println(timeDifference("Los Angeles", "April 1, 2011 23:23", "Canberra"));
        System.out.println(timeDifference("London", "July 31, 1983 23:01", "Rome"));
        System.out.println(timeDifference("New York", "December 31, 1970 13:40", "Beijing"));
        System.out.println("----------------");
        System.out.println(" ");
        System.out.println("-----Task 10-----");
        System.out.println(isNew(3));
        System.out.println(isNew(30));
        System.out.println(isNew(321));
        System.out.println(isNew(123));
    }

    //1 Шаблон
    public static boolean sameLetterPattern(String s, String t) {
        if (s.length() != t.length()) { //если длина строки s не равна длине строки t, то возвращает false.
            return false;
        }
        //Эти карты будут использоваться для отслеживания индексов, в которых каждый символ последний раз встречается в соответствующей строке.
        HashMap<Character, Integer> smap = new HashMap<>();
        HashMap<Character, Integer> tmap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            //проверяется, совпадает ли его последний известный индекс в s с последним известным индексом соответствующего символа в t.
            if (!Objects.equals(smap.get(s.charAt(i)), tmap.get(t.charAt(i)))) return false;
            //вызовы обновляют карты, записывая текущий индекс i как последний известный индекс для каждого символ
            smap.put(s.charAt(i), i);
            tmap.put(t.charAt(i), i);
        }
        //если все сходится
        return true;
    }


    //2 Паук
    public static String spiderVsFly(String spider, String fly) {
        StringBuilder path = new StringBuilder();
        char spiderRadial = spider.charAt(0);
        int spiderRing = Character.getNumericValue(spider.charAt(1));
        char flyRadial = fly.charAt(0);
        int flyRing = Character.getNumericValue(fly.charAt(1));

        // Если паук и муха на одном радиале
        if (spiderRadial == flyRadial) {
            if (spiderRing > flyRing) {
                while (spiderRing > flyRing) {
                    path.append(spiderRadial).append(spiderRing--).append("-");
                }
            } else {
                while (spiderRing < flyRing) {
                    path.append(spiderRadial).append(spiderRing++).append("-");
                }
            }
            path.append(fly); // Добавляем муху в конце
            return path.toString();
        }

        // Если паук на центре или если быстрее спуститься к центру и подняться к мухе
        if (spiderRing == 0 || spiderRing + flyRing <= ringDistance(spiderRadial, flyRadial)) {
            if (spiderRing > 0) {
                path.append(spider).append("-A0-");
            }
            path.append(flyRadial).append("0-");
            while (flyRing > 0) {
                path.append(flyRadial).append(flyRing--).append("-");
            }
            return path.deleteCharAt(path.length() - 1).toString();
        }

        // Поднимаемся или спускаемся к кольцу мухи
        while (spiderRing != flyRing) {
            path.append(spiderRadial).append(spiderRing).append("-");
            if (spiderRing > flyRing) {
                spiderRing--;
            } else {
                spiderRing++;
            }
        }

        // Перемещение по радиалам к мухе
        int distance = ringDistance(spiderRadial, flyRadial);
        boolean clockwise = (flyRadial - spiderRadial + 8) % 8 < 4;
        for (int i = 1; i <= distance; i++) {
            if (clockwise) {
                spiderRadial = (char) ((spiderRadial - 'A' + 1) % 8 + 'A');
            } else {
                spiderRadial = (char) ((spiderRadial - 'A' + 7) % 8 + 'A');
            }
            path.append(spiderRadial).append(flyRing).append("-");
        }

        // Завершаем путь добавлением мухи
        path.append(fly);

        // Удаление лишнего тире в конце, если есть
        if (path.charAt(path.length() - 1) == '-') {
            path.deleteCharAt(path.length() - 1);
        }

        return path.toString();
    }

    // Вычисление расстояния по кольцу
    private static int ringDistance(char a, char b) {
        int distance = Math.abs(a - b);
        return Math.min(distance, 8 - distance);
    }


    //3 Сколько цифр в числе
    public static int digitsCount(long n) {
        // Базовый случай: если число 0, возвращаем 1, так как у числа 0 одна цифра.
        if (n == 0) {
            return 1;
        }
        // Если число меньше 10, возвращаем 1, так как у всех однозначных чисел одна цифра.
        if (n < 10) {
            return 1;
        }
        // Рекурсивный случай: возвращаем 1 (текущую цифру) + количество цифр оставшегося числа.
        return 1 + digitsCount(n / 10);
    }


    //4 Очки из слов
    public static int totalPoints(String[] guesses, String word) {//массив строк guesses, который содержит слова,
        // угаданные игроком, и строку word, которая является исходным словом.
        int score = 0;
        //метод перебирает все угаданные слова, и если слово может быть сформировано из букв исходного слова
        // (проверяется функцией canBeFormed), к общему счету добавляется количество очков за это слово, рассчитанное
        // функцией getWordScore.
        for (String guess : guesses) {
            if (canBeFormed(guess, word)) {
                score += getWordScore(guess, word);
            }
        }

        return score;
    }

    private static boolean canBeFormed(String guess, String word) {
        Map<Character, Integer> wordLetters = new HashMap<>();//ключ - это символ, а значение - это количество раз,
        // которое символ встречается в word

        //Метод перебирает каждую букву угаданного слова и уменьшает соответствующее количество в карте. Если какой-либо
        // буквы не хватает для формирования слова, метод возвращает false.
        for (char c : word.toCharArray()) {
            wordLetters.put(c, wordLetters.getOrDefault(c, 0) + 1);
        }

        for (char c : guess.toCharArray()) {
            if (!wordLetters.containsKey(c) || wordLetters.get(c) == 0) {
                return false;
            }
            wordLetters.put(c, wordLetters.get(c) - 1);
        }

        return true;
    }

    private static int getWordScore(String guess, String word) {
        return switch (guess.length()) {
            case 3 -> 1;
            case 4 -> 2;
            case 5 -> 3;
            case 6 -> isAnagram(guess, word) ? 54 : 4; // 4 points for the word + 50 bonus points
            default -> 0;
        };
    }

    //Алгоритм похож на canBeFormed, но он также проверяет, что длины слов совпадают,
    // что является необходимым условием для анаграммы.
    private static boolean isAnagram(String guess, String word) {
        if (guess.length() != word.length()) {
            return false;
        }

        Map<Character, Integer> wordLetters = new HashMap<>();
        for (char c : word.toCharArray()) {
            wordLetters.put(c, wordLetters.getOrDefault(c, 0) + 1);
        }

        for (char c : guess.toCharArray()) {
            if (!wordLetters.containsKey(c) || wordLetters.get(c) == 0) {
                return false;
            }
            wordLetters.put(c, wordLetters.get(c) - 1);
        }

        return true;
    }


    //5 Массив пар
    public static ArrayList<ArrayList<Integer>> sumsUp(int[] arr) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();//ArrayList для хранения результата,
        // где каждый элемент ArrayList содержит пару чисел.
        int indexRes = 0;//использоваться для отслеживания индекса в res.
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();//используется для хранения чисел и их количества
        for (int j : arr) {// пробегает по каждому элементу массива arr
            boolean b = true;//для проверки на пару
            for (Integer key : map.keySet()) {
                if (key + j == 8) {//сумма ключа и текущего элемента arr[i] равна 8
                    ArrayList<Integer> cl = new ArrayList<Integer>();//создается новый ArrayList для хранения пары
                    cl.add(0, Math.min(key, j));//минимальное значение между ключом и arr[i]
                    cl.add(1, Math.max(key, j));//максимальное значение
                    res.add(indexRes, cl);//ArrayList добавляется в res по индексу indexRes
                    indexRes++;//увеличивается на 1, чтобы следующая найденная пара добавлялась в следующий индекс.
                    map.remove(key, 1);//map удаляется ключ со значением 1, тк пара найдена
                    b = false;//переменная b устанавливается в false, чтобы обозначить, что текущее число j уже
                    // сформировало пару
                    break;
                }
            }
            if (b)//b остается true, то в map добавляется новый ключ arr[i] со значением 1, что обозначает, что это
                // число теперь доступно для поиска пары в последующих итерациях.
                map.put(j, 1);
        }
        return res;
    }


    //6 Процент теста
    public static String takeDownAverage(String[] arr) {
        if (arr.length == 0)//проверяем длину массива
            return "0%";
        double sum = 0;//сумма всех процентов
        for (String str : arr) {
            int number = Integer.parseInt(str.split("%")[0]);//каждый элемент разделяется на число и знак "%" и преобразуем в int
            sum += number;
        }
        double avg = sum / arr.length;//среднее значение
        double res = (arr.length + 1) * (avg - 5) - sum;//вычисляется оценка, которую необходимо получить в следующий раз
        return Integer.toString((int) Math.round(res)) + "%";//округляем до целого числа и добавляем %
    }


    //7 Шифратор сообщений. Шифр Цезаря — это метод шифрования, при котором каждая буква в тексте заменяется другой
    // буквой, находящейся на фиксированное количество позиций дальше или ближе в алфавите.
    public static String caesarCipher(String mode, String message, int shift) {
        //сдвиг меняется на противоположный (умножается на -1). Это необходимо для того, чтобы "отменить" шифрование,
        // то есть сдвинуть буквы обратно на их исходные позиции.
        if ("decode".equals(mode)) {
            shift = -shift;
        }

        StringBuilder encodedMessage = new StringBuilder();//будет содержать результаты
        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) {//Если символ является буквой, то он подвергается шифрованию или дешифрованию.
                char base = Character.isUpperCase(c) ? 'A' : 'a';//определяем основу алфавита
                int offset = (c - base + shift) % 26;//Вычисляется сдвиг символа. Это делается путем вычитания кода
                // основы алфавита из кода символа, прибавления сдвига и взятия остатка от деления на 26
                if (offset < 0) {
                    offset += 26;//Если результат сдвига отрицательный, к нему прибавляется 26, чтобы обеспечить
                    // корректный циклический сдвиг
                }
                encodedMessage.append((char) (base + offset));//добавляем сдвинутый символ
            } else {
                encodedMessage.append(c);
            }
        }

        return encodedMessage.toString();
    }


    //8 Комбинаторика
    //рекурсивно вычисляем факториал числа n.
    public static int factorial(int n) {
        if (n == 0) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    //вычисляем количество возможных комбинаций из n элементов по k элементов, используя факториальную функцию
    public static int setSetup(int n, int k) {
        if (n < k) {
            return 0;
        }
        return factorial(n) / factorial(n - k);
    }


    //9 Время в городах
    private static final Map<String, Duration> cityOffsets = new HashMap<>();//создается статический словарь cityOffsets

    static {
        cityOffsets.put("Los Angeles", Duration.ofHours(-8));
        cityOffsets.put("New York", Duration.ofHours(-5));
        cityOffsets.put("Caracas", Duration.ofHours(-4).plusMinutes(-30));
        cityOffsets.put("Buenos Aires", Duration.ofHours(-3));
        cityOffsets.put("London", Duration.ofHours(0));
        cityOffsets.put("Rome", Duration.ofHours(1));
        cityOffsets.put("Moscow", Duration.ofHours(3));
        cityOffsets.put("Tehran", Duration.ofHours(3).plusMinutes(30));
        cityOffsets.put("New Delhi", Duration.ofHours(5).plusMinutes(30));
        cityOffsets.put("Beijing", Duration.ofHours(8));
        cityOffsets.put("Canberra", Duration.ofHours(10));
    }

    public static String timeDifference(String cityA, String timestamp, String cityB) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy HH:mm", Locale.US);//объект с указанным шаблоном
        //Из строки timestamp методом parse собирает объект dateTimeA класса LocalDateTime с использованием созданного ранее formatter.
        LocalDateTime dateTimeA = LocalDateTime.parse(timestamp, formatter);

        Duration offsetA = cityOffsets.getOrDefault(cityA, Duration.ZERO);//получает значения из словаря, если нет то присваивает Duration.ZERO
        LocalDateTime dateTimeB = dateTimeA.plus(offsetA);//добовляется смещение и получается новый объект

        Duration offsetB = cityOffsets.getOrDefault(cityB, Duration.ZERO);
        dateTimeB = dateTimeB.plus(offsetB);

        DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("yyyy-M-d HH:mm");//новый объект
        return dateTimeB.format(newFormatter);//форматируется в строку и выводится эта строка
    }

    //10 Новое число
    public static boolean isNew(int num) {
        String str = String.valueOf(num);//преобразуется в строку
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) != '0' && str.charAt(i) < str.charAt(0)) {//если текущий символ не равен нулю и меньше первого символа строки
                return false;//то возвращается значение false
            }
        }
        return true;
    }
}