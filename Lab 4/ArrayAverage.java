public class ArrayAverage {
    public static void main(String[] args) {
        String[] arr = {"1", "2", "three", "4", "5"};
        int sum = 0;
        try {
            for (String str : arr) {
                sum += Integer.parseInt(str);
            }
            double result = (double) sum / arr.length;
            System.out.println(result);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: элемент массива не является числом" + e.getMessage());
        }
    }
}