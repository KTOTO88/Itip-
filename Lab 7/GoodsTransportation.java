import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

class Warehouse {
    private List<Integer> itemsWeights;

    public Warehouse() {
        // Фиксированный вес товаров на складе
        itemsWeights = new ArrayList<>(Arrays.asList(70, 30, 15, 25, 10, 18, 70, 22, 27, 14, 20));
    }

    public List<Integer> getItemsWeights() {
        return itemsWeights;
    }

    public synchronized void removeItem(int weight) {
        itemsWeights.remove(Integer.valueOf(weight));
    }
}

class Loader implements Runnable {
    private final int loaderId; // Номер грузчика
    private final Warehouse warehouse;
    private final CyclicBarrier cyclicBarrier;
    private final List<Integer> itemsWeights;
    private static int totalWeight = 0; // Общий вес переносимых товаров
    private static final int MAX_WEIGHT = 150; // Максимальный общий вес
    private static boolean limitExceeded = false; // Флаг превышения лимита

    public Loader(int loaderId, Warehouse warehouse, CyclicBarrier cyclicBarrier) {
        this.loaderId = loaderId;
        this.warehouse = warehouse;
        this.cyclicBarrier = cyclicBarrier;
        this.itemsWeights = warehouse.getItemsWeights();
    }

    @Override
    public void run() {
        for (int i = 0; i < itemsWeights.size(); i++) {
            int currentItemWeight = itemsWeights.get(i);

            synchronized (Loader.class) {
                // Проверка, можно ли добавить вес товара к общему весу
                if (totalWeight + currentItemWeight <= MAX_WEIGHT) {
                    totalWeight += currentItemWeight;
                    System.out.println("Грузчик #" + loaderId + " отнёс товар весом " + currentItemWeight + " кг");

                    // Удаление поднятого товара из списка на складе
                    warehouse.removeItem(currentItemWeight);
                } else {
                    // Если добавление превысит лимит, пропускаем товар
                    limitExceeded = true;
                    continue;
                }
            }

            // Эмуляция подъема товара
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Ожидание остальных грузчиков перед отправкой на другой склад
            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }

            // Если это последний товар или общий вес достиг лимита, отправляем грузчиков на другой склад
            if (i == itemsWeights.size() - 1 || totalWeight == MAX_WEIGHT) {
                if (totalWeight == MAX_WEIGHT) {
//                    System.out.println("Грузчики перенесли 150 кг");
                }
                System.out.println("Грузчик #" + loaderId + " отправляется на другой склад");
                break;
            }
        }
    }
}

public class GoodsTransportation {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

        for (int i = 0; i < 3; i++) {
            Thread loaderThread = new Thread(new Loader(i + 1, warehouse, cyclicBarrier));
            loaderThread.start();
        }
    }
}