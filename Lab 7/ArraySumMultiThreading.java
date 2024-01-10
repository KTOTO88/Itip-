public class ArraySumMultiThreading {
    private static int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    private static int result = 0;

    public static void main(String[] args) {
        int mid = array.length / 2;

        Thread thread1 = new Thread(() -> {
            int sum = 0;
            for (int i = 0; i < mid; i++) {
                sum += array[i];
            }
            synchronized (ArraySumMultiThreading.class) {
                result += sum;
            }
        });

        Thread thread2 = new Thread(() -> {
            int sum = 0;
            for (int i = mid; i < array.length; i++) {
                sum += array[i];
            }
            synchronized (ArraySumMultiThreading.class) {
                result += sum;
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Результат: " + result);
    }
}
