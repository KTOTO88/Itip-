import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TopWords {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\Александр\\Desktop\\1.txt";

        File file = new File(filePath);

        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Map<String, Integer> wordCounts = new HashMap<>();

        while (scanner.hasNext()) {
            String word = scanner.next().toLowerCase();
            wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
        }

        scanner.close();

        List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordCounts.entrySet());

        Collections.sort(wordList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        int count = 1;
        for (Map.Entry<String, Integer> entry : wordList) {
            System.out.println(count + ". " + entry.getKey() + ": " + entry.getValue());
            count++;
            if (count > 10) {
                break;
            }
        }
    }
}
