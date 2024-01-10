import java.util.LinkedList;


public class HashTable<K, V> {
    private LinkedList<Entry<K, V>>[] table;
    private int size;

    public HashTable(int initialCapacity) {
        table = new LinkedList[initialCapacity];
        size = 0;
    }

    public void put(K key, V value) {
        int index = hash(key);
        if (table[index] == null) {
            table[index] = new LinkedList<>();
        }
        for (Entry<K, V> entry : table[index]) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
        }
        table[index].add(new Entry<>(key, value));
        size++;
    }

    public V get(K key) {
        int index = hash(key);
        if (table[index] != null) {
            for (Entry<K, V> entry : table[index]) {
                if (entry.getKey().equals(key)) {
                    return entry.getValue();
                }
            }
        }
        return null;
    }

    public void remove(K key) {
        int index = hash(key);
        if (table[index] != null) {
            for (Entry<K, V> entry : table[index]) {
                if (entry.getKey().equals(key)) {
                    table[index].remove(entry);
                    size--;
                    return;
                }
            }
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int hash(K key) {
        return key.hashCode() % table.length;
    }

    private static class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }
    public static void main(String[] args) {
        HashTable<String, Integer> table = new HashTable<>(10);
        table.put("a", 1);
        table.put("b", 2);
        table.put("a", 3);

        System.out.println(table.get("a"));
        table.remove("b");
        System.out.println(table.get("a"));
        System.out.println(table.size());
        System.out.println(table.isEmpty());
//        System.out.println(table.get("a"));
//        System.out.println(table.size());
//        System.out.println(table.isEmpty());
//        System.out.println("------------------");
//        table.remove("b");
//        System.out.println(table.get("b"));
//        System.out.println(table.size());
//        System.out.println(table.isEmpty());
//        System.out.println("------------------");
//        table.remove("c");
//        table.remove("a");
//        System.out.println(table.get("c"));
//        System.out.println(table.get("a"));
//        System.out.println(table.size());
//        System.out.println(table.isEmpty());
    }
}
